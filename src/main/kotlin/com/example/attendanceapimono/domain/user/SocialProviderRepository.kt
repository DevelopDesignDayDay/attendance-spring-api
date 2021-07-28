package com.example.attendanceapimono.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SocialProviderRepository : JpaRepository<SocialProvider, SocialProviderID>