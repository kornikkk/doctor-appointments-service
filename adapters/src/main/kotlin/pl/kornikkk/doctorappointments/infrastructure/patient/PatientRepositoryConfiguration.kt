package pl.kornikkk.doctorappointments.infrastructure.patient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.patient.PatientRepository

@Configuration
class PatientRepositoryConfiguration {

    @Bean
    fun patientRepository(patientCrudRepository: SpringDataCrudPatientRepository): PatientRepository =
            CrudPatientRepository(patientCrudRepository)

}