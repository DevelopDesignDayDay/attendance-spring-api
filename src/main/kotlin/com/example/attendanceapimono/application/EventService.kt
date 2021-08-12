package com.example.attendanceapimono.application

import com.example.attendanceapimono.application.dto.event.CreateEventRequest
import com.example.attendanceapimono.application.dto.event.CreateEventResponse
import com.example.attendanceapimono.domain.event.EventRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class EventService (
  private val eventRepository: EventRepository
) {
    fun createEvent(dto: CreateEventRequest): CreateEventResponse = runBlocking {
        val event = dto.entity()

        eventRepository.save(event)

        CreateEventResponse(event)
    }
}