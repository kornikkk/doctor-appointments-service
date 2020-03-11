package pl.kornikkk.doctorappointments.domain.doctor

import java.util.*

interface DoctorRepository {
    fun findById(id: UUID): Doctor?
    fun existsById(id: UUID): Boolean
    fun save(doctor: Doctor): Doctor
    fun deleteById(id: UUID)
}