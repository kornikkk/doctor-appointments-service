package pl.kornikkk.doctorappointments.application.commons.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime

fun <T> createdWithLocationResponse(id: T): ResponseEntity<Any> {
    val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri()
    return ResponseEntity.created(location).build()
}

fun createErrorResponse(exception: Exception, status: HttpStatus): ResponseEntity<ErrorResponse> {
    val path = ServletUriComponentsBuilder.fromCurrentRequest().build().path
    val body = ErrorResponse(LocalDateTime.now(), status.value(), status.reasonPhrase, exception.localizedMessage, path)
    return ResponseEntity(body, status)
}