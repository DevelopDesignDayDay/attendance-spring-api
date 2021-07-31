package com.example.attendanceapimono.application

import com.example.attendanceapimono.adapter.infra.security.TokenProvider
import com.example.attendanceapimono.application.dto.user.CreateUser
import com.example.attendanceapimono.application.dto.user.SignIn
import com.example.attendanceapimono.domain.user.*
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService(
    private val userRepository: UserRepository,
    private val socialProviderRepository: SocialProviderRepository,
    @Qualifier("googleAdapter")
    private val googleAdapter: SocialAdapter,
    private val tokenProvider: TokenProvider
) {

    @Transactional
    fun createUser(dto: CreateUser): Unit = runBlocking {
        val user = dto.entity()
        listOf(
            async { userRepository.save(user) },
            async {
                val socialInfo = when (dto.type) {
                    SocialType.GOOGLE->googleAdapter.findByToken(dto.token)
                    SocialType.APPLE->TODO("not implemented, throw exception")
                }
                val socialID = SocialProviderID(socialInfo.id, socialInfo.type)
                socialProviderRepository
                    .findByIdOrNull(
                        socialID
                    )?.run {
                        TODO("conflict, exists social provider, throw exception")
                    }
                socialProviderRepository.save(
                    SocialProvider(
                        socialID,
                        user
                    )
                )
            }
        ).awaitAll()
    }

    fun getUserBySocialToken(dto: SignIn) = runBlocking {
        val result: Deferred<SocialProvider?> = async {
            val socialID = SocialProviderID(dto.token, dto.type)

            socialProviderRepository.findByIdOrNull(socialID)
        }

        val socialProvider = result.await();

        val jwtToken = socialProvider?.let {
            tokenProvider.createToken(it.user.id)
        } ?: {
            TODO("conflict, not found social provider, throw exception")
        };

        jwtToken;
    }
}