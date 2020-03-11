package pl.kornikkk.doctorappointments.infrastructure.patient

import pl.kornikkk.doctorappointments.domain.patient.Patient

fun PatientEntity.toDomain() = Patient(
        id,
        firstName,
        lastName,
        address.toDomain()
)

fun Patient.toEntity() = PatientEntity(
        id,
        firstName,
        lastName,
        address.toEntity()
)