package pl.kornikkk.doctorappointments.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.PatientRepository
import pl.kornikkk.doctorappointments.domain.PatientService

@Configuration
class PatientConfiguration {

    @Bean
    fun patientService(patientRepository: PatientRepository): PatientService =
            PatientService(patientRepository)

}