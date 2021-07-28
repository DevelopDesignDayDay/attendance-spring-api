package com.example.attendanceapimono.adapter.infra.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import feign.*
import feign.codec.Decoder
import feign.codec.Encoder
import feign.codec.ErrorDecoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.http.HttpStatus
import java.util.*


@Configuration
class FeignDefaultConfig {

    @Bean
    fun decoder(): Decoder {
        return JacksonDecoder(jacksonObjectMapper())
    }

    @Bean
    fun encoder(): Encoder {
        return JacksonEncoder(jacksonObjectMapper())
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun retryer() = Retryer.Default()

    @Bean
    fun localDateFeignFormatterRegister(): FeignFormatterRegistrar? {
        return FeignFormatterRegistrar { registry: FormatterRegistry ->
            val registrar = DateTimeFormatterRegistrar()
            registrar.setUseIsoFormat(true)
            registrar.registerFormatters(registry)
        }
    }

//    @Bean
//    fun decoder(): ErrorDecoder { todo implements
//        return ErrorDecoder { methodKey, response ->
//
//        }
//    }
}