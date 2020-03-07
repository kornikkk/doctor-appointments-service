package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Doctor
import java.util.*

interface DoctorService {
    fun createDoctor(firstName: String, lastName: String): Doctor
    fun updateDoctor(doctor: Doctor): Doctor
    fun getDoctor(id: UUID): Doctor
}