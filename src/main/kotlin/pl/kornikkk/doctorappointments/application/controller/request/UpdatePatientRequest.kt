package pl.kornikkk.doctorappointments.application.controller.request

data class UpdatePatientRequest(
        val firstName: String,
        val lastName: String,
        val address: String
)