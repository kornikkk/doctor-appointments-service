package pl.kornikkk.doctorappointments.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import pl.kornikkk.doctorappointments.infrastructure.repository.jpa.CrudDoctorRepository
import pl.kornikkk.doctorappointments.infrastructure.repository.jpa.SpringDataCrudDoctorRepository

@Configuration
class DoctorRepositoryConfiguration {

    @Bean
    fun doctorRepository(doctorCrudRepository: SpringDataCrudDoctorRepository): DoctorRepository =
            CrudDoctorRepository(doctorCrudRepository)

}