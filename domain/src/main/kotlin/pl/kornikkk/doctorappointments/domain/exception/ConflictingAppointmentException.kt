package pl.kornikkk.doctorappointments.domain.exception

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_DATE_TIME
import java.util.*

class ConflictingAppointmentException(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime)
    : Exception("Appointment for patient with id \"$patientId\" at doctor with id \"$doctorId\" at ${ISO_DATE_TIME.format(dateTime)} would be conflicting another appointment")