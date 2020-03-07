package pl.kornikkk.doctorappointments.application.controller

import org.springframework.http.HttpStatus
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun add(@RequestBody request: NewPatientRequest): ResponseEntity<Any> {
        val patient = patientService.createPatient(request.firstName, request.lastName, request.address)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{patientId}")
                .buildAndExpand(patient.personId)
                .toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{patientId}")
    fun findById(@PathVariable patientId: String): PatientResponse =
            patientService.getPatient(UUID.fromString(patientId)).let {
                PatientResponse(
                        it.personId.toString(),
                        it.firstName,
                        it.lastName,
                        it.address)
            }

    @PutMapping("/{patientId}")
    fun update(@PathVariable patientId: String, @RequestBody request: UpdatePatientRequest): ResponseEntity<String> {
        patientService.updatePatient(Patient(
                UUID.fromString(patientId),
                request.firstName,
                request.lastName,
                request.address
        ))

        return ResponseEntity.ok("Patient updated")
    }

    @PatchMapping("/{patientId}")
    fun modify(@PathVariable patientId: String, @RequestBody request: ModifyPatientRequest): ResponseEntity<String> {
        val patient = patientService.getPatient(UUID.fromString(patientId))
        request.firstName?.let { patient.firstName = it }
        request.lastName?.let { patient.lastName = it }
        request.address?.let { patient.address = it }

        patientService.updatePatient(patient)

        return ResponseEntity.ok("Patient updated")
    }

    @ExceptionHandler(PatientNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlePatientNotFound(patientNotFoundException: PatientNotFoundException) {
        log.debug(patientNotFoundException.message)
    }

}