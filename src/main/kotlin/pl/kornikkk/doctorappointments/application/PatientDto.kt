package pl.kornikkk.doctorappointments.application

data class PatientDto(
        var personId: String,
        var firstName: String,
        var lastName: String,
        var address: String
)