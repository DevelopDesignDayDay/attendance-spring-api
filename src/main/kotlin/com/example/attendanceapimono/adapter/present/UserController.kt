package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.UserAPI
import com.example.attendanceapimono.application.UserService
import com.example.attendanceapimono.application.dto.user.CreateUser
import com.example.attendanceapimono.application.dto.user.SignIn
import com.example.attendanceapimono.application.exception.handleValidationCatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(private val userService: UserService) : UserAPI {
    override suspend fun createUser(body: Mono<CreateUser>) {
        body.handleValidationCatch()
            .map(userService::createUser)
            .awaitSingle()
    }

    override suspend fun signIn(body: Mono<SignIn>) {
         body.handleValidationCatch()
             .map(userService::getUserBySocialToken)
             .awaitSingle()
    }
}

/*
 [{"error_description": "Invalid Value"}]
* */