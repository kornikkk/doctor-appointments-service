package pl.kornikkk.doctorappointments.application.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kornikkk.doctorappointments.application.controller.request.NewDoctorRequest
import pl.kornikkk.doctorappointments.application.controller.request.UpdateDoctorRequest
import pl.kornikkk.doctorappointments.application.controller.response.DoctorResource
import pl.kornikkk.doctorappointments.application.controller.utils.createdWithLocationResponse
import pl.kornikkk.doctorappointments.application.util.Logging
import pl.kornikkk.doctorappointments.application.util.logger
import pl.kornikkk.doctorappointments.domain.exception.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.service.DoctorService
import java.util.*

@RestController
@RequestMapping("/doctors")
class DoctorRestController(private val doctorService: DoctorService) : Logging {

    private val log = logger()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody request: NewDoctorRequest): ResponseEntity<Any> =
            createdWithLocationResponse(doctorService.create(request.firstName, request.lastName))

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID): DoctorResource =
            doctorService.get(id).toResource()

    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: UUID, @RequestBody request: UpdateDoctorRequest) {
        doctorService.update(doctorService.get(id).update(request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        doctorService.delete(id)
    }

    @ExceptionHandler(DoctorNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(notFoundException: DoctorNotFoundException) {
        log.debug(notFoundException.message)
    }

}