package pl.kornikkk.doctorappointments.domain.patient

import java.util.*

interface PatientService {
    fun get(id: UUID): Patient
    fun existsById(id: UUID): Boolean
    fun create(firstName: String, lastName: String, address: Address): UUID
    fun update(patient: Patient)
    fun delete(id: UUID)
}