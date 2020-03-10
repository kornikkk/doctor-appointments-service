package pl.kornikkk.doctorappointments.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.application.events.DoctorEventListener
import pl.kornikkk.doctorappointments.domain.events.EventPublisher
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import pl.kornikkk.doctorappointments.domain.service.AppointmentService
import pl.kornikkk.doctorappointments.domain.service.DoctorService
import pl.kornikkk.doctorappointments.domain.service.DoctorServiceImpl

@Configuration
class DoctorConfiguration {

    @Bean
    fun doctorService(doctorRepository: DoctorRepository, eventPublisher: EventPublisher): DoctorService =
            DoctorServiceImpl(doctorRepository, eventPublisher)

    @Bean
    fun doctorEventListener(appointmentService: AppointmentService): DoctorEventListener =
            DoctorEventListener(appointmentService)

}