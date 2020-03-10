package pl.kornikkk.doctorappointments.domain.exception

import java.util.*

class AppointmentNotFoundException(id: UUID) : Exception("Appointment with id \"${id}\"not found") {
}