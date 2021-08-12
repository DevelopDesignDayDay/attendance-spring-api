package com.example.attendanceapimono.util

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono

fun <T> Mono<T>.httpOk(contentType: MediaType = MediaType.APPLICATION_JSON) =
    ResponseEntity.ok()
        .contentType(contentType)
        .body(ifUnitEmptyFlatMap())

fun <T> Mono<T>.httpCreated(contentType: MediaType = MediaType.APPLICATION_JSON) =
    ResponseEntity.status(HttpStatus.CREATED)
        .contentType(contentType)
        .body(ifUnitEmptyFlatMap())


fun <T> Mono<T>.ifUnitEmptyFlatMap() = flatMap {
    if (it is Unit || it == null) Mono.empty()
    else Mono.just(it)
}