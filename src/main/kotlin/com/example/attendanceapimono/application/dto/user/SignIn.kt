package com.example.attendanceapimono.application.dto.user

import com.example.attendanceapimono.domain.user.*
import io.swagger.v3.oas.annotations.media.Schema
import lombok.extern.slf4j.Slf4j
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Schema(
    title = "로그인",
    description = "",
    example = SignIn.Example,
)
data class SignIn(
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

@Schema(
    title = "SignIn Token Response",
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