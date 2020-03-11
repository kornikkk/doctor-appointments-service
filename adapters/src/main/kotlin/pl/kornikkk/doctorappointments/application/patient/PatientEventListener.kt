package pl.kornikkk.doctorappointments.application.patient

import org.springframework.context.event.EventListener
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.patient.PatientDeletedEvent

class PatientEventListener(private val appointmentService: AppointmentService) {

    @EventListener
    fun handle(event: PatientDeletedEvent) {
        appointmentService.deletePatientAppointments(event.patientId)
    }
}