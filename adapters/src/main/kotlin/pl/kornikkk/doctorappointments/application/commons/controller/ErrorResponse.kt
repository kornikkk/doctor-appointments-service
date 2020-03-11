package pl.kornikkk.doctorappointments.application.commons.controller

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(
        val timestamp: LocalDateTime,
        val status: Int,
        val error: String,
        val message: String?,
        val path: String?
)