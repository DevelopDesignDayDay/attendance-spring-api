package com.example.attendanceapimono.application.dto.user

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    title = "토큰 새로 요청",
    description = "Resign(사임)이 아닌, re-sign 을 의미함",
    example = ReSignResponse.Example,
)
data class ReSignResponse(
    @Schema(description = "JWT 토큰")
    val token: String,

    @Schema(description = "매니저 권한이 있는지에 대한 필드")
    val isManager: Boolean,
) {
    companion object {
        const val Example = """
            {
                "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
                "isManager": true
            }
        """
    }
}