package com.example.attendanceapimono.adapter.infra.security

import org.springframework.http.HttpMethod

data class PermitSet(
    val method: HttpMethod,
    val path: String
)
