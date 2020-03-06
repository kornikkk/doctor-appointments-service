package pl.kornikkk.doctorappointments.application.controller.request

data class ModifyPatientRequest(
        val firstName: String?,
        val lastName: String?,
        val address: String?
)