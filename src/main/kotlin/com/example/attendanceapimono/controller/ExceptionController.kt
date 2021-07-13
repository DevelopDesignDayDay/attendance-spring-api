package com.example.attendanceapimono.controller

import com.example.attendanceapimono.exception.JavaTestException
import com.example.attendanceapimono.exception.KotlinTestException
import com.example.attendanceapimono.util.log
import com.example.attendanceapimono.vo.ErrorResponse
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
        JavaTestException::class.java to ErrorResponse( "hello test java error"),
    )

    @ExceptionHandler(value = [KotlinTestException::class, JavaTestException::class])
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun testError(e: Exception) = errorMaps[e.javaClass]!!

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknown(e: Exception) {
        log.error("server internal error", e)
    }
}