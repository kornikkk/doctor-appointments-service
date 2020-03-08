package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Appointment
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

interface AppointmentService {
    fun scheduleAppointment(patientId: UUID, doctorId: UUID, location: String, dateTime: LocalDateTime): UUID
    fun getAppointment(id: UUID): Appointment
    fun rescheduleAppointment(id: UUID, newTime: LocalTime, allowConflicts: Boolean = false)
    fun delete(id: UUID)
}