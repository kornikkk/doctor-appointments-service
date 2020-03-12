package pl.kornikkk.doctorappointments.application.patient

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import pl.kornikkk.doctorappointments.application.commons.controller.ErrorResponse
import pl.kornikkk.doctorappointments.application.commons.controller.createErrorResponse
import pl.kornikkk.doctorappointments.application.commons.utils.Logging
import pl.kornikkk.doctorappointments.application.commons.utils.logger
import pl.kornikkk.doctorappointments.domain.patient.PatientNotFoundException

@ControllerAdvice
class PatientExceptionHandler : Logging {

    private val log = logger()

    @ExceptionHandler(PatientNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: PatientNotFoundException): ResponseEntity<ErrorResponse> {
        log.debug(exception.message)
        return createErrorResponse(exception, HttpStatus.NOT_FOUND)
    }

}