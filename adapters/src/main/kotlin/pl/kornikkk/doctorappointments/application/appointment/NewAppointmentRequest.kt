package pl.kornikkk.doctorappointments.application.appointment

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class NewAppointmentRequest(
        val patientId: UUID,
        val doctorId: UUID,
        val location: String,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val date: LocalDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        val time: LocalTime
)