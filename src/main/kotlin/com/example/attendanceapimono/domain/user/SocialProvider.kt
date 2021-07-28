package com.example.attendanceapimono.domain.user

import java.io.Serializable
import javax.persistence.*

enum class SocialType {
    GOOGLE, APPLE
}

@Entity
@Table(name = "social_providers")
class SocialProvider(
    @EmbeddedId
    val id: SocialProviderID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // user.id
    val user: User,
)


@Embeddable
class SocialProviderID(
    @Column(name = "id", length = 30, nullable = false)
    val id: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 10, nullable = false)
    val type: SocialType,
) : Serializable