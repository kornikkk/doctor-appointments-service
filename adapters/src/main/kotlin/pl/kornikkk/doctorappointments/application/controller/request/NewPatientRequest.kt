package pl.kornikkk.doctorappointments.application.controller.request

data class NewPatientRequest(
        val firstName: String,
        val lastName: String,
        val address: String
)