package pl.kornikkk.doctorappointments.domain

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