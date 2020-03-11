package pl.kornikkk.doctorappointments.domain.appointment

import java.util.*

class AppointmentNotFoundException(id: UUID) : Exception("Appointment with id \"${id}\" not found") {
}