package pl.kornikkk.doctorappointments.application.controller

import pl.kornikkk.doctorappointments.application.controller.request.UpdatePatientRequest
import pl.kornikkk.doctorappointments.application.controller.response.PatientResource
import pl.kornikkk.doctorappointments.domain.Patient

fun Patient.toResource() = PatientResource(id!!, firstName, lastName, address)

fun Patient.update(request: UpdatePatientRequest): Patient {
    firstName = request.firstName
    lastName = request.lastName
    address = request.address
    return this
}