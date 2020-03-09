package pl.kornikkk.doctorappointments.application.controller.response

import java.util.*

data class PatientResource(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val address: String
)