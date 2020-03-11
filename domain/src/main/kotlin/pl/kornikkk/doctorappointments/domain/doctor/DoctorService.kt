package pl.kornikkk.doctorappointments.domain.doctor

import java.util.*

interface DoctorService {
    fun get(id: UUID): Doctor
    fun existsById(id: UUID): Boolean
    fun create(firstName: String, lastName: String, specialization: String): UUID
    fun update(doctor: Doctor)
    fun delete(id: UUID)
}