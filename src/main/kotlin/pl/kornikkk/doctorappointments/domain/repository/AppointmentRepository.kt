package pl.kornikkk.doctorappointments.domain.repository

import pl.kornikkk.doctorappointments.domain.Appointment
import java.time.LocalDateTime
import java.util.*

interface AppointmentRepository {
    fun findById(id: UUID): Appointment?
    fun findAll(): List<Appointment>
    fun findAllByPatientId(patientId: UUID): List<Appointment>
    fun save(appointment: Appointment): Appointment
    fun existsAtDateTime(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime): Boolean
    fun deleteById(id: UUID)
}