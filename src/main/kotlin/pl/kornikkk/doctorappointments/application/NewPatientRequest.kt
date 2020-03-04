package pl.kornikkk.doctorappointments.application

data class NewPatientRequest(
        var firstName: String,
        var lastName: String,
        var address: String
)