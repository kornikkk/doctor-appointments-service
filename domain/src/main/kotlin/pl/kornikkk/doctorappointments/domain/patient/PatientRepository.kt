package pl.kornikkk.doctorappointments.domain.patient

import java.util.*

interface PatientRepository {
    fun findById(id: UUID): Patient?
    fun existsById(id: UUID): Boolean
    fun save(patient: Patient): Patient
    fun deleteById(id: UUID)
}