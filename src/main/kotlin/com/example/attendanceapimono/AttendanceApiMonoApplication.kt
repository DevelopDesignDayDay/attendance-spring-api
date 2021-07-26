package com.example.attendanceapimono

import com.example.attendanceapimono.adapter.infra.security.AuthProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


//@EnableConfigurationProperties(AuthProperties::class)
@EnableFeignClients
@SpringBootApplication
class AttendanceApiMonoApplication

fun main(args: Array<String>) {
    runApplication<AttendanceApiMonoApplication>(*args)
}
