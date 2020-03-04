package pl.kornikkk.doctorappointments.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DoctorAppointmentsServiceApplication

fun main(args: Array<String>) {
	runApplication<DoctorAppointmentsServiceApplication>(*args)
}
