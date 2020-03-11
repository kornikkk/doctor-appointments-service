package pl.kornikkk.doctorappointments.domain.appointment

import java.time.LocalDate
import java.time.LocalTime
import java.util.*

interface AppointmentService {
    fun schedule(patientId: UUID, doctorId: UUID, location: String, date: LocalDate, time: LocalTime): UUID
    fun get(id: UUID): Appointment
    fun findAll(): List<Appointment>
    fun findAllByPatientId(patientId: UUID): List<Appointment>
    fun reschedule(id: UUID, newTime: LocalTime, allowConflicts: Boolean = false)
    fun delete(id: UUID)
    fun deletePatientAppointments(patientId: UUID)
    fun deleteDoctorAppointments(doctorId: UUID)
}