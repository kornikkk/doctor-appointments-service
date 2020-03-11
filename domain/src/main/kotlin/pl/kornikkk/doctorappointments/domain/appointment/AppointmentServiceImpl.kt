package pl.kornikkk.doctorappointments.domain.appointment

import pl.kornikkk.doctorappointments.domain.doctor.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.doctor.DoctorService
import pl.kornikkk.doctorappointments.domain.patient.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.patient.PatientService
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class AppointmentServiceImpl(private val appointmentRepository: AppointmentRepository,
                             private val patientService: PatientService,
                             private val doctorService: DoctorService) : AppointmentService {

    override fun schedule(patientId: UUID, doctorId: UUID, location: String, date: LocalDate, time: LocalTime): UUID = when {
        !patientService.existsById(patientId) ->
            throw PatientNotFoundException(patientId)
        !doctorService.existsById(doctorId) ->
            throw DoctorNotFoundException(doctorId)
        isConflictingAnotherAppointment(patientId, doctorId, date, time) ->
            throw ConflictingAppointmentException(patientId, doctorId, date, time)
        else ->
            appointmentRepository.save(Appointment(patientId, doctorId, location, date, time)).id!!
    }

    override fun get(id: UUID): Appointment =
            appointmentRepository.findById(id) ?: throw AppointmentNotFoundException(id)

    override fun findAll(): List<Appointment> =
            appointmentRepository.findAll()

    override fun findAllByPatientId(patientId: UUID): List<Appointment> =
            appointmentRepository.findAllByPatientId(patientId)

    override fun reschedule(id: UUID, newTime: LocalTime, allowConflicts: Boolean) {
        val appointment = get(id)
        if (!allowConflicts && isConflictingAnotherAppointment(appointment.patientId, appointment.doctorId, appointment.date, newTime)) {
            throw ConflictingAppointmentException(appointment.patientId, appointment.doctorId, appointment.date, newTime)
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


    private fun isConflictingAnotherAppointment(patientId: UUID, doctorId: UUID, date: LocalDate, time: LocalTime): Boolean =
            appointmentRepository.existsByPatientIdOrDoctorIdAtDateTime(patientId, doctorId, LocalDateTime.of(date, time))

}