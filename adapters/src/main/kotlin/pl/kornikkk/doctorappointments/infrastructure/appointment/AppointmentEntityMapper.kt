package pl.kornikkk.doctorappointments.infrastructure.appointment

import pl.kornikkk.doctorappointments.domain.appointment.Appointment

fun AppointmentEntity.toDomain() = Appointment(
        id,
        patientId,
        doctorId,
        location,
        dateTime.toLocalDate(),
        dateTime.toLocalTime()
)

fun Appointment.toEntity() = AppointmentEntity(
        id,
        patientId,
        doctorId,
        location,
        dateTime
)