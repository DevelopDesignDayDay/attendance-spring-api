package com.example.attendanceapimono.application

import com.example.attendanceapimono.domain.attendance.AttendanceRepository
import com.example.attendanceapimono.domain.event.EventRepository
import org.springframework.stereotype.Service

@Service
class AttendanceService(
    private val eventRepository: EventRepository,
    private val attendanceRepository: AttendanceRepository,
) {

}