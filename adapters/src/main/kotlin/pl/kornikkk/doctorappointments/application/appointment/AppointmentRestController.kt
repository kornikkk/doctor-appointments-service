package pl.kornikkk.doctorappointments.application.appointment

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kornikkk.doctorappointments.application.commons.utils.Logging
import pl.kornikkk.doctorappointments.application.commons.utils.createdWithLocationResponse
import pl.kornikkk.doctorappointments.application.commons.utils.logger
import pl.kornikkk.doctorappointments.domain.appointment.Appointment
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.doctor.DoctorNotFoundException
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@RequestMapping("/appointments")
class AppointmentRestController(private val appointmentService: AppointmentService) : Logging {

    private val log = logger()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody request: NewAppointmentRequest): ResponseEntity<Any> =
            createdWithLocationResponse(appointmentService.schedule(
                    request.patientId,
                    request.doctorId,
                    request.location,
                    LocalDateTime.of(request.date, request.time))
            )

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID): AppointmentResource =
            appointmentService.get(id).toResource()

    @PatchMapping("/{id}")
    fun modify(@PathVariable id: UUID, @RequestBody request: ModifyResourceRequest): ResponseEntity<Any> = when (request.op) {
        "reschedule" -> reschedule(id, request.path, request.value, false)
        "reschedule_allow_conflicts" -> reschedule(id, request.path, request.value, true)
        else -> ResponseEntity.badRequest().build()
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun find(@RequestParam patientId: UUID?): List<AppointmentResource> = when {
        patientId != null -> appointmentService.findAllByPatientId(patientId)
        else -> appointmentService.findAll()
    }.map(Appointment::toResource)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        appointmentService.delete(id)
    }

    @ExceptionHandler(DoctorNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(notFoundException: DoctorNotFoundException) {
        log.debug(notFoundException.message)
    }

    private fun reschedule(id: UUID, path: String, value: Any, allowConflicts: Boolean): ResponseEntity<Any> = when {
        path != "/time" || value !is String ->
            ResponseEntity.badRequest().build()
        else -> {
            val newTime = LocalTime.parse(value, DateTimeFormatter.ofPattern("HH:mm"))
            appointmentService.reschedule(id, newTime, allowConflicts)
            ResponseEntity.noContent().build()
        }
    }
}
