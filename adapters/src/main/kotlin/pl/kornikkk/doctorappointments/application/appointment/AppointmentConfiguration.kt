package pl.kornikkk.doctorappointments.application.appointment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentRepository
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentServiceImpl
import pl.kornikkk.doctorappointments.domain.doctor.DoctorService
import pl.kornikkk.doctorappointments.domain.patient.PatientService

@Configuration
class AppointmentConfiguration {

    @Bean
    fun appointmentService(appointmentRepository: AppointmentRepository, patientService: PatientService, doctorService: DoctorService): AppointmentService =
            AppointmentServiceImpl(appointmentRepository, patientService, doctorService)

}