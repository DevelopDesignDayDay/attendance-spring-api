package com.example.attendanceapimono.adapter.infra.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "googleAuth", url = "https://www.googleapis.com")
interface GoogleAuthClient {
    @GetMapping("/oauth2/v3/tokeninfo")
    fun getByToken(@RequestParam("id_token") idToken: String): GoogleSocialInfo
}
