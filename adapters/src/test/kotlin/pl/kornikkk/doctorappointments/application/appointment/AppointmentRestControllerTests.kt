package pl.kornikkk.doctorappointments.application.appointment

import com.ninjasquad.springmockk.MockkBean
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.spring.SpringListener
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.`is`
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
import pl.kornikkk.doctorappointments.domain.appointment.Appointment
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.appointment.ConflictingAppointmentException
import pl.kornikkk.doctorappointments.domain.patient.PatientNotFoundException
import java.time.LocalDate
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
    fun `appointments GET should find all appointments`() {
        val appointments: List<Appointment> = listOf(mockk(relaxed = true), mockk(relaxed = true))

        every { service.findAll() } returns appointments

        mockMvc.perform(
                get("/appointments"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()", `is`(2)))
    }

    @Test
    fun `appointments GET with patientId request parametere should find filtered appointments`() {
        val patientId = UUID.randomUUID()
        val patientAppointment: Appointment = mockk(relaxed = true)

        val allAppointments = listOf(patientAppointment, mockk(relaxed = true))
        val patientAppointments = allAppointments.filter { it.patientId == patientId }

        every { patientAppointment.patientId } returns patientId

        every { service.findAll() } returns allAppointments
        every { service.findAllByPatientId(patientId) } returns patientAppointments

        mockMvc.perform(
                get("/appointments")
                        .requestAttr("patientId", patientId))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()", `is`(2)))
    }

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

        every { service.schedule(patientId, doctorId, location, date, time) } returns id

        mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated)
                .andExpect(header().string(HttpHeaders.LOCATION, StringEndsWith(true, id.toString())))
    }

    @Test
    fun `appointments POST shouldn't schedule appointment for not existing patient`() {
        val patientId = UUID.randomUUID()
        val requestBody = """
                |{
                |  "patientId" : "$patientId",
                |  "doctorId" : "${UUID.randomUUID()}",
                |  "location" : "Test clinic",
                |  "date" : "${LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}",
                |  "time" : "${LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))}"
                |}
                |""".trimMargin()

        every { service.schedule(patientId, any(), any(), any(), any()) } throws PatientNotFoundException(patientId)

        mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `appointments DELETE should delete appointment`() {
        val id = UUID.randomUUID()

        every { service.delete(id) } returns mockk()

        mockMvc.perform(delete("/appointments/$id"))
                .andExpect(status().is2xxSuccessful)

    }

    @Test
    fun `appointments PATCH with operation 'reschedule' should reschedule appointment`() {
        val id = UUID.randomUUID()
        val newTime = LocalTime.of(12, 30)

        val requestBody = """
                |{
                |  "op" : "reschedule",
                |  "path" : "/time",
                |  "value" : "${newTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
                |}
                |""".trimMargin()

        every { service.reschedule(id, newTime, false) } returns mockk()

        mockMvc.perform(patch("/appointments/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().is2xxSuccessful)

    }

    @Test
    fun `appointments PATCH with operation 'reschedule' shouldn't reschedule conflicting appointment`() {
        val id = UUID.randomUUID()
        val newTime = LocalTime.of(12, 30)

        val requestBody = """
                |{
                |  "op" : "reschedule",
                |  "path" : "/time",
                |  "value" : "${newTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
                |}
                |""".trimMargin()

        every {
            service.reschedule(id, newTime, false)
        } throws ConflictingAppointmentException(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), newTime)

        mockMvc.perform(patch("/appointments/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isUnprocessableEntity)

    }

    @Test
    fun `appointments PATCH with operation 'reschedule_allow_conflicts' should reschedule appointment`() {
        val id = UUID.randomUUID()
        val newTime = LocalTime.of(12, 30)

        val requestBody = """
                |{
                |  "op" : "reschedule_allow_conflicts",
                |  "path" : "/time",
                |  "value" : "${newTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
                |}
                |""".trimMargin()

        every { service.reschedule(id, newTime, true) } returns mockk()

        mockMvc.perform(patch("/appointments/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().is2xxSuccessful)

    }
}
