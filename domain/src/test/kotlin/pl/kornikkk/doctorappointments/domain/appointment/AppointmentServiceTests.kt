package pl.kornikkk.doctorappointments.domain.appointment

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import pl.kornikkk.doctorappointments.domain.doctor.DoctorService
import pl.kornikkk.doctorappointments.domain.patient.PatientService
import java.time.LocalDate
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

        val date = LocalDate.of(2020, 3, 8)
        val time = LocalTime.of(7, 30)
        val conflictingTime = LocalTime.of(9, 30)

        every { appointmentRepository.save(any()) } returns mockk(relaxed = true)
        every { appointmentRepository.existsByPatientIdOrDoctorIdAtDateTime(patientId, doctorId, LocalDateTime.of(date, time)) } returns false
        every { appointmentRepository.existsByPatientIdOrDoctorIdAtDateTime(patientId, doctorId, LocalDateTime.of(date, conflictingTime)) } returns true

        every { patientService.existsById(patientId) } returns true
        every { doctorService.existsById(doctorId) } returns true

        When("scheduling appointment") {
            appointmentService.schedule(patientId, doctorId, location, date, time)

            Then("appointment is scheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }
        When("scheduling conflicting appointment") {
            shouldThrow<ConflictingAppointmentException> {
                appointmentService.schedule(patientId, doctorId, location, date, conflictingTime)
            }
            Then("conflicting appointment exception is thrown") { }
        }
    }

    Given("appointment and new time") {
        val appointmentId = UUID.randomUUID()
        val patientId = UUID.randomUUID()
        val doctorId = UUID.randomUUID()
        val location = "Test Location"
        val date = LocalDate.of(2020, 3, 8)
        val time = LocalTime.of(7, 30)
        val appointment = Appointment(appointmentId, patientId, doctorId, location, date, time)

        val newTime = LocalTime.of(8, 30)
        val newConflictingTime = LocalTime.of(9, 30)

        every { appointmentRepository.findById(appointmentId) } returns appointment
        every {
            appointmentRepository.existsByPatientIdOrDoctorIdAtDateTime(patientId, doctorId, LocalDateTime.of(date, newTime))
        } returns false
        every {
            appointmentRepository.existsByPatientIdOrDoctorIdAtDateTime(patientId, doctorId, LocalDateTime.of(date, newConflictingTime))
        } returns true

        When("rescheduling appointment") {
            appointmentService.reschedule(appointmentId, newTime)

            Then("appointment is rescheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }

        When("rescheduling appointment to conflicting time") {
            shouldThrow<ConflictingAppointmentException> {
                appointmentService.reschedule(appointmentId, newConflictingTime)
            }
            Then("conflicting appointment exception is thrown") { }
        }

        When("rescheduling appointment to conflicting time with conflicts allowed") {
            appointmentService.reschedule(appointmentId, newConflictingTime, true)

            Then("conflicting appointment is rescheduled") {
                verify { appointmentRepository.save(any()) }
            }
        }
    }

    Given("appointment ids") {
        val existingAppointmentId = UUID.randomUUID()
        val notExistingAppointmentId = UUID.randomUUID()
        val appointment = mockk<Appointment>()

        every { appointmentRepository.findById(existingAppointmentId) } returns appointment
        every { appointmentRepository.findById(notExistingAppointmentId) } returns null
        every { appointmentRepository.deleteById(any()) } returns mockk()

        When("getting existing appointment") {
            val foundAppointment = appointmentService.get(existingAppointmentId)

            Then("appointment is found") {
                foundAppointment shouldBe appointment
            }
        }

        When("getting not existing appointment") {
            val exception = shouldThrow<AppointmentNotFoundException> {
                appointmentService.get(notExistingAppointmentId)
            }
            Then("exception with appointment id is thrown") {
                exception.message shouldContain notExistingAppointmentId.toString()
            }
        }

        When("deleting existing appointment") {
            appointmentService.delete(existingAppointmentId)

            Then("appointment is deleted") {
                verify { appointmentRepository.deleteById(existingAppointmentId) }
            }
        }
    }

    Given("appointments and patient ID") {
        val patientId = UUID.randomUUID()

        every { appointmentRepository.findAll() } returns listOf()
        every { appointmentRepository.findAllByPatientId(patientId) } returns listOf()

        When("getting all appointments") {
            appointmentService.findAll()

            Then("all appointments are fetched") {
                verify { appointmentRepository.findAll() }
            }
        }

        When("getting patient appointments") {
            appointmentService.findAllByPatientId(patientId)

            Then("all appointments are fetched") {
                verify { appointmentRepository.findAllByPatientId(patientId) }
            }
        }
    }
})