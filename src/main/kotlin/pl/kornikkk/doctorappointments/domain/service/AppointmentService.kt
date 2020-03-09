package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Appointment
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

interface AppointmentService {
    fun schedule(patientId: UUID, doctorId: UUID, location: String, dateTime: LocalDateTime): UUID
    fun get(id: UUID): Appointment
    fun findAll(): List<Appointment>
    fun findAllByPatientId(patientId: UUID): List<Appointment>
    fun reschedule(id: UUID, newTime: LocalTime, allowConflicts: Boolean = false)
    fun delete(id: UUID)
}