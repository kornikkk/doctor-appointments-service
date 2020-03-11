package pl.kornikkk.doctorappointments.application.patient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.commons.events.EventPublisher
import pl.kornikkk.doctorappointments.domain.patient.PatientRepository
import pl.kornikkk.doctorappointments.domain.patient.PatientService
import pl.kornikkk.doctorappointments.domain.patient.PatientServiceImpl
import pl.kornikkk.doctorappointments.infrastructure.patient.CrudPatientRepository
import pl.kornikkk.doctorappointments.infrastructure.patient.SpringDataCrudPatientRepository

@Configuration
class PatientConfiguration {

    @Bean
    fun patientRepository(patientCrudRepository: SpringDataCrudPatientRepository): PatientRepository =
            CrudPatientRepository(patientCrudRepository)

    @Bean
    fun patientService(patientRepository: PatientRepository, eventPublisher: EventPublisher): PatientService =
            PatientServiceImpl(patientRepository, eventPublisher)

    @Bean
    fun patientEventListener(appointmentService: AppointmentService): PatientEventListener =
            PatientEventListener(appointmentService)

}