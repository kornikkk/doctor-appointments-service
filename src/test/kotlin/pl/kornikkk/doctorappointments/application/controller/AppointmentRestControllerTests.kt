package pl.kornikkk.doctorappointments.application.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.spring.SpringListener
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.core.StringEndsWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import pl.kornikkk.doctorappointments.domain.service.AppointmentService
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@AutoConfigureMockMvc
@ContextConfiguration(classes = [AppointmentRestController::class])
@WebMvcTest
class AppointmentRestControllerTests : AnnotationSpec() {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var service: AppointmentService

    @Test
    fun `appointments POST should schedule appointment`() {
        val id = UUID.randomUUID()
        val patientId = UUID.randomUUID()
        val doctorId = UUID.randomUUID()
        val location = "Test clinic"
        val date = LocalDate.of(2020, 3, 8)
        val time = LocalTime.of(12, 30)

        val requestBody = """
                |{
                |  "patientId" : "$patientId",
                |  "doctorId" : "$doctorId",
                |  "location" : "$location",
                |  "date" : "${date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}",
                |  "time" : "${time.format(DateTimeFormatter.ofPattern("HH:mm"))}"
                |}
                |""".trimMargin()

        every { service.scheduleAppointment(patientId, doctorId, location, LocalDateTime.of(date, time)) } returns id

        mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated)
                .andExpect(header().string(HttpHeaders.LOCATION, StringEndsWith(true, id.toString())))
    }

    @Test
    fun `appointments DELETE should delete appointment`() {
        val id = UUID.randomUUID()

        every { service.delete(id) } returns mockk()

        mockMvc.perform(delete("/appointments/$id"))
                .andExpect(status().is2xxSuccessful)

    }
}
