package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Patient
import javax.persistence.EntityManager

fun PatientEntity.toDomain() = Patient(
        id,
        firstName,
        lastName,
        address,
        appointments.map { it.id!! }
)

fun Patient.toEntity(entityManager: EntityManager) = PatientEntity(
        id,
        firstName,
        lastName,
        address,
        appointmentIds.map { entityManager.getReference(AppointmentEntity::class.java, it) }.toMutableList()
)