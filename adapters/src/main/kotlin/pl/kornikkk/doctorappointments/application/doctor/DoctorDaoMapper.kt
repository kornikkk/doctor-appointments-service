package pl.kornikkk.doctorappointments.application.doctor

import pl.kornikkk.doctorappointments.domain.doctor.Doctor

fun Doctor.toResource() = DoctorResource(id!!, firstName, lastName)

fun Doctor.update(request: UpdateDoctorRequest): Doctor {
    firstName = request.firstName
    lastName = request.lastName
    return this
}