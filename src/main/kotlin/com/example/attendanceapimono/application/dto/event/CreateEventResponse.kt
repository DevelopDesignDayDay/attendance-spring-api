package com.example.attendanceapimono.application.dto.event

import com.example.attendanceapimono.domain.event.Event
import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    title = "이벤트 등록 후 반환",
    description = "이벤트를 등록한 후, 반환해주는 Response에 대한 DTO 입니다.",
    example = CreateEventResponse.Example,
)
data class CreateEventResponse (
    @Schema(description = "생성된 event")
    val event: Event,
) {
    companion object {
        const val Example = """
            {
                "id": "123e4567-e89b-12d3-a456-426614174000",
                "generationID": 6,
                "title": "오리엔테이션",
                "description": "함께 모여 앞으로의 방향에 대해 이야기 나눠 보아요.",
                "isDone": false,
                "expectedAt": "2021-08-21T14:00:00.000",
                "lateDiffMinute": 10,
                "absentDiffMinute": 60,
                "startAt": null,
                "createdAt": "2021-08-12T22:13:47.245",
                "updatedAt": "2021-08-12T22:13:47.245"
            }
        """
    }
}