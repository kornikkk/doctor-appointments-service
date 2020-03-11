package pl.kornikkk.doctorappointments.infrastructure.appointment

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.appointment.Appointment
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentRepository
import java.time.LocalDateTime
import java.util.*


class CrudAppointmentRepository(private val crudRepository: SpringDataCrudAppointmentRepository) : AppointmentRepository {

    override fun findById(id: UUID): Appointment? =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun findAll(): List<Appointment> =
            crudRepository.findAll().map(AppointmentEntity::toDomain)

    override fun findAllByPatientId(patientId: UUID): List<Appointment> =
            crudRepository.findAllByPatientId(patientId).map(AppointmentEntity::toDomain)

    override fun save(appointment: Appointment): Appointment =
            crudRepository.save(appointment.toEntity()).toDomain()

    override fun existsByPatientIdOrDoctorIdAtDateTime(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime): Boolean =
            crudRepository.existsByPatientIdOrDoctorIdAtDateTime(patientId, doctorId, dateTime)

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }

    override fun deleteByPatientId(patientId: UUID) {
        crudRepository.deleteByPatientId(patientId)
    }

    override fun deleteByDoctorId(doctorId: UUID) {
        crudRepository.deleteByDoctorId(doctorId)
    }
}