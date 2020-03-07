package pl.kornikkk.doctorappointments.domain.exception

import java.util.*

class DoctorNotFoundException(id: UUID) : Exception("Doctor with id \"${id}\"not found") {
}