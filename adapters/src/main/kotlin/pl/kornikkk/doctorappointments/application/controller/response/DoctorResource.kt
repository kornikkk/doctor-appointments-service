package pl.kornikkk.doctorappointments.application.controller.response

import java.util.*

data class DoctorResource(
        val id: UUID,
        val firstName: String,
        val lastName: String
)