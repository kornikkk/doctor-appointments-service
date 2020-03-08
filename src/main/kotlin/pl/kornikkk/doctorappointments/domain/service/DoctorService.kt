package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Doctor
import java.util.*

interface DoctorService {
    fun create(firstName: String, lastName: String): Doctor
    fun update(doctor: Doctor): Doctor
    fun get(id: UUID): Doctor
    fun delete(id: UUID)
}