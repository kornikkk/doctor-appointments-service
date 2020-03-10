package pl.kornikkk.doctorappointments.application.controller

import pl.kornikkk.doctorappointments.application.controller.request.UpdateDoctorRequest
import pl.kornikkk.doctorappointments.application.controller.response.DoctorResource
import pl.kornikkk.doctorappointments.domain.Doctor

fun Doctor.toResource() = DoctorResource(id!!, firstName, lastName)

fun Doctor.update(request: UpdateDoctorRequest): Doctor {
    firstName = request.firstName
    lastName = request.lastName
    return this
}