package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Patient
import java.util.*

interface PatientService {
    fun createPatient(firstName: String, lastName: String, address: String): Patient
    fun updatePatient(patient: Patient)
    fun getPatient(id: UUID): Patient
}