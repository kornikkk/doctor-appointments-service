package pl.kornikkk.doctorappointments.application.patient

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import pl.kornikkk.doctorappointments.application.doctor.DoctorRestController
import pl.kornikkk.doctorappointments.domain.patient.Patient

fun Patient.toResource(): PatientResource = PatientResource(id!!, firstName, lastName, address)
        .add(linkTo(methodOn(DoctorRestController::class.java).findById(id!!)).withSelfRel())

fun Patient.update(request: UpdatePatientRequest): Patient {
    firstName = request.firstName
    lastName = request.lastName
    address = request.address
    return this
}