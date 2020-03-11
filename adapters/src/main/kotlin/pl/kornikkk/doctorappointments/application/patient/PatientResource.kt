package pl.kornikkk.doctorappointments.application.patient

import org.springframework.hateoas.RepresentationModel
import pl.kornikkk.doctorappointments.domain.patient.Address
import java.util.*

open class PatientResource(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val address: Address
) : RepresentationModel<PatientResource>()