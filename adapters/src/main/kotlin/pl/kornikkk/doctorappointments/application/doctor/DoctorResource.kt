package pl.kornikkk.doctorappointments.application.doctor

import java.util.*

data class DoctorResource(
        val id: UUID,
        val firstName: String,
        val lastName: String
)