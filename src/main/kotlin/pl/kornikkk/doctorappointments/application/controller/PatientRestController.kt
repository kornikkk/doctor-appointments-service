package pl.kornikkk.doctorappointments.application.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.kornikkk.doctorappointments.application.controller.request.NewPatientRequest
import pl.kornikkk.doctorappointments.application.controller.request.UpdatePatientRequest
import pl.kornikkk.doctorappointments.application.controller.response.PatientResponse
import pl.kornikkk.doctorappointments.application.util.Logging
import pl.kornikkk.doctorappointments.application.util.logger
import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.service.PatientService
import java.util.*

@RestController
@RequestMapping("/patients")
class PatientRestController(private val patientService: PatientService) : Logging {

    private val log = logger()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody request: NewPatientRequest): ResponseEntity<Any> {
        val patient = patientService.createPatient(request.firstName, request.lastName, request.address)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patient.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID): PatientResponse =
            patientService.getPatient(id).let {
                PatientResponse(
                        it.id!!,
                        it.firstName,
                        it.lastName,
                        it.address)
            }

    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: UUID, @RequestBody request: UpdatePatientRequest) {
        patientService.updatePatient(Patient(
                id,
                request.firstName,
                request.lastName,
                request.address
        ))
    }

    @DeleteMapping("/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable patientId: UUID) {
        patientService.deletePatient(patientId)
    }

    @ExceptionHandler(PatientNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlePatientNotFound(patientNotFoundException: PatientNotFoundException) {
        log.debug(patientNotFoundException.message)
    }

}