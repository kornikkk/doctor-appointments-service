package pl.kornikkk.doctorappointments.domain.repository

import pl.kornikkk.doctorappointments.domain.Doctor
import java.util.*

interface DoctorRepository {
    fun findById(id: UUID): Doctor?
    fun existsById(id: UUID): Boolean
    fun save(doctor: Doctor): Doctor
    fun deleteById(id: UUID)
}