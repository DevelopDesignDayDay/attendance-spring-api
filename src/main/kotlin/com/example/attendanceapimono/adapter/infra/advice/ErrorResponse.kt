package com.example.attendanceapimono.adapter.infra.advice

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema


@Schema(
    title = "에러 모델",
    description = "에러 처리를 위한 기본 모델",
    example = ErrorResponse.ExampleFull
)
data class ErrorResponse(
    @Schema(description = "에러 메세지")
    val errorMsg: String,

    @Schema(description = "에러 코드", nullable = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val errorCode: Int? = null,
) {
    companion object {
        const val ExampleFull = """
            {
                "errorMsg": "string",
                "errorCode": 0
            }
        """

        const val ExampleOnlyMessage = """
            {
                "errorMsg": "string"
            }
        """
    }
}

