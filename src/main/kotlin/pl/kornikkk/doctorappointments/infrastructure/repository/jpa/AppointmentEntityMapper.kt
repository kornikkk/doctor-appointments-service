package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Appointment
import javax.persistence.EntityManager

fun AppointmentEntity.toDomain() = Appointment(
        id,
        patient.id!!,
        doctor.id!!,
        location,
        dateTime
)

fun Appointment.toEntity(entityManager: EntityManager) = AppointmentEntity(
        id,
        entityManager.getReference(PatientEntity::class.java, patientId),
        entityManager.getReference(DoctorEntity::class.java, doctorId),
        location,
        dateTime
)