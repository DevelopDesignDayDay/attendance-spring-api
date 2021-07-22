package com.example.attendanceapimono.domain.user

import javax.persistence.*

enum class SocialType {
    GOOGLE
}

@Entity
@Table(name = "social_providers")
class SocialProvider(
    @Id
    @Column(length = 30)
    val id: String,

    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    val type: SocialType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id") // user.id
    val user: User?,
)