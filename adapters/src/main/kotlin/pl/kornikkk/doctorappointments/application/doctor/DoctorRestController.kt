package pl.kornikkk.doctorappointments.application.doctor

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kornikkk.doctorappointments.application.commons.controller.createdWithLocationResponse
import pl.kornikkk.doctorappointments.application.commons.utils.Logging
import pl.kornikkk.doctorappointments.application.commons.utils.logger
import pl.kornikkk.doctorappointments.domain.doctor.Doctor
import pl.kornikkk.doctorappointments.domain.doctor.DoctorService
import java.util.*

@RestController
@RequestMapping("/doctors")
class DoctorRestController(private val doctorService: DoctorService) : Logging {

    private val log = logger()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody request: NewDoctorRequest): ResponseEntity<Any> =
            createdWithLocationResponse(doctorService.create(request.firstName, request.lastName, request.specialization))

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<DoctorResource> =
            doctorService.findAll().map(Doctor::toResource)

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

}