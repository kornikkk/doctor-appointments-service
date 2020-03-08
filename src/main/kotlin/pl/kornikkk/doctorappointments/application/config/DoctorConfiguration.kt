package pl.kornikkk.doctorappointments.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import pl.kornikkk.doctorappointments.domain.service.DoctorService
import pl.kornikkk.doctorappointments.domain.service.DoctorServiceImpl

@Configuration
class DoctorConfiguration {

    @Bean
    fun doctorService(doctorRepository: DoctorRepository): DoctorService =
            DoctorServiceImpl(doctorRepository)

}