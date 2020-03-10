package pl.kornikkk.doctorappointments.domain.repository

import pl.kornikkk.doctorappointments.domain.Patient
import java.util.*

interface PatientRepository {
    fun findById(id: UUID): Patient?
    fun existsById(id: UUID): Boolean
    fun save(patient: Patient): Patient
    fun deleteById(id: UUID)
}