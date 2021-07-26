package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.HelloWorldAPI
import com.example.attendanceapimono.application.exception.KotlinTestException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
class HelloWorldController : HelloWorldAPI {


    override suspend fun helloWorld() = ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body("\"hello ddd attendance api mono world\"")
}