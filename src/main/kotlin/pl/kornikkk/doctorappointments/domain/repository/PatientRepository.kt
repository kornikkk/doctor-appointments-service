package pl.kornikkk.doctorappointments.domain.repository

import pl.kornikkk.doctorappointments.domain.Patient
import java.util.*

interface PatientRepository {
    fun findByPersonId(id: UUID): Patient?
    fun save(patient: Patient): Patient
}