package pl.kornikkk.doctorappointments.domain.appointment

import java.time.LocalDateTime
import java.util.*

interface AppointmentRepository {
    fun findById(id: UUID): Appointment?
    fun findAll(): List<Appointment>
    fun findAllByPatientId(patientId: UUID): List<Appointment>
    fun save(appointment: Appointment): Appointment
    fun existsByPatientIdOrDoctorIdAtDateTime(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime): Boolean
    fun deleteById(id: UUID)
    fun deleteByPatientId(patientId: UUID)
    fun deleteByDoctorId(doctorId: UUID)
}