package com.example.attendanceapimono.application.dto.user

import com.example.attendanceapimono.domain.user.*
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

@Schema(
    title = "로그인",
    description = "",
    example = SignInRequest.Example,
)
data class SignInRequest(
    @Schema(description = "소셜 토큰")
    @field:NotEmpty
    val token: String,

    @Schema(description = "소설 타입")
    val type: SocialType,
) {

    companion object {
        const val Example = """
            {
                "token": "social_token",
                "type": "GOOGLE"
            }
        """
    }
}

