package pl.kornikkk.doctorappointments.application.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kornikkk.doctorappointments.application.controller.request.NewAppointmentRequest
import pl.kornikkk.doctorappointments.application.controller.response.AppointmentResource
import pl.kornikkk.doctorappointments.application.controller.utils.createdWithLocationResponse
import pl.kornikkk.doctorappointments.application.util.Logging
import pl.kornikkk.doctorappointments.application.util.logger
import pl.kornikkk.doctorappointments.domain.exception.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.service.AppointmentService
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/appointments")
class AppointmentRestController(private val appointmentService: AppointmentService) : Logging {

    private val log = logger()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody request: NewAppointmentRequest): ResponseEntity<Any> =
            createdWithLocationResponse(appointmentService.scheduleAppointment(
                    request.patientId,
                    request.doctorId,
                    request.location,
                    LocalDateTime.of(request.date, request.time))
            )

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID): AppointmentResource =
            appointmentService.getAppointment(id).toResource()

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

}