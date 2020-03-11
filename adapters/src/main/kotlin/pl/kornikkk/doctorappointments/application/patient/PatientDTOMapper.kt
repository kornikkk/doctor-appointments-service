package pl.kornikkk.doctorappointments.application.patient

import pl.kornikkk.doctorappointments.domain.patient.Patient

fun Patient.toResource() = PatientResource(id!!, firstName, lastName, address)

fun Patient.update(request: UpdatePatientRequest): Patient {
    firstName = request.firstName
    lastName = request.lastName
    address = request.address
    return this
}