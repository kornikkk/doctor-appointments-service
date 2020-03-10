package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Appointment
import pl.kornikkk.doctorappointments.domain.exception.AppointmentNotFoundException
import pl.kornikkk.doctorappointments.domain.exception.ConflictingAppointmentException
import pl.kornikkk.doctorappointments.domain.exception.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.AppointmentRepository
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class AppointmentServiceImpl(private val appointmentRepository: AppointmentRepository,
                             private val patientService: PatientService,
                             private val doctorService: DoctorService) : AppointmentService {

    override fun schedule(patientId: UUID, doctorId: UUID, location: String, dateTime: LocalDateTime): UUID = when {
        !patientService.existsById(patientId) ->
            throw PatientNotFoundException(patientId)
        !doctorService.existsById(doctorId) ->
            throw DoctorNotFoundException(doctorId)
        isConflictingAnotherAppointment(patientId, doctorId, dateTime) ->
            throw ConflictingAppointmentException(patientId, doctorId, dateTime)
        else ->
            appointmentRepository.save(Appointment(patientId, doctorId, location, dateTime)).id!!
    }

    override fun get(id: UUID): Appointment =
            appointmentRepository.findById(id) ?: throw AppointmentNotFoundException(id)

    override fun findAll(): List<Appointment> =
            appointmentRepository.findAll()

    override fun findAllByPatientId(patientId: UUID): List<Appointment> =
            appointmentRepository.findAllByPatientId(patientId)

    override fun reschedule(id: UUID, newTime: LocalTime, allowConflicts: Boolean) {
        val appointment = get(id)
        val newDateTime = appointment.dateTime.with(newTime)
        if (!allowConflicts && isConflictingAnotherAppointment(appointment.patientId, appointment.doctorId, newDateTime)) {
            throw ConflictingAppointmentException(appointment.patientId, appointment.doctorId, newDateTime)
        }
        appointment.reschedule(newTime)
        appointmentRepository.save(appointment)
    }

    override fun delete(id: UUID) {
        appointmentRepository.deleteById(id)
    }

    override fun deletePatientAppointments(patientId: UUID) {
        appointmentRepository.deleteByPatientId(patientId)
    }

    override fun deleteDoctorAppointments(doctorId: UUID) {
        appointmentRepository.deleteByDoctorId(doctorId)
    }


    private fun isConflictingAnotherAppointment(patientId: UUID, doctorId: UUID, dateTime: LocalDateTime): Boolean =
            appointmentRepository.existsAtDateTime(patientId, doctorId, dateTime)

}