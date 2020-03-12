package pl.kornikkk.doctorappointments.application.doctor

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import pl.kornikkk.doctorappointments.application.commons.controller.ErrorResponse
import pl.kornikkk.doctorappointments.application.commons.controller.createErrorResponse
import pl.kornikkk.doctorappointments.application.commons.utils.Logging
import pl.kornikkk.doctorappointments.application.commons.utils.logger
import pl.kornikkk.doctorappointments.domain.doctor.DoctorNotFoundException

@ControllerAdvice
class DoctorExceptionHandler : Logging {

    private val log = logger()

    @ExceptionHandler(DoctorNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: DoctorNotFoundException): ResponseEntity<ErrorResponse> {
        log.debug(exception.message)
        return createErrorResponse(exception, HttpStatus.NOT_FOUND)
    }

}