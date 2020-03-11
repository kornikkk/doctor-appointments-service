package pl.kornikkk.doctorappointments.application.doctor

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import pl.kornikkk.doctorappointments.domain.doctor.Doctor

fun Doctor.toResource(): DoctorResource = DoctorResource(id!!, firstName, lastName)
        .add(linkTo(methodOn(DoctorRestController::class.java).findById(id!!)).withSelfRel())

fun Doctor.update(request: UpdateDoctorRequest): Doctor {
    firstName = request.firstName
    lastName = request.lastName
    specialization = request.specialization
    return this
}