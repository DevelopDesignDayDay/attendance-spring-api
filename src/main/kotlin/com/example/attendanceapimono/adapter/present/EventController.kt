package com.example.attendanceapimono.adapter.present

import com.example.attendanceapimono.adapter.present.api.EventAPI
import com.example.attendanceapimono.application.EventService
import com.example.attendanceapimono.application.dto.event.CreateEventRequest
import com.example.attendanceapimono.application.dto.event.CreateEventResponse
import com.example.attendanceapimono.application.dto.event.GetEventListResponse
import com.example.attendanceapimono.application.exception.handleValidationCatch
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class EventController (private val eventService: EventService): EventAPI {
    override suspend fun createEvent(body: Mono<CreateEventRequest>): CreateEventResponse {
        return body.handleValidationCatch()
            .map(eventService::createEvent)
            .awaitSingle()
    }

    override suspend fun getEventList(): GetEventListResponse {
        return eventService.getEventList()
    }
}