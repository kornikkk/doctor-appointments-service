package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.exception.PatientNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import java.util.*

class PatientServiceImpl(private val patientRepository: PatientRepository) : PatientService {

    override fun create(firstName: String, lastName: String, address: String) =
            patientRepository.save(Patient(firstName, lastName, address)).id!!

    override fun update(patient: Patient) {
        patientRepository.save(patient)
    }

    override fun get(id: UUID): Patient =
            patientRepository.findById(id) ?: throw PatientNotFoundException(id)

    override fun delete(id: UUID) {
        patientRepository.deleteById(id)
    }
}