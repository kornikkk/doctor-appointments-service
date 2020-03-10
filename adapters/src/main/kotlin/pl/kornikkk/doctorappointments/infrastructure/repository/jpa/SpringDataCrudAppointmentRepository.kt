package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Repository
interface SpringDataCrudAppointmentRepository : CrudRepository<AppointmentEntity, UUID> {

    fun findAllByPatientId(patientId: UUID): Iterable<AppointmentEntity>

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN true ELSE false END FROM AppointmentEntity a WHERE (a.patientId = :patientId OR a.doctorId =:doctorId) AND a.dateTime = :dateTime")
    fun existsAtDateTime(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime): Boolean

    @Transactional
    fun deleteByPatientId(patientId: UUID)

    @Transactional
    fun deleteByDoctorId(doctorId: UUID)

}