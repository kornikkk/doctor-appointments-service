package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Patient
import java.util.*

interface PatientService {
    fun create(firstName: String, lastName: String, address: String): Patient
    fun update(patient: Patient)
    fun get(id: UUID): Patient
    fun delete(id: UUID)
}