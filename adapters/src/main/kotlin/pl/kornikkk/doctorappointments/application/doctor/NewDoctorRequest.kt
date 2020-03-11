package pl.kornikkk.doctorappointments.application.doctor

data class NewDoctorRequest(
        val firstName: String,
        val lastName: String,
        val specialization: String
)