package pl.kornikkk.doctorappointments.domain

import java.util.*

interface PatientRepository {
    fun findById(id: UUID): Patient?
    fun save(patient: Patient): Patient
}