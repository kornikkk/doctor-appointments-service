package pl.kornikkk.doctorappointments.application.appointment

import org.springframework.hateoas.RepresentationModel
import java.util.*

open class AppointmentDoctorResource(val id: UUID) : RepresentationModel<AppointmentDoctorResource>()