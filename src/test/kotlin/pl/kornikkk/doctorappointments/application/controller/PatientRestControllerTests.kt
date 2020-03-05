package pl.kornikkk.doctorappointments.application.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import io.mockk.every
import org.hamcrest.core.StringEndsWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import pl.kornikkk.doctorappointments.domain.Patient
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
            val patient = Patient(personId, firstName, lastName, address)

            every { service.createPatient(firstName, lastName, address) } returns patient

            val request = """
                |{
                |  "firstName" : "$firstName",
                |  "lastName" : "$lastName",
                |  "address" : "$address"
                |}
                |""".trimMargin()

            mockMvc.perform(
                    post("/patients")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isCreated)
                    .andExpect(header().string(HttpHeaders.LOCATION, StringEndsWith(true, personId.toString())))
        }
    }
}
