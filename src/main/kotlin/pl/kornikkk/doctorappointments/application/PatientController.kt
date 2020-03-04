package pl.kornikkk.doctorappointments.application

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.kornikkk.doctorappointments.domain.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.PatientService
import java.util.*

@RestController
@RequestMapping("/patients")
class PatientController(private val patientService: PatientService) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun newPatient(@RequestBody request: NewPatientRequest): ResponseEntity<*> {
        val patient = patientService.createPatient(request.firstName, request.lastName, request.address)

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{patientId}")
                .buildAndExpand(patient.personId)
                .toUri()
        ).build<Any>()
    }

    @GetMapping("/{patientId}")
    fun getPatient(@PathVariable patientId: String): ResponseEntity<PatientDto> =
            ResponseEntity.ok(patientService.getPatient(UUID.fromString(patientId)).toDto())

    @ExceptionHandler(PatientNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlePatientNotFound(patientNotFoundException: PatientNotFoundException) {
        log.debug(patientNotFoundException.message)
    }

}