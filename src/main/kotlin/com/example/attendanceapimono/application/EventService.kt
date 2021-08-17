package com.example.attendanceapimono.application

import com.example.attendanceapimono.application.dto.event.CreateEventRequest
import com.example.attendanceapimono.application.dto.event.CreateEventResponse
import com.example.attendanceapimono.application.dto.event.GetEventListResponse
import com.example.attendanceapimono.domain.event.EventRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService (
  private val eventRepository: EventRepository
) {
    @Transactional
    fun createEvent(dto: CreateEventRequest): CreateEventResponse = runBlocking {
        val event = dto.entity()

        eventRepository.save(event)

        CreateEventResponse(eventId = event.id)
    }

    @Transactional
    fun getEventList(): GetEventListResponse = runBlocking {
        val data = eventRepository.findAll()
        val count = eventRepository.count()

        GetEventListResponse(data, count)
    }
}