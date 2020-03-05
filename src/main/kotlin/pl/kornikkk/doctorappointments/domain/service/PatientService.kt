package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import java.util.*

class PatientService(private val patientRepository: PatientRepository) {

    fun createPatient(firstName: String, lastName: String, address: String): Patient =
            patientRepository.save(Patient(firstName, lastName, address))

    fun updatePatient(patient: Patient) {
        if (patientRepository.findById(patient.personId) == null) {
            throw PatientNotFoundException(patient.personId)
        }
        patientRepository.save(patient)
    }

    fun getPatient(id: UUID): Patient =
            patientRepository.findById(id) ?: throw PatientNotFoundException(id)

}