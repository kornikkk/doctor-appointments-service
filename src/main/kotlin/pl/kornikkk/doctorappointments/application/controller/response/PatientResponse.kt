package pl.kornikkk.doctorappointments.application.controller.response

data class PatientResponse(
        var personId: String,
        var firstName: String,
        var lastName: String,
        var address: String
)