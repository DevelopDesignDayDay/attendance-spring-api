package com.example.attendanceapimono.adapter.present.api

import com.example.attendanceapimono.adapter.infra.security.UserPrincipal
import com.example.attendanceapimono.adapter.present.LoginUserID
import com.example.attendanceapimono.application.dto.user.CreateUserRequest
import com.example.attendanceapimono.application.dto.user.ReSignResponse
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
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono
import java.util.*
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
        responseCode = "200",
        description = "일반 참가자 생성",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = TokenResponse::class),
            examples = [ExampleObject(TokenResponse.Example)]
        )],
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    suspend fun createUser(@Valid @RequestBody body: Mono<CreateUserRequest>): ResponseEntity<Mono<TokenResponse>>

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

    @Operation(
        summary = "토큰 체크 또는 재생성",
    )
    @ApiResponse(
        responseCode = "200",
        description = "토큰 체크 및 재생성 하는 기능",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = ReSignResponse::class),
            examples = [ExampleObject(ReSignResponse.Example)]
        )],
    )
    @JWTTokenV1
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/token")
    suspend fun reSign(@LoginUserID userID: UUID): ResponseEntity<Mono<ReSignResponse>>
}