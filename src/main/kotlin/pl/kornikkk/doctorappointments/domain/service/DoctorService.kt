package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Doctor
import java.util.*

interface DoctorService {
    fun create(firstName: String, lastName: String): UUID
    fun update(doctor: Doctor)
    fun get(id: UUID): Doctor
    fun delete(id: UUID)
}