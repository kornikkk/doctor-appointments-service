package pl.kornikkk.doctorappointments.application.doctor

import org.springframework.hateoas.RepresentationModel
import java.util.*

open class DoctorResource(
        val id: UUID,
        val firstName: String,
        val lastName: String
) : RepresentationModel<DoctorResource>()