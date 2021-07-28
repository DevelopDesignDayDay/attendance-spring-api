package com.example.attendanceapimono.application

import com.example.attendanceapimono.application.dto.user.CreateUser
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
    private val googleAdapter: SocialAdapter
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
}