package pl.kornikkk.doctorappointments.domain.exception

import java.util.*

class PatientNotFoundException(id: UUID) : Exception("Patient with id \"${id}\"not found") {
}