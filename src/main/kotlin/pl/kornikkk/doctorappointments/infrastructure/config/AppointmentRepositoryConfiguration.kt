package pl.kornikkk.doctorappointments.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.repository.AppointmentRepository
import pl.kornikkk.doctorappointments.infrastructure.repository.jpa.CrudAppointmentRepository
import pl.kornikkk.doctorappointments.infrastructure.repository.jpa.SpringDataCrudAppointmentRepository

@Configuration
class AppointmentRepositoryConfiguration {

    @Bean
    fun appointmentRepository(appointmentCrudRepository: SpringDataCrudAppointmentRepository): AppointmentRepository =
            CrudAppointmentRepository(appointmentCrudRepository)

}