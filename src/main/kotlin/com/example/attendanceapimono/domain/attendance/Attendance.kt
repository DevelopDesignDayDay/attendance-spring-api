package com.example.attendanceapimono.domain.attendance

import com.example.attendanceapimono.domain.event.Event
import com.example.attendanceapimono.domain.user.User
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "attendances")
class Attendance(
    @Id
    @Type(type="uuid-char")
    @Column(columnDefinition = "CHAR(36)")
    val id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // user.id
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    var event: Event,

    @Column(nullable = false)
    val createdAt: LocalDateTime
)