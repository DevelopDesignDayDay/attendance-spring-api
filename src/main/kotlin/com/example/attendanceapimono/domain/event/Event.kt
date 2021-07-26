package com.example.attendanceapimono.domain.event

import com.example.attendanceapimono.domain.attendance.Attendance
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "events")
class Event(
    @Id
    @Type(type="uuid-char")
    @Column(columnDefinition = "CHAR(36)")
    val id: UUID,

    @Column(nullable = false)
    var generationID: Int,

    @Column(length = 255, nullable = false)
    var title: String,

    @Column(length = 500, nullable = false)
    var description: String,

    @Column(nullable = false)
    var isDone: Boolean,

    @Column(nullable = false)
    val expectedAt: LocalDateTime,

    @Column(nullable = false)
    var lateDiffMinutes: Int,

    @Column(nullable = false)
    var absentDiffMinutes: Int,

    @Column(nullable = false)
    val startAt: LocalDateTime,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @Column(nullable = false)
    val updatedAt: LocalDateTime,

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    val attendances: List<Attendance> = arrayListOf()
)