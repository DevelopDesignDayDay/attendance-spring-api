package com.example.attendanceapimono.domain.news

import java.time.LocalDateTime
import javax.persistence.*

enum class NewsType {
    FACEBOOK, MEDIUM, IN_APP, YOUTUBE,INSTAGRAM
    // 추가 필요
}

@Entity
@Table(name = "news")
class News(
    @Id
    @GeneratedValue
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    val type: NewsType,

    @Column(length = 255, nullable = false)
    val title: String,

    @Column(length = 500, nullable = false)
    val description: String,

    @Column(length = 500, nullable = false)
    val createdAt: LocalDateTime,

    @Column(length = 2048)
    val link: String?,

    @Column(name = "android_deep_link", length = 2048)
    val androidDeepLink: String?,

    @Column(name = "ios_deep_link", length = 2048)
    val iosDeepLink: String?,
)