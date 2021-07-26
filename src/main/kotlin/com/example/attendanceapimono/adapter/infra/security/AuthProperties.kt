package com.example.attendanceapimono.adapter.infra.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class AuthProperties (val secret: String, val expires: Long);