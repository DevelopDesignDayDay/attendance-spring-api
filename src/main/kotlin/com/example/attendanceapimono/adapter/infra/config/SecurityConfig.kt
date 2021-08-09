package com.example.attendanceapimono.adapter.infra.config

import com.example.attendanceapimono.adapter.infra.security.AuthenticationManager
import com.example.attendanceapimono.adapter.infra.security.PermitSet
import com.example.attendanceapimono.adapter.infra.security.SecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig(
    private val authenticationManager: AuthenticationManager,
    private val securityContextRepository: SecurityContextRepository,
) {

    private val permitAllUrls = listOf(
        PermitSet(HttpMethod.GET, "/favicon.ico"),

        PermitSet(HttpMethod.POST, "/user"),
        PermitSet(HttpMethod.POST, "/user/login"),

        // Swagger
        PermitSet(HttpMethod.GET, "/swagger-resources/**"),
        PermitSet(HttpMethod.GET, "/webjars/**"),
        PermitSet(HttpMethod.GET, "/v3/api-docs/**"),
        PermitSet(HttpMethod.GET, "/doc"),
        PermitSet(HttpMethod.GET, "/swagger-ui/**"),
    )

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        return http.cors().and()
            .csrf().disable()
            .headers().frameOptions().disable().and()
            .formLogin().disable()
            .httpBasic().disable()
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS).permitAll()
            .processPermitAll()
            .anyExchange().authenticated()
            .and()
            .build()
    }

    private fun ServerHttpSecurity.AuthorizeExchangeSpec.processPermitAll():
            ServerHttpSecurity.AuthorizeExchangeSpec {
        var last = this
        permitAllUrls.forEach {
            last = last.pathMatchers(it.method, it.path).permitAll()
        }
        return last
    }
}