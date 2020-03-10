package pl.kornikkk.doctorappointments.domain.service

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import java.util.*

class PatientServiceTests : BehaviorSpec({

    val patientRepository: PatientRepository = mockk()
    val patientService: PatientService = PatientServiceImpl(patientRepository, mockk(relaxed = true))

    Given("new patient data") {
        val firstName = "Test"
        val lastName = "Patient"
        val address = "Street 2/3 City"

        every { patientRepository.save(any()) } returns mockk(relaxed = true)

        When("creating patient") {
            patientService.create(firstName, lastName, address)

            Then("patient is created") {
                verify { patientRepository.save(any()) }
            }
        }
    }

    Given("patient ids") {
        val existingPatientId = UUID.randomUUID()
        val notExistingPatientId = UUID.randomUUID()
        val patient = mockk<Patient>()

        every { patientRepository.findById(existingPatientId) } returns patient
        every { patientRepository.findById(notExistingPatientId) } returns null
        every { patientRepository.deleteById(any()) } returns mockk()

        When("getting existing patient") {
            val foundPatient = patientService.get(existingPatientId)

            Then("patient is found") {
                foundPatient shouldBe patient
            }
        }

        When("getting not existing patient") {
            val exception = shouldThrow<PatientNotFoundException> {
                patientService.get(notExistingPatientId)
            }
            Then("exception with patient id is thrown") {
                exception.message shouldContain notExistingPatientId.toString()
            }
        }

        When("deleting existing patient") {
            patientService.delete(existingPatientId)

            Then("patient is deleted") {
                verify { patientRepository.deleteById(existingPatientId) }
            }
        }
    }

})