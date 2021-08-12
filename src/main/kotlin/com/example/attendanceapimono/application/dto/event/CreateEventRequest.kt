package com.example.attendanceapimono.application.dto.event

import com.example.attendanceapimono.domain.event.Event
import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import org.springframework.beans.factory.annotation.Value
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

@Schema(
    title = "이벤트 등록 요청",
    description = "새로운 이벤트를 등록할 때 필요한 Body에 대한 DTO입니다.",
    example = CreateEventRequest.Example
)
class CreateEventRequest(
    @Schema(description = "DDD 기수 입니다.")
    val generationID: Int,

    @Schema(description = "이벤트 명")
    @field:Length(min = 5, max = 255)
    val title: String,

    @Schema(description = "이벤트 설명")
    @field:Length(max = 500)
    val description: String,

    @Schema(description = "예상 시작 일")
    @field:DateTimeFormat
    val expectedAt: String,

    @Value("\${event.late-diff-minute}")
    @Schema(description = "지각 기준 시간 (분)")
    val lateDiffMinute: Int,

    @Value("\${event.absent-diff-minute}")
    @Schema(description = "결석 기준 시간 (분)")
    val absentDiffMinute: Int
) {
    fun entity() = Event (
        id = UUID.randomUUID(),
        generationID = this.generationID,
        title = this.title,
        description = this.description,
        expectedAt = LocalDateTime.parse(this.expectedAt),
        startAt = LocalDateTime.parse(this.expectedAt),
        lateDiffMinutes = this.lateDiffMinute,
        absentDiffMinutes = this.absentDiffMinute,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    companion object {
        const val Example = """
            {
                "generationID": 6,
                "title": "오리엔테이션",
                "description": "함께 모여 앞으로의 방향에 대해 이야기 나눠 보아요.",
                "expectedAt": "2021-08-21T14:00:00.000",
                "lateDiffMinute": 10,
                "absentDiffMinute": 60
            }
        """
    }
}