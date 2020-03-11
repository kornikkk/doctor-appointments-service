package pl.kornikkk.doctorappointments.infrastructure.appointment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentRepository

@Configuration
class AppointmentRepositoryConfiguration {

    @Bean
    fun appointmentRepository(appointmentCrudRepository: SpringDataCrudAppointmentRepository): AppointmentRepository =
            CrudAppointmentRepository(appointmentCrudRepository)

}