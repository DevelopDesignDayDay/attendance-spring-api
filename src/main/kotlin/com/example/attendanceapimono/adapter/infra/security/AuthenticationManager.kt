package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.adapter.infra.security.TokenProvider
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Component
class AuthenticationManager(private val tokenProvider: TokenProvider)
    : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.justOrEmpty(
            tokenProvider
                .getAuthentication(authentication.credentials.toString())
        )
    }
}