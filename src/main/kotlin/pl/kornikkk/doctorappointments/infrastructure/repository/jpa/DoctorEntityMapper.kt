package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Doctor

fun DoctorEntity.toDomain() = Doctor(
        id,
        firstName,
        lastName
)

fun Doctor.toEntity() = DoctorEntity(
        id,
        firstName,
        lastName
)