package pl.kornikkk.doctorappointments.application.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.kornikkk.doctorappointments.application.controller.request.ModifyPatientRequest
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
                .path("/{patientId}")
                .buildAndExpand(patient.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{patientId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable patientId: UUID): PatientResponse =
            patientService.getPatient(patientId).let {
                PatientResponse(
                        it.id!!,
                        it.firstName,
                        it.lastName,
                        it.address)
            }

    @PutMapping("/{patientId}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable patientId: UUID, @RequestBody request: UpdatePatientRequest) {
        patientService.updatePatient(Patient(
                patientId,
                request.firstName,
                request.lastName,
                request.address
        ))
    }

    @PatchMapping("/{patientId}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun modify(@PathVariable patientId: UUID, @RequestBody request: ModifyPatientRequest) {
        val patient = patientService.getPatient(patientId)
        request.firstName?.let { patient.firstName = it }
        request.lastName?.let { patient.lastName = it }
        request.address?.let { patient.address = it }

        patientService.updatePatient(patient)
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