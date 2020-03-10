package pl.kornikkk.doctorappointments.application.events

import org.springframework.context.event.EventListener
import pl.kornikkk.doctorappointments.domain.events.PatientDeletedEvent
import pl.kornikkk.doctorappointments.domain.service.AppointmentService

class PatientEventListener(private val appointmentService: AppointmentService) {

    @EventListener
    fun handle(event: PatientDeletedEvent) {
        appointmentService.deletePatientAppointments(event.patientId)
    }
}