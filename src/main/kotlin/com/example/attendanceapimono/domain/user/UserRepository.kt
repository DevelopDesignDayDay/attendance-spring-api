package com.example.attendanceapimono.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UUID, User> {
}