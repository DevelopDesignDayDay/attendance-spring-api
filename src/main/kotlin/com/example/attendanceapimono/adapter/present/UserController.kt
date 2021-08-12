package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.UserAPI
import com.example.attendanceapimono.application.UserService
import com.example.attendanceapimono.application.dto.user.CreateUserRequest
import com.example.attendanceapimono.application.dto.user.ReSignResponse
import com.example.attendanceapimono.application.dto.user.SignInRequest
import com.example.attendanceapimono.application.dto.user.TokenResponse
import com.example.attendanceapimono.application.exception.handleValidationCatch
import com.example.attendanceapimono.util.httpOk
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class UserController(private val userService: UserService) : UserAPI {
    override suspend fun createUser(body: Mono<CreateUserRequest>): ResponseEntity<Mono<TokenResponse>> {
        return body.handleValidationCatch()
            .map(userService::createUser)
            .httpOk()
    }

    override suspend fun signIn(body: Mono<SignInRequest>): TokenResponse {
         return body.handleValidationCatch()
             .map(userService::getUserBySocialToken)
             .awaitSingle()
    }

    override suspend fun reSign(userID: UUID): ResponseEntity<Mono<ReSignResponse>> {
        return Mono.just(userID)
            .map(userService::reSignByID)
            .httpOk()
    }
}

/*
 [{"error_description": "Invalid Value"}]
* */