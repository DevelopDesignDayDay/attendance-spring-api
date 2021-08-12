package com.example.attendanceapimono.application.dto.attendace

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(
    title = "QR Code 요청",
    example = QRCodeResponse.Example,
)
data class QRCodeResponse(
    @Schema(description = "QR Code 문자열")
    val code: String,
) {
    companion object {
        const val Example = """
            {
                "code": "7JWELOydvO2VmOq4sOyLq+uLpC4uLg=="
            }
        """
    }
}

data class QRCodeValue(
    val userID: UUID
)