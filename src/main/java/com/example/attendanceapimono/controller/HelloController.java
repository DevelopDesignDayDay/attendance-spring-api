package com.example.attendanceapimono.controller;

import com.example.attendanceapimono.exception.JavaTestException;
import com.example.attendanceapimono.exception.KotlinTestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Hello World")
@RestController
public class HelloController {

    @Operation(
            summary = "Hello World",
            description = "Response \"hello attendance api world\"",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject("\"hello attendance api world\"")
                            )
                    )
            }
    )
    @GetMapping("/")
    public Mono<ResponseEntity<?>> helloWorld() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("\"hello attendance api world\"")
        );
    }


    @Operation(
            summary = "Throw Test Java Exception",
            description = "Test, Throw Java Exception"
    )
    @GetMapping("/test-java-exception")
    public void exceptionJava() {
        throw new JavaTestException();
    }

    @Operation(
            summary = "Throw Test Kotlin Exception",
            description = "Test, Throw Kotlin Exception"
    )
    @GetMapping("/test-kotlin-exception")
    public void exceptionKotlin() throws KotlinTestException {
        throw new KotlinTestException();
    }

    @Operation(
            summary = "Throw Test Unknown Exception",
            description = "Test, Throw Unknown Exception"
    )
    @GetMapping("/test-unknown-exception")
    public void exceptionUnknown() throws Exception {
        throw new Exception("unknown exception");
    }

}
