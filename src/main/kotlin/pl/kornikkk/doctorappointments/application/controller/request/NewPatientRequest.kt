package pl.kornikkk.doctorappointments.application.controller.request

data class NewPatientRequest(
        var firstName: String,
        var lastName: String,
        var address: String
)