package pl.kornikkk.doctorappointments.domain.service

import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import pl.kornikkk.doctorappointments.domain.Appointment
import pl.kornikkk.doctorappointments.domain.exception.ConflictingAppointmentException
import pl.kornikkk.doctorappointments.domain.repository.AppointmentRepository
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class AppointmentServiceTests : BehaviorSpec({

    val patientService: PatientService = mockk()
    val doctorService: DoctorService = mockk()
    val appointmentRepository: AppointmentRepository = mockk()
    val appointmentService: AppointmentService = AppointmentServiceImpl(appointmentRepository, patientService, doctorService)

    Given("new appointment data") {
        val patientId = UUID.randomUUID()
        val doctorId = UUID.randomUUID()
        val location = "Test Location"
        val dateTime = LocalDateTime.now()
        val conflictingDateTime = LocalDateTime.now().plusHours(2)

        every { appointmentRepository.save(any()) } returns mockk()
        every { appointmentRepository.existsAtDateTime(patientId, doctorId, dateTime) } returns false
        every { appointmentRepository.existsAtDateTime(patientId, doctorId, conflictingDateTime) } returns true

        When("scheduling appointment") {
            appointmentService.scheduleAppointment(patientId, doctorId, location, dateTime)

            Then("appointment is scheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }
        When("scheduling conflicting appointment") {
            shouldThrow<ConflictingAppointmentException> {
                appointmentService.scheduleAppointment(patientId, doctorId, location, conflictingDateTime)
            }
            Then("conflicting appointment exception is thrown") { }
        }
    }

    Given("appointment and new time") {
        val appointmentId = UUID.randomUUID()
        val patientId = UUID.randomUUID()
        val doctorId = UUID.randomUUID()
        val location = "Test Location"
        val dateTime = LocalDateTime.now()
        val appointment = Appointment(appointmentId, patientId, doctorId, location, dateTime)

        val newTime = LocalTime.now().plusHours(2)
        val newDateTime = dateTime.with(newTime)

        every { appointmentRepository.findById(appointmentId) } returns appointment
        every { appointmentRepository.existsAtDateTime(patientId, doctorId, newDateTime) } returns false

        When("rescheduling appointment") {
            appointmentService.rescheduleAppointment(appointmentId, newTime)

            Then("appointment is rescheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }
    }


    Given("appointment and new time conflicting with another appointment") {
        val appointmentId = UUID.randomUUID()
        val patientId = UUID.randomUUID()
        val doctorId = UUID.randomUUID()
        val location = "Test Location"
        val dateTime = LocalDateTime.now()
        val appointment = Appointment(appointmentId, patientId, doctorId, location, dateTime)

        val newTime = LocalTime.now().plusHours(2)
        val newDateTime = dateTime.with(newTime)

        every { appointmentRepository.findById(appointmentId) } returns appointment
        every { appointmentRepository.existsAtDateTime(patientId, doctorId, newDateTime) } returns true

        When("rescheduling appointment") {
            shouldThrow<ConflictingAppointmentException> {
                appointmentService.rescheduleAppointment(appointmentId, newTime)
            }
            Then("conflicting appointment exception is thrown") { }
        }

        When("rescheduling conflicting appointment with conflicts allowed") {
            appointmentService.rescheduleAppointment(appointmentId, newTime, true)

            Then("conflicting appointment is rescheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }
    }
})