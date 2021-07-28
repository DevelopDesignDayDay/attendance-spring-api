package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.HelloWorldAPI
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController : HelloWorldAPI {
    override suspend fun helloWorld() = ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body("\"hello ddd attendance api mono world\"")
}