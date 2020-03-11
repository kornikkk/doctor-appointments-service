package pl.kornikkk.doctorappointments.infrastructure.doctor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.doctor.DoctorRepository

@Configuration
class DoctorRepositoryConfiguration {

    @Bean
    fun doctorRepository(doctorCrudRepository: SpringDataCrudDoctorRepository): DoctorRepository =
            CrudDoctorRepository(doctorCrudRepository)

}