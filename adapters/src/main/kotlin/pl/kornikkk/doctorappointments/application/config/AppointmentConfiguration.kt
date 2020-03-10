package pl.kornikkk.doctorappointments.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.repository.AppointmentRepository
import pl.kornikkk.doctorappointments.domain.service.AppointmentService
import pl.kornikkk.doctorappointments.domain.service.AppointmentServiceImpl
import pl.kornikkk.doctorappointments.domain.service.DoctorService
import pl.kornikkk.doctorappointments.domain.service.PatientService

@Configuration
class AppointmentConfiguration {

    @Bean
    fun appointmentService(appointmentRepository: AppointmentRepository, patientService: PatientService, doctorService: DoctorService): AppointmentService =
            AppointmentServiceImpl(appointmentRepository, patientService, doctorService)

}