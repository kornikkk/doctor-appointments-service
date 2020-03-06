package pl.kornikkk.doctorappointments.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import pl.kornikkk.doctorappointments.domain.service.PatientService
import pl.kornikkk.doctorappointments.domain.service.PatientServiceImpl

@Configuration
class PatientConfiguration {

    @Bean
    fun patientService(patientRepository: PatientRepository): PatientService =
            PatientServiceImpl(patientRepository)

}