package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Appointment

fun AppointmentEntity.toDomain() = Appointment(
        id,
        patientId,
        doctorId,
        location,
        dateTime
)

fun Appointment.toEntity() = AppointmentEntity(
        id,
        patientId,
        doctorId,
        location,
        dateTime
)