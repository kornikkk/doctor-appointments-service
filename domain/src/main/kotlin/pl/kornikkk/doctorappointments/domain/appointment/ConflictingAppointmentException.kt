package pl.kornikkk.doctorappointments.domain.appointment

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ISO_DATE_TIME
import java.util.*

class ConflictingAppointmentException(patientId: UUID, doctorId: UUID, date: LocalDate, time: LocalTime)
    : Exception("Appointment for patient with id \"$patientId\" at doctor with id \"$doctorId\" at ${ISO_DATE_TIME.format(LocalDateTime.of(date, time))} would be conflicting another appointment")