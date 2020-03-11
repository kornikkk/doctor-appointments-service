package pl.kornikkk.doctorappointments.domain.appointment

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*

class Appointment(val id: UUID?, val patientId: UUID, val doctorId: UUID, val location: String, val date: LocalDate, time: LocalTime) {

    private var _time = truncateTime(time)
    val time: LocalTime get() = _time

    val dateTime: LocalDateTime get() = LocalDateTime.of(date, time)


    constructor(patientId: UUID, doctorId: UUID, location: String, date: LocalDate, time: LocalTime) :
            this(null, patientId, doctorId, location, date, time)

    fun reschedule(time: LocalTime) {
        _time = truncateTime(time)
    }

    private fun truncateTime(time: LocalTime) = time.truncatedTo(ChronoUnit.MINUTES)
}