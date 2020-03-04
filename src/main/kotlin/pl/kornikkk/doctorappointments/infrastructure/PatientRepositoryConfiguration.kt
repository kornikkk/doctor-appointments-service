package pl.kornikkk.doctorappointments.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.PatientRepository

@Configuration
class PatientRepositoryConfiguration {

    @Bean
    fun patientRepository(patientCrudRepository: PatientCrudRepository): PatientRepository =
            PatientRepositoryImpl(patientCrudRepository)

}