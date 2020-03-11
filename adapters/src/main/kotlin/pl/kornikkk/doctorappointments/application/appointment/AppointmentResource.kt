package pl.kornikkk.doctorappointments.application.appointment

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

open class AppointmentResource(
        val id: UUID,
        val patient: AppointmentPatientResource,
        val doctor: AppointmentDoctorResource,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val date: LocalDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        val time: LocalTime
) : RepresentationModel<AppointmentResource>()