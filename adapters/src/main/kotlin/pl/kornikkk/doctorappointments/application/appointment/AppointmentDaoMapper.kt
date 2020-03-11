package pl.kornikkk.doctorappointments.application.appointment

import pl.kornikkk.doctorappointments.domain.appointment.Appointment

fun Appointment.toResource() = AppointmentResource(id!!, patientId, doctorId, date, time)
