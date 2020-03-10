package pl.kornikkk.doctorappointments.application.events

import org.springframework.context.event.EventListener
import pl.kornikkk.doctorappointments.domain.events.DoctorDeletedEvent
import pl.kornikkk.doctorappointments.domain.service.AppointmentService

class DoctorEventListener(private val appointmentService: AppointmentService) {

    @EventListener
    fun handle(event: DoctorDeletedEvent) {
        appointmentService.deleteDoctorAppointments(event.doctorId)
    }
}