package pl.kornikkk.doctorappointments.domain.doctor

import java.util.*

class DoctorNotFoundException(id: UUID) : Exception("Doctor with id \"${id}\"not found") {
}