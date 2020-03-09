package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.Appointment
import pl.kornikkk.doctorappointments.domain.repository.AppointmentRepository
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


class CrudAppointmentRepository(private val crudRepository: SpringDataCrudAppointmentRepository) : AppointmentRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findById(id: UUID): Appointment? =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun findAll(): List<Appointment> =
            crudRepository.findAll().map(AppointmentEntity::toDomain)

    override fun findAllByPatientId(patientId: UUID): List<Appointment> =
            crudRepository.findAllByPatientId(patientId).map(AppointmentEntity::toDomain)

    override fun save(appointment: Appointment): Appointment =
            crudRepository.save(appointment.toEntity(entityManager)).toDomain()

    override fun existsAtDateTime(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime): Boolean =
            crudRepository.existsAtDateTime(patientId, doctorId, dateTime)

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }

}