package pl.kornikkk.doctorappointments.domain

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*

class Appointment(val id: UUID?, val patientId: UUID, val doctorId: UUID, val location: String, dateTime: LocalDateTime) {

    private var _dateTime = dateTime.truncatedTo(ChronoUnit.MINUTES)
    val dateTime: LocalDateTime get() = _dateTime

    constructor(patientId: UUID, doctorId: UUID, location: String, dateTime: LocalDateTime) :
            this(null, patientId, doctorId, location, dateTime)

    fun reschedule(time: LocalTime) {
        _dateTime = dateTime.with(time.truncatedTo(ChronoUnit.MINUTES))
    }
}