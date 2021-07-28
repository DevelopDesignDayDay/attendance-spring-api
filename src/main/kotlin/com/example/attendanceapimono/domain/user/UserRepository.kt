package com.example.attendanceapimono.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface  UserRepository : JpaRepository<User, UUID>