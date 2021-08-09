package com.example.attendanceapimono.application.dto.user

import com.example.attendanceapimono.domain.user.*
import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Schema(
    title = "회원가입",
    description = "",
    example = CreateUserRequest.Example,
)
data class CreateUserRequest(
    @Schema(description = "소셜 토큰")
    @field:NotEmpty
    val token: String,

    @Schema(description = "소설 타입")
    val type: SocialType,
    
    @Schema(description = "이메일 주소")
    @field:Email
    val email: String,

    @Schema(description = "이름(성)")
    @field:Length(min = 1, max = 20)
    val lastName: String,

    @Schema(description = "이름")
    @field:Length(min = 1, max = 20)
    val firstName: String,

    @Schema(description = "직군 포지션")
    val position: UserPosition,
) {
    fun entity() = User(
        id = UUID.randomUUID(),
        state = UserState.NORMAL,
        role = UserRole.MEMBER,
        position = this.position,
        generationID = 6, // TODO 별도의 제네레이션 테이블로 처리 하는게 좋을 듯함
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )

    companion object {
        const val Example = """
            {
                "token": "social_token",
                "type": "GOOGLE",
                "email": "dddstudy1@gmail.com",
                "lastName": "이",
                "firstName": "재성",
                "position": "BACKEND"
            }
        """
    }
}