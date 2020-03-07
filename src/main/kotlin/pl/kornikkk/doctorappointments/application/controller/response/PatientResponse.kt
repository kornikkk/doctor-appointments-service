package pl.kornikkk.doctorappointments.application.controller.response

import java.util.*

data class PatientResponse(
        var personId: UUID,
        var firstName: String,
        var lastName: String,
        var address: String
)