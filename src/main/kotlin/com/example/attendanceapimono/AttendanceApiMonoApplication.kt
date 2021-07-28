package com.example.attendanceapimono

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@EnableFeignClients
@SpringBootApplication
class AttendanceApiMonoApplication

fun main(args: Array<String>) {
    runApplication<AttendanceApiMonoApplication>(*args)
}
