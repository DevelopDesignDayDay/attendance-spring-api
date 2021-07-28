package com.example.attendanceapimono.domain.user

interface SocialInfo {
    val id: String
    val type: SocialType
    val email: String
    val thumb: String?
}