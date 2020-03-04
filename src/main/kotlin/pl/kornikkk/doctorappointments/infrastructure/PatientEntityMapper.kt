package pl.kornikkk.doctorappointments.infrastructure

import pl.kornikkk.doctorappointments.domain.Patient
import java.util.*

fun PatientEntity.toDomain() = Patient(
        UUID.fromString(personId),
        firstName,
        lastName,
        address
)

fun Patient.toEntity(entityId: Long?) = PatientEntity(
        entityId,
        personId.toString(),
        firstName,
        lastName,
        address
)