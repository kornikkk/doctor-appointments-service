package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Patient

fun PatientEntity.toDomain() = Patient(
        id,
        firstName,
        lastName,
        address
)

fun Patient.toEntity() = PatientEntity(
        id,
        firstName,
        lastName,
        address
)