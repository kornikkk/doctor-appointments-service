package pl.kornikkk.doctorappointments.application

import pl.kornikkk.doctorappointments.domain.Patient

fun Patient.toDto() = PatientDto(
        personId.toString(),
        firstName,
        lastName,
        address
)