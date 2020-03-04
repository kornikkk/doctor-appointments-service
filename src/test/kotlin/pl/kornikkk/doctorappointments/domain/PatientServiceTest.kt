package pl.kornikkk.doctorappointments.domain

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class PatientServiceTest {
    private val patientRepository: PatientRepository = mockk()
    private val patientService = PatientService(patientRepository)

    @Test
    fun `Should create patient`() {
        //given
        val firstName = "Test"
        val lastName = "User"
        val address = "Street 2/3 City"
        every { patientRepository.save(any()) } returns mockk()

        //when
        patientService.createPatient(firstName, lastName, address)

        //then
        verify { patientRepository.save(any()) }
    }

    @Test
    fun `Should get patient`() {
        //given
        val id = UUID.randomUUID()
        val firstName = "Test"
        val lastName = "User"
        val address = "Street 2/3 City"
        val expectedPatient = Patient(id, firstName, lastName, address)
        every { patientRepository.findById(id) } returns expectedPatient

        //when
        val patient = patientService.getPatient(id)

        //then
        assertEquals(expectedPatient, patient)
    }

    @Test
    fun `Should throw exception when getting not existing patient`() {
        //given
        every { patientRepository.findById(any()) } returns null

        //when / then
        assertThrows(PatientNotFoundException::class.java) {
            patientService.getPatient(UUID.randomUUID())
        }
    }
}