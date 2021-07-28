package com.example.attendanceapimono.domain.user

interface SocialAdapter {
    fun findByToken(token: String): SocialInfo
}