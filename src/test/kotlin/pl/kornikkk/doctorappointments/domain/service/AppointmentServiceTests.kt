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
        val dateTime = LocalDateTime.of(2020, 3, 8, 7, 30)
        val conflictingDateTime = LocalDateTime.of(2020, 3, 8, 9, 30)

        every { appointmentRepository.save(any()) } returns mockk(relaxed = true)
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
        val dateTime = LocalDateTime.of(2020, 3, 8, 7, 30)
        val appointment = Appointment(appointmentId, patientId, doctorId, location, dateTime)

        val newDateTime = LocalDateTime.of(2020, 3, 8, 9, 30)
        val newConflictingDateTime = LocalDateTime.of(2020, 3, 8, 14, 30)

        every { appointmentRepository.findById(appointmentId) } returns appointment
        every { appointmentRepository.existsAtDateTime(patientId, doctorId, newDateTime) } returns false
        every { appointmentRepository.existsAtDateTime(patientId, doctorId, newConflictingDateTime) } returns true

        When("rescheduling appointment") {
            appointmentService.rescheduleAppointment(appointmentId, newDateTime.toLocalTime())

            Then("appointment is rescheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }

        When("rescheduling appointment to conflicting time") {
            shouldThrow<ConflictingAppointmentException> {
                appointmentService.rescheduleAppointment(appointmentId, newConflictingDateTime.toLocalTime())
            }
            Then("conflicting appointment exception is thrown") { }
        }

        When("rescheduling appointment to conflicting time with conflicts allowed") {
            appointmentService.rescheduleAppointment(appointmentId, newConflictingDateTime.toLocalTime(), true)

            Then("conflicting appointment is rescheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }
    }

})