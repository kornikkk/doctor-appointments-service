package pl.kornikkk.doctorappointments.application.patient

import pl.kornikkk.doctorappointments.domain.patient.Address

data class NewPatientRequest(
        val firstName: String,
        val lastName: String,
        val address: Address
)