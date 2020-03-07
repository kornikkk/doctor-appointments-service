package pl.kornikkk.doctorappointments.application.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.core.StringContains
import org.hamcrest.core.StringEndsWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.service.PatientService
import java.util.*

@AutoConfigureMockMvc
@ContextConfiguration(classes = [PatientRestController::class])
@WebMvcTest
class PatientRestControllerTests : StringSpec() {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var service: PatientService

    init {
        "patients POST should create patient" {
            val personId = UUID.randomUUID()
            val firstName = "Test"
            val lastName = "User"
            val address = "Street 2/3 City"

            val requestBody = """
                |{
                |  "firstName" : "$firstName",
                |  "lastName" : "$lastName",
                |  "address" : "$address"
                |}
                |""".trimMargin()

            every {
                service.createPatient(firstName, lastName, address)
            } returns Patient(personId, firstName, lastName, address)

            mockMvc.perform(
                    post("/patients")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated)
                    .andExpect(header().string(HttpHeaders.LOCATION, StringEndsWith(true, personId.toString())))
        }

        "patients PUT should update patient" {
            val personId = UUID.randomUUID()

            val requestBody = """
                |{
                |  "firstName" : "Test",
                |  "lastName" : "Patient",
                |  "address" : "Street 2/3 City"
                |}
                |""".trimMargin()

            every { service.updatePatient(any()) } returns mockk()

            mockMvc.perform(
                    put("/patients/$personId")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().is2xxSuccessful)
        }

        "patients GET should get patient" {
            val personId = UUID.randomUUID()
            val patient = mockk<Patient>(relaxed = true)

            every { patient.id } returns personId
            every { service.getPatient(personId) } returns patient

            mockMvc.perform(
                    get("/patients/$personId"))
                    .andExpect(status().isOk)
                    .andExpect(content().string(StringContains(true, personId.toString())))

        }

        "patients GET should return NotFound when patient not existing" {
            val personId = UUID.randomUUID()

            every { service.getPatient(any()) } throws PatientNotFoundException(personId)

            mockMvc.perform(
                    get("/patients/$personId"))
                    .andExpect(status().isNotFound)
        }

        "patients DELETE should delete patient" {
            val id = UUID.randomUUID()

            every { service.deletePatient(id) } returns mockk()

            mockMvc.perform(delete("/patients/$id"))
                    .andExpect(status().is2xxSuccessful)

        }
    }
}
