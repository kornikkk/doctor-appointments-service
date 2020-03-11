package pl.kornikkk.doctorappointments.application.patient

import pl.kornikkk.doctorappointments.domain.patient.Address
import java.util.*

data class PatientResource(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val address: Address
)