package com.example.attendanceapimono.application.exception

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebInputException
import reactor.core.publisher.Mono
import java.lang.RuntimeException

class BadRequestBodyException : RuntimeException {
    constructor(cause: WebExchangeBindException) : super(cause.fieldErrors.joinToString(";") {
        "${it.field}(${it.rejectedValue})=${it.defaultMessage}"
    }, cause)

    constructor(cause: ServerWebInputException) : super(cause.message, cause)
}

fun <T> Flow<T>.handleValidationCatch(): Flow<T> {
    return catch { handle(it) }
}

fun <T> Mono<T>.handleValidationCatch(): Mono<T> {
    return doOnError(::handle)
}

private fun handle(it: Throwable) {
    when (it) {
        is WebExchangeBindException->throw BadRequestBodyException(it)
        is ServerWebInputException->throw BadRequestBodyException(it)
        else->throw it
    }
}