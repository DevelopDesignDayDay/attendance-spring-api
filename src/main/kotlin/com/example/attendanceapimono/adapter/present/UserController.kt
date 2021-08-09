package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.UserAPI
import com.example.attendanceapimono.application.UserService
import com.example.attendanceapimono.application.dto.user.CreateUserRequest
import com.example.attendanceapimono.application.dto.user.SignInRequest
import com.example.attendanceapimono.application.dto.user.TokenResponse
import com.example.attendanceapimono.application.exception.handleValidationCatch
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(private val userService: UserService) : UserAPI {
    override suspend fun createUser(body: Mono<CreateUserRequest>) {
        body.handleValidationCatch()
            .map(userService::createUser)
            .awaitSingle()
    }

    override suspend fun signIn(body: Mono<SignInRequest>): TokenResponse {
         return body.handleValidationCatch()
             .map(userService::getUserBySocialToken)
             .awaitSingle()
    }
}

/*
 [{"error_description": "Invalid Value"}]
* */