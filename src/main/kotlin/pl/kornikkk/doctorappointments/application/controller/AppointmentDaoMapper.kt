package pl.kornikkk.doctorappointments.application.controller

import pl.kornikkk.doctorappointments.application.controller.response.AppointmentResource
import pl.kornikkk.doctorappointments.domain.Appointment

fun Appointment.toResource() = AppointmentResource(id!!, patientId, doctorId, date, time)
