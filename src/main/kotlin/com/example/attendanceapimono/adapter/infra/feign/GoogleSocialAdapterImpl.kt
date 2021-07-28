package com.example.attendanceapimono.adapter.infra.feign

import com.example.attendanceapimono.domain.user.SocialAdapter
import com.example.attendanceapimono.domain.user.SocialInfo
import com.example.attendanceapimono.domain.user.SocialType
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import feign.QueryMap
import feign.RequestLine
import kotlinx.coroutines.flow.Flow
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import javax.inject.Qualifier


@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleSocialInfo(
    val sub: String,
    override val email: String,
    val picture: String,
) : SocialInfo {
    override val id: String get() = this.sub
    override val type: SocialType get() = SocialType.GOOGLE
    override val thumb: String get() = this.picture
}

@Component("googleAdapter")
class GoogleSocialAdapterImpl(private val auth: GoogleAuthClient) : SocialAdapter {
    override fun findByToken(token: String): SocialInfo {
        return auth.getByToken(token)
    }
}