package pl.kornikkk.doctorappointments.domain.doctor

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class DoctorServiceTests : BehaviorSpec({

    val doctorRepository: DoctorRepository = mockk()
    val doctorService: DoctorService = DoctorServiceImpl(doctorRepository, mockk(relaxed = true))

    Given("new doctor data") {
        val firstName = "Test"
        val lastName = "Doctor"

        every { doctorRepository.save(any()) } returns mockk(relaxed = true)

        When("creating doctor") {
            doctorService.create(firstName, lastName)

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
            val foundDoctor = doctorService.get(existingDoctorId)

            Then("doctor is found") {
                foundDoctor shouldBe doctor
            }
        }

        When("getting not existing doctor") {
            val exception = shouldThrow<DoctorNotFoundException> {
                doctorService.get(notExistingDoctorId)
            }
            Then("exception with doctor id is thrown") {
                exception.message shouldContain notExistingDoctorId.toString()
            }
        }

        When("deleting existing doctor") {
            doctorService.delete(existingDoctorId)

            Then("doctor is deleted") {
                verify { doctorRepository.deleteById(existingDoctorId) }
            }
        }
    }

})