package pl.kornikkk.doctorappointments.application.appointment

import org.springframework.hateoas.RepresentationModel
import java.util.*

open class AppointmentPatientResource(val id: UUID) : RepresentationModel<AppointmentPatientResource>()