package pl.kornikkk.doctorappointments.application.patient

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kornikkk.doctorappointments.application.commons.controller.ErrorResponse
import pl.kornikkk.doctorappointments.application.commons.controller.createErrorResponse
import pl.kornikkk.doctorappointments.application.commons.controller.createdWithLocationResponse
import pl.kornikkk.doctorappointments.application.commons.utils.Logging
import pl.kornikkk.doctorappointments.application.commons.utils.logger
import pl.kornikkk.doctorappointments.domain.patient.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.patient.PatientService
import java.util.*

@RestController
@RequestMapping("/patients")
class PatientRestController(private val patientService: PatientService) : Logging {

    private val log = logger()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody request: NewPatientRequest): ResponseEntity<Any> =
            createdWithLocationResponse(patientService.create(request.firstName, request.lastName, request.address))

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID): PatientResource =
            patientService.get(id).toResource()

    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: UUID, @RequestBody request: UpdatePatientRequest) {
        patientService.update(patientService.get(id).update(request))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        patientService.delete(id)
    }

    @ExceptionHandler(PatientNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(notFoundException: PatientNotFoundException): ResponseEntity<ErrorResponse> {
        log.debug(notFoundException.message)
        return createErrorResponse(notFoundException, HttpStatus.NOT_FOUND)
    }

}