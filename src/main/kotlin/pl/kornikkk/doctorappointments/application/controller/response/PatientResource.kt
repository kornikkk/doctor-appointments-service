package pl.kornikkk.doctorappointments.application.controller.response

import java.util.*

data class PatientResource(
        var id: UUID,
        var firstName: String,
        var lastName: String,
        var address: String
)