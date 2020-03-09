package pl.kornikkk.doctorappointments.application.controller.response

import java.util.*

data class DoctorResource(
        var id: UUID,
        var firstName: String,
        var lastName: String
)