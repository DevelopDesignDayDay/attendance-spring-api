package com.example.attendanceapimono.application.dto.event

import com.example.attendanceapimono.domain.event.Event
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "이벤트 리스트")
class EventList(
    @Schema(description = "이벤트 리스트의 데이터입니다.")
    val data: List<Event>,

    @Schema(description = "이벤트의 전체 개수입니다.")
    val count: Int
)

@Schema(
    title = "이벤트 리스트 가져오기",
    description = "이벤트 리스트를 가져왔을 때 Response에 대한 DTO입니다.",
    example = GetEventListResponse.Example
)
class GetEventListResponse (
    private val data: List<Event>,
    private val count: Int
) {
    @Schema(description = "이벤트 리스트")
    val events = EventList(data, count)

    companion object {
        const val Example = """
            {
                "events": {
                    "count": 13,
                    "data": [
                        {
                            "id": "123e4567-e89b-12d3-a456-426614174000",
                            "generationID": 6,
                            "title": "오리엔테이션",
                            "description": "함께 모여 앞으로의 방향에 대해 이야기 나눠 보아요.",
                            "isDone": true,
                            "expectedAt": "2021-08-21T14:00:00.000",
                            "lateDiffMinute": 10,
                            "absentDiffMinute": 60,
                            "startAt": "2021-08-21T14:03:24.247",
                            "createdAt": "2021-08-16T11:24:25.243",
                            "updatedAt": "2021-08-16T11:24:25.243"
                        },
                        {
                            "id": "123e4567-e89b-12d3-a456-426614174000",
                            "generationID": 6,
                            "title": "오리엔테이션",
                            "description": "함께 모여 앞으로의 방향에 대해 이야기 나눠 보아요.",
                            "isDone": true,
                            "expectedAt": "2021-08-21T14:00:00.000",
                            "lateDiffMinute": 10,
                            "absentDiffMinute": 60,
                            "startAt": "2021-08-21T14:03:24.247",
                            "createdAt": "2021-08-16T11:24:25.243",
                            "updatedAt": "2021-08-16T11:24:25.243"
                        }
                    ]
                }
            }
        """
    }
}