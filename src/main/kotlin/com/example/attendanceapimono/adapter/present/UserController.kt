package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.UserAPI
import com.example.attendanceapimono.application.UserService
import com.example.attendanceapimono.application.dto.user.CreateUserRequest
import com.example.attendanceapimono.application.dto.user.SignInRequest
import com.example.attendanceapimono.application.dto.user.TokenResponse
import com.example.attendanceapimono.application.exception.handleValidationCatch
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class UserController(private val userService: UserService) : UserAPI {
    override suspend fun createUser(body: Mono<CreateUserRequest>) {
        body.handleValidationCatch()
            .map(userService::createUser)
            .awaitSingle()
    }

    override suspend fun signIn(body: Mono<SignInRequest>): TokenResponse {
         return body.handleValidationCatch()
             .map(userService::getUserBySocialToken)
             .awaitSingle()
    }


    //TODO need delete
    @Operation(
        summary = "엑세스 토큰 테스트",
        description = "엑세스 토큰 테스트 및 컨트롤러 파라미터 user id 인자로 넣어주는거 테스트",
    )
    @SecurityRequirement(name = "signedTokenV1")
    @ApiResponse(
        responseCode = "200",
        description = "토큰 테스트 성공",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(type = "string", format = "uuid"),
                examples = [ExampleObject("\"aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee\"")]
            )
        ],
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test-principal")
    fun testLoginUserID(@LoginUserID id: UUID): ResponseEntity<String> {
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body("\"$id\"")
    }
}

/*
 [{"error_description": "Invalid Value"}]
* */