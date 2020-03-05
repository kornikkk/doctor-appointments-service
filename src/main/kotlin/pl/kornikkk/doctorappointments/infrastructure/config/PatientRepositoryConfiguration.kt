package pl.kornikkk.doctorappointments.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import pl.kornikkk.doctorappointments.infrastructure.repository.jpa.CrudPatientRepository
import pl.kornikkk.doctorappointments.infrastructure.repository.jpa.SpringDataCrudPatientRepository

@Configuration
class PatientRepositoryConfiguration {

    @Bean
    fun patientRepository(patientCrudRepository: SpringDataCrudPatientRepository): PatientRepository =
            CrudPatientRepository(patientCrudRepository)

}