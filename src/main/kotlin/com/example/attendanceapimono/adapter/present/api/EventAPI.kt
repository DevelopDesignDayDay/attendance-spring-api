package com.example.attendanceapimono.adapter.present.api

import com.example.attendanceapimono.application.dto.event.CreateEventRequest
import com.example.attendanceapimono.application.dto.event.CreateEventResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono
import javax.validation.Valid

@Tag(name = "Event 관련 API")
interface EventAPI {
    @Operation(
        summary = "이벤트 생성",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = CreateEventRequest::class),
                )
            ]
        )
    )
    @ApiResponse(
        responseCode = "201",
        description = "이벤트 생성",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = CreateEventResponse::class),
            examples = [ExampleObject(CreateEventResponse.Example)]
        )],
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/event")
    suspend fun createEvent(@Valid @RequestBody body: Mono<CreateEventRequest>): CreateEventResponse
}