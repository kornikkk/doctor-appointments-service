package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Patient
import java.util.*

interface PatientService {
    fun get(id: UUID): Patient
    fun existsById(id: UUID): Boolean
    fun create(firstName: String, lastName: String, address: String): UUID
    fun update(patient: Patient)
    fun delete(id: UUID)
}