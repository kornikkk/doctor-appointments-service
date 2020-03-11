package pl.kornikkk.doctorappointments.application.doctor

import com.ninjasquad.springmockk.MockkBean
import io.kotlintest.specs.AnnotationSpec
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
import pl.kornikkk.doctorappointments.domain.doctor.Doctor
import pl.kornikkk.doctorappointments.domain.doctor.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.doctor.DoctorService
import java.util.*

@AutoConfigureMockMvc
@ContextConfiguration(classes = [DoctorRestController::class])
@WebMvcTest
class DoctorRestControllerTests : AnnotationSpec() {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var service: DoctorService

    @Test
    fun `doctors POST should create doctor`() {
        val id = UUID.randomUUID()
        val firstName = "Test"
        val lastName = "Doctor"

        val requestBody = """
                |{
                |  "firstName" : "$firstName",
                |  "lastName" : "$lastName"
                |}
                |""".trimMargin()

        every { service.create(firstName, lastName) } returns id

        mockMvc.perform(
                post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated)
                .andExpect(header().string(HttpHeaders.LOCATION, StringEndsWith(true, id.toString())))
    }

    @Test
    fun `doctors PUT should update doctor`() {
        val id = UUID.randomUUID()

        val requestBody = """
                |{
                |  "firstName" : "Test",
                |  "lastName" : "Doctor"
                |}
                |""".trimMargin()

        every { service.update(any()) } returns mockk()
        every { service.get(id) } returns mockk(relaxed = true)

        mockMvc.perform(
                put("/doctors/$id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun `doctors GET should get doctor`() {
        val id = UUID.randomUUID()
        val doctor = mockk<Doctor>(relaxed = true)

        every { doctor.id } returns id
        every { service.get(id) } returns doctor

        mockMvc.perform(
                get("/doctors/$id"))
                .andExpect(status().isOk)
                .andExpect(content().string(StringContains(true, id.toString())))

    }

    @Test
    fun `doctors GET should return NotFound when doctor not existing`() {
        val id = UUID.randomUUID()

        every { service.get(any()) } throws DoctorNotFoundException(id)

        mockMvc.perform(
                get("/doctors/$id"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `doctors DELETE should delete doctor`() {
        val id = UUID.randomUUID()

        every { service.delete(id) } returns mockk()

        mockMvc.perform(delete("/doctors/$id"))
                .andExpect(status().is2xxSuccessful)

    }
}
