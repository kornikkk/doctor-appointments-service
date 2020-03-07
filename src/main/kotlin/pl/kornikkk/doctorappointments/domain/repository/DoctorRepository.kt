package pl.kornikkk.doctorappointments.domain.repository

import pl.kornikkk.doctorappointments.domain.Doctor
import java.util.*

interface DoctorRepository {
    fun findById(id: UUID): Doctor?
    fun save(doctor: Doctor): Doctor
}