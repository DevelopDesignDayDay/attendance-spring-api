package com.example.attendanceapimono.adapter.infra.advice

import com.example.attendanceapimono.application.exception.BadRequestBodyException
import com.example.attendanceapimono.application.exception.KotlinTestException
import com.example.attendanceapimono.application.exception.SocialProviderNotFoundException
import com.example.attendanceapimono.application.exception.UserExistsException
import com.example.attendanceapimono.util.log
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


// === 에러 코드 ===
// 1x -> 인증 관련
// 1xx -> 유저 관련
// ===============

@RestControllerAdvice
class ExceptionController {
    private val errorMaps: Map<Class<*>, ErrorResponse> = mapOf(
        KotlinTestException::class.java
                to ErrorResponse("hello test kotlin error", -999999),
        SocialProviderNotFoundException::class.java
                to ErrorResponse(SocialProviderNotFoundException.message, 11),

        UserExistsException::class.java
                to ErrorResponse(UserExistsException.message, 101)
    )

    @ApiResponse(
        responseCode = "400",
        description = "잘못된 요청 형식",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = ErrorResponse::class),
            examples = [ExampleObject(ErrorResponse.ExampleOnlyMessage)]
        )]
    )
    @ExceptionHandler(BadRequestBodyException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationExceptionHandler(e: BadRequestBodyException) = ErrorResponse(e.message ?: "")

    @ApiResponse(
        responseCode = "401",
        description = "인증 실패",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = ErrorResponse::class),
                examples = [
                    ExampleObject(
                        summary = "소셜 정보 없음",
                        value = ErrorResponse.SocialProviderNotFound
                    )
                ]
            ),
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = ErrorResponse::class),
                examples = [
                    ExampleObject(
                        summary = "소셜 정보 없음",
                        value = ErrorResponse.SocialProviderNotFound
                    )
                ]
            )
        ]
    )
    @ExceptionHandler(SocialProviderNotFoundException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun unauthorized(e: Throwable) = errorMaps[e.javaClass]

    @ApiResponse(
        responseCode = "409",
        description = "서버 상태 충돌",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = ErrorResponse::class),
                examples = [
                    ExampleObject(
                        summary = "이미 가입된 유저",
                        value = ErrorResponse.UserExists
                    )
                ]
            )
        ]
    )
    @ExceptionHandler(UserExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun conflict(e: Throwable)  = errorMaps[e.javaClass]

    @Hidden
    @ExceptionHandler(value = [KotlinTestException::class])
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun testError(e: Throwable) = errorMaps[e.javaClass]!!

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknown(e: Throwable) {
        log.error("server internal error", e)
    }
}