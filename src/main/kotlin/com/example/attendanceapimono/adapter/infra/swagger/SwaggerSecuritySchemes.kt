package com.example.attendanceapimono.adapter.infra.swagger

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes


@SecuritySchemes(
    value = [
        SecurityScheme(
            name = "signedTokenV1",
            paramName = "Authorization",
            `in` = SecuritySchemeIn.HEADER,
            type = SecuritySchemeType.HTTP,
            scheme = "Bearer",
        )
    ]
)
object SwaggerSecuritySchemes