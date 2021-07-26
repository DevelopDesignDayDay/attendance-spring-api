package com.example.attendanceapimono.adapter.present.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus

@Tag(name = "Hello World")
interface HelloWorldAPI {

    @Operation(summary = "Hello World", description = "Response \"hello ddd attendance api mono world\"")
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    suspend fun helloWorld(): ResponseEntity<*>
}