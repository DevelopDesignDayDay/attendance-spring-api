package com.example.attendanceapimono.application.dto.event

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    title = "이벤트 등록",
    description = "새로운 이벤트를 등록합니다.",
    example = CreateEventRequest.Example
)
class CreateEventRequest {
    companion object {
        const val Example = """
            {
                "generationId": 6,
                "title": "오리엔테이션",
                "description": "함께 모여 앞으로의 방향에 대해 이야기 나눠 보아요.",
                "expectedAt": "2021-08-21T14:00:00.000",
                "lateDiffMinute": 10,
                "absentDiffMinute": 60,
            }
        """
    }
}