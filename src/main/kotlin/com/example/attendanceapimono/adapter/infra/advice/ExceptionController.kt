package com.example.attendanceapimono.adapter.infra.advice

import com.example.attendanceapimono.application.exception.KotlinTestException
import com.example.attendanceapimono.util.log
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Slf4j
class ExceptionController {
    private val errorMaps: Map<Class<*>, ErrorResponse> = mapOf(
        KotlinTestException::class.java to ErrorResponse("hello test kotlin error", -999999),
    )

    @ExceptionHandler(value = [KotlinTestException::class])
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun testError(e: Exception) = errorMaps[e.javaClass]!!

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknown(e: Exception) {
        log.error("server internal error", e)
    }
}