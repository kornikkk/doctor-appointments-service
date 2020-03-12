package pl.kornikkk.doctorappointments.application.appointment

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import pl.kornikkk.doctorappointments.application.commons.controller.ErrorResponse
import pl.kornikkk.doctorappointments.application.commons.controller.createErrorResponse
import pl.kornikkk.doctorappointments.application.commons.utils.Logging
import pl.kornikkk.doctorappointments.application.commons.utils.logger
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentNotFoundException
import pl.kornikkk.doctorappointments.domain.appointment.ConflictingAppointmentException

@ControllerAdvice
class AppointmentExceptionHandler : Logging {

    private val log = logger()

    @ExceptionHandler(AppointmentNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: AppointmentNotFoundException): ResponseEntity<ErrorResponse> {
        log.debug(exception.message)
        return createErrorResponse(exception, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ConflictingAppointmentException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleConflictingAppointment(exception: ConflictingAppointmentException): ResponseEntity<ErrorResponse> {
        log.debug(exception.message)
        return createErrorResponse(exception, HttpStatus.UNPROCESSABLE_ENTITY)
    }
}