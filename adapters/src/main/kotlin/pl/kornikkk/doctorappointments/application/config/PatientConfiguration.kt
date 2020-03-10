package pl.kornikkk.doctorappointments.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional
import pl.kornikkk.doctorappointments.application.events.PatientEventListener
import pl.kornikkk.doctorappointments.domain.events.EventPublisher
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import pl.kornikkk.doctorappointments.domain.service.AppointmentService
import pl.kornikkk.doctorappointments.domain.service.PatientService
import pl.kornikkk.doctorappointments.domain.service.PatientServiceImpl

@Configuration
class PatientConfiguration {

    @Bean
    @Transactional
    fun patientService(patientRepository: PatientRepository, eventPublisher: EventPublisher): PatientService =
            PatientServiceImpl(patientRepository, eventPublisher)

    @Bean
    fun patientEventListener(appointmentService: AppointmentService): PatientEventListener =
            PatientEventListener(appointmentService)

}