package com.example.attendanceapimono.adapter.infra.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
class AuthProperties (@Value("\${jwt.secret}")val secret: String, @Value("\${jwt.expires}") val expires: Long);