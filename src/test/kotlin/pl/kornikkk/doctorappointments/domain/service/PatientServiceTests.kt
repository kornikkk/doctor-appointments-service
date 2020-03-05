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
    val patientService = PatientService(patientRepository)

    Given("new patient data") {
        val firstName = "Test"
        val lastName = "User"
        val address = "Street 2/3 City"

        every { patientRepository.save(any()) } returns mockk()

        When("creating patient") {
            patientService.createPatient(firstName, lastName, address)

            Then("patient is created") {
                verify { patientRepository.save(any()) }
            }
        }
    }

    Given("patient ids") {
        val existingPatientId = UUID.randomUUID()
        val notExistingPatientId = UUID.randomUUID()
        val patient = mockk<Patient>()

        every { patientRepository.findByPersonId(existingPatientId) } returns patient
        every { patientRepository.findByPersonId(notExistingPatientId) } returns null

        When("getting existing patient") {
            val foundPatient = patientService.getPatient(existingPatientId)

            Then("patient is found") {
                foundPatient shouldBe patient
            }
        }

        When("getting not existing patient") {
            val exception = shouldThrow<PatientNotFoundException> {
                patientService.getPatient(notExistingPatientId)
            }
            Then("exception with patient id is thrown") {
                exception.message shouldContain notExistingPatientId.toString()
            }
        }
    }

})