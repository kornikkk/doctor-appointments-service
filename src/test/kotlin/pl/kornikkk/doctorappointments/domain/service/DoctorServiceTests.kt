package pl.kornikkk.doctorappointments.domain.service

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import pl.kornikkk.doctorappointments.domain.Doctor
import pl.kornikkk.doctorappointments.domain.exception.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import java.util.*

class DoctorServiceTests : BehaviorSpec({

    val doctorRepository: DoctorRepository = mockk()
    val doctorService: DoctorService = DoctorServiceImpl(doctorRepository)

    Given("new doctor data") {
        val firstName = "Test"
        val lastName = "Doctor"

        every { doctorRepository.save(any()) } returns mockk()

        When("creating doctor") {
            doctorService.createDoctor(firstName, lastName)

            Then("doctor is created") {
                verify { doctorRepository.save(any()) }
            }
        }
    }

    Given("doctor ids") {
        val existingDoctorId = UUID.randomUUID()
        val notExistingDoctorId = UUID.randomUUID()
        val doctor = mockk<Doctor>()

        every { doctorRepository.findById(existingDoctorId) } returns doctor
        every { doctorRepository.findById(notExistingDoctorId) } returns null
        every { doctorRepository.deleteById(any()) } returns mockk()

        When("getting existing doctor") {
            val foundPatient = doctorService.getDoctor(existingDoctorId)

            Then("doctor is found") {
                foundPatient shouldBe doctor
            }
        }

        When("getting not existing doctor") {
            val exception = shouldThrow<DoctorNotFoundException> {
                doctorService.getDoctor(notExistingDoctorId)
            }
            Then("exception with doctor id is thrown") {
                exception.message shouldContain notExistingDoctorId.toString()
            }
        }

        When("deleting existing doctor") {
            doctorService.deleteDoctor(existingDoctorId)

            Then("patient is deleted") {
                verify { doctorRepository.deleteById(existingDoctorId) }
            }
        }
    }

})