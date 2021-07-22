package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.application.exception.KotlinTestException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@Tag(name = "Hello World")
@RestController
class HelloWorldController {

    @Operation(
        summary = "Hello World",
        description = "Response \"hello ddd attendance api mono world\"",
    )
    @ApiResponse(
        responseCode = "200",
        description = "api 서비스의 핼로우 월드 기능, health check 용",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = String::class),
                examples = [ExampleObject("\"hello ddd attendance api mono world\"")]
            )
        ]
    )
    @GetMapping("/")
    fun helloWorld(): Mono<ResponseEntity<*>>? {
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("\"hello ddd attendance api mono world\"")
        )
    }


    @Operation(summary = "Throw Test Kotlin Exception", description = "Test, Throw Kotlin Exception")
    @GetMapping("/test-kotlin-exception")
    fun exceptionKotlin() {
        throw KotlinTestException()
    }

    @Operation(summary = "Throw Test Unknown Exception", description = "Test, Throw Unknown Exception")
    @GetMapping("/test-unknown-exception")
    @Throws(
        Exception::class
    )
    fun exceptionUnknown() {
        throw Exception("unknown exception")
    }
}