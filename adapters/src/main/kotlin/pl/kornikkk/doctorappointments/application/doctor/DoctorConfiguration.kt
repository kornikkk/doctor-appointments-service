package pl.kornikkk.doctorappointments.application.doctor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.appointment.AppointmentService
import pl.kornikkk.doctorappointments.domain.commons.events.EventPublisher
import pl.kornikkk.doctorappointments.domain.doctor.DoctorRepository
import pl.kornikkk.doctorappointments.domain.doctor.DoctorService
import pl.kornikkk.doctorappointments.domain.doctor.DoctorServiceImpl
import pl.kornikkk.doctorappointments.infrastructure.doctor.CrudDoctorRepository
import pl.kornikkk.doctorappointments.infrastructure.doctor.SpringDataCrudDoctorRepository

@Configuration
class DoctorConfiguration {

    @Bean
    fun doctorRepository(doctorCrudRepository: SpringDataCrudDoctorRepository): DoctorRepository =
            CrudDoctorRepository(doctorCrudRepository)

    @Bean
    fun doctorService(doctorRepository: DoctorRepository, eventPublisher: EventPublisher): DoctorService =
            DoctorServiceImpl(doctorRepository, eventPublisher)

    @Bean
    fun doctorEventListener(appointmentService: AppointmentService): DoctorEventListener =
            DoctorEventListener(appointmentService)

}