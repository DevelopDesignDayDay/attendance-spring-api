package com.example.attendanceapimono.adapter.infra.advice

import com.example.attendanceapimono.application.exception.BadRequestBodyException
import com.example.attendanceapimono.application.exception.KotlinTestException
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

@RestControllerAdvice
@Slf4j
class ExceptionController {
    private val errorMaps: Map<Class<*>, ErrorResponse> = mapOf(
        KotlinTestException::class.java to ErrorResponse("hello test kotlin error", -999999),
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