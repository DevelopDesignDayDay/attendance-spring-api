package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.adapter.present.LoginUserID
import org.springframework.core.MethodParameter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoginUserIdArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter) = parameter.hasParameterAnnotation(LoginUserID::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any> = exchange
        .getPrincipal<UsernamePasswordAuthenticationToken>()
        .map { it.principal as UserPrincipal }
        .map { it.id }
}