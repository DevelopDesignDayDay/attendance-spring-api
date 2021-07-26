package com.example.attendanceapimono.domain.user

import java.io.Serializable
import javax.persistence.*

enum class SocialType {
    GOOGLE
}

@Entity
@Table(name = "social_providers")
@IdClass(SocialProviderID::class)
class SocialProvider(

    @Id
    @Column(length = 30, nullable = false)
    val id: String,

    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    val type: SocialType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // user.id
    val user: User,
)


data class SocialProviderID(
    val id: String,
    val type: SocialType,
) : Serializable
