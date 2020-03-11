package pl.kornikkk.doctorappointments.application.appointment

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import pl.kornikkk.doctorappointments.application.doctor.DoctorRestController
import pl.kornikkk.doctorappointments.application.patient.PatientRestController
import pl.kornikkk.doctorappointments.domain.appointment.Appointment
import java.util.*

fun Appointment.toResource(): AppointmentResource =
        AppointmentResource(
                id!!,
                toAppointmentPatientResource(patientId),
                toAppointmentDoctorResource(doctorId),
                date,
                time
        ).add(linkTo(methodOn(AppointmentRestController::class.java).findById(id!!)).withSelfRel())

private fun toAppointmentPatientResource(patientId: UUID): AppointmentPatientResource = AppointmentPatientResource(patientId)
        .add(linkTo(methodOn(PatientRestController::class.java).findById(patientId)).withSelfRel())

private fun toAppointmentDoctorResource(doctorId: UUID): AppointmentDoctorResource = AppointmentDoctorResource(doctorId)
        .add(linkTo(methodOn(DoctorRestController::class.java).findById(doctorId)).withSelfRel())
