package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Doctor
import javax.persistence.EntityManager

fun DoctorEntity.toDomain() = Doctor(
        id,
        firstName,
        lastName,
        appointments.map { it.id!! }
)

fun Doctor.toEntity(entityManager: EntityManager) = DoctorEntity(
        id,
        firstName,
        lastName,
        appointmentIds.map { entityManager.getReference(AppointmentEntity::class.java, it) }.toMutableList()
)