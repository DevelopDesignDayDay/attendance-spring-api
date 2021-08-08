package com.example.attendanceapimono.adapter.infra.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(
    private val authenticationManager: AuthenticationManager) : ServerSecurityContextRepository {

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        return Mono
            .justOrEmpty(exchange
                .request
                .headers
                .getFirst(HttpHeaders.AUTHORIZATION))
            .filter { it.startsWith(SecurityConst.BEARER) }
            .flatMap {
                val token = it.substring(SecurityConst.BEARER.length)
                val auth = UsernamePasswordAuthenticationToken(null, token)
                authenticationManager
                    .authenticate(auth)
                    .map { wrapAuth -> SecurityContextImpl(wrapAuth) }
            }
    }
}