package com.example.attendanceapimono.adapter.present.api

import com.example.attendanceapimono.application.dto.user.CreateUserRequest
import com.example.attendanceapimono.application.dto.user.SignInRequest
import com.example.attendanceapimono.application.dto.user.TokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono
import javax.validation.Valid
import io.swagger.v3.oas.annotations.parameters.RequestBody as DocRequestBody

@Tag(name = "유저 관련 API")
interface UserAPI {

    @Operation(
        summary = "유저 생성",
        requestBody = DocRequestBody(content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = CreateUserRequest::class),
            )
        ])
    )
    @ApiResponse(
        responseCode = "201",
        description = "일반 참가자 생성",
        content = [Content(examples = [ExampleObject("")])],
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    suspend fun createUser(@Valid @RequestBody body: Mono<CreateUserRequest>)

    @Operation(
        summary = "로그인",
    )
    @ApiResponse(
        responseCode = "200",
        description = "로그인",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = TokenResponse::class),
            examples = [ExampleObject(TokenResponse.Example)]
        )],
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/user/login")
    suspend fun signIn(@Valid @RequestBody body: Mono<SignInRequest>): TokenResponse
}