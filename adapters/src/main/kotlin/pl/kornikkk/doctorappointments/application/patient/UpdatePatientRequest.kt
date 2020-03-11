package pl.kornikkk.doctorappointments.application.patient

import pl.kornikkk.doctorappointments.domain.patient.Address

data class UpdatePatientRequest(
        val firstName: String,
        val lastName: String,
        val address: Address
)