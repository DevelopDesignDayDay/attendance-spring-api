package com.example.attendanceapimono.application.dto.event

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(
    title = "이벤트 등록 후 반환",
    description = "이벤트를 등록한 후, 반환해주는 Response에 대한 DTO 입니다.",
    example = CreateEventResponse.Example,
)
data class CreateEventResponse (
    @Schema(description = "생성된 event")
    val eventId: UUID,
) {
    companion object {
        const val Example = """
            {
                "id": "123e4567-e89b-12d3-a456-426614174000"
            }
        """
    }
}