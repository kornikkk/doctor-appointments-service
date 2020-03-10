package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Doctor
import java.util.*

interface DoctorService {
    fun get(id: UUID): Doctor
    fun existsById(id: UUID): Boolean
    fun create(firstName: String, lastName: String): UUID
    fun update(doctor: Doctor)
    fun delete(id: UUID)
}