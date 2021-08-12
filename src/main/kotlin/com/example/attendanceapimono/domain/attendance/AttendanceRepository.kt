package com.example.attendanceapimono.domain.attendance

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AttendanceRepository : JpaRepository<Attendance, UUID>