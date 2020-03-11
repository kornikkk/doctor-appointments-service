package pl.kornikkk.doctorappointments.infrastructure.doctor

import pl.kornikkk.doctorappointments.domain.doctor.Doctor

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