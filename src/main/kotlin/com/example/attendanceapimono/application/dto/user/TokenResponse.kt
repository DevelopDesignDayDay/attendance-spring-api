package com.example.attendanceapimono.application.dto.user

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    title = "로그인 성공, 엑세스 토큰",
    description = "",
    example = TokenResponse.Example,
)
data class TokenResponse(
    @Schema(description = "JWT 토큰")
    val token: String,
) {
    companion object {
        const val Example = """
            {
                "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
            }
        """
    }
}