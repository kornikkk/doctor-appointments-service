package pl.kornikkk.doctorappointments.domain.patient

import java.util.*

class PatientNotFoundException(id: UUID) : Exception("Patient with id \"${id}\" not found") {
}