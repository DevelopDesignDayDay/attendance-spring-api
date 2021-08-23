package com.example.attendanceapimono.adapter.present.api

import com.example.attendanceapimono.adapter.present.LoginUserID
import com.example.attendanceapimono.application.dto.attendace.QRCodeResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono

@Tag(name = "출쳌 관련 API")
interface AttendanceAPI {

    @Operation(
        summary = "QR 코드 생성",
    )
    @ApiResponse(
        responseCode = "200",
        description = "QR 코드 생성 완료",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = QRCodeResponse::class),
            examples = [ExampleObject(QRCodeResponse.Example)]
        )],
    )
    @JWTTokenV1
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/qr-code")
    fun getQRCode(@LoginUserID id: String): ResponseEntity<Mono<QRCodeResponse>>
}