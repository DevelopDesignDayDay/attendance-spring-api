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

    @Column(name = "generation_id", nullable = false)
    var generationID: Int,

    @Column(length = 255, nullable = false)
    var title: String,

    @Column(length = 500, nullable = false)
    var description: String = "",

    @Column(nullable = false)
    var isDone: Boolean = false,

    @Column(nullable = false)
    val expectedAt: LocalDateTime,

    @Column(name = "late_diff_min", nullable = false)
    var lateDiffMinutes: Int,

    @Column(name = "absent_diff_min", nullable = false)
    var absentDiffMinutes: Int,

    @Column(nullable = false)
    var startAt: LocalDateTime,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @Column(nullable = false)
    val updatedAt: LocalDateTime,

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    val attendances: List<Attendance> = arrayListOf()
)