package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import java.util.*

class PatientServiceImpl(private val patientRepository: PatientRepository) : PatientService {

    override fun createPatient(firstName: String, lastName: String, address: String): Patient =
            patientRepository.save(Patient(firstName, lastName, address))

    override fun updatePatient(patient: Patient) {
        if (patientRepository.findById(patient.personId) == null) {
            throw PatientNotFoundException(patient.personId)
        }
        patientRepository.save(patient)
    }

    override fun getPatient(id: UUID): Patient =
            patientRepository.findById(id) ?: throw PatientNotFoundException(id)

}