package pl.kornikkk.doctorappointments.application.doctor

import org.springframework.context.event.EventListener
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.doctor.DoctorDeletedEvent

class DoctorEventListener(private val appointmentService: AppointmentService) {

    @EventListener
    fun handle(event: DoctorDeletedEvent) {
        appointmentService.deleteDoctorAppointments(event.doctorId)
    }
}