package pl.kornikkk.doctorappointments.application.commons.utils

import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

fun <T> createdWithLocationResponse(id: T): ResponseEntity<Any> {
    val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri()
    return ResponseEntity.created(location).build()
}

