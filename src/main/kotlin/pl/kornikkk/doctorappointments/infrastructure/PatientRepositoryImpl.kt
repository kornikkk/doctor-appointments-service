package pl.kornikkk.doctorappointments.infrastructure

import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.PatientRepository
import java.util.*

class PatientRepositoryImpl(private val patientCrudRepository: PatientCrudRepository) : PatientRepository {
    override fun findById(id: UUID) =
            patientCrudRepository.findByPersonId(id.toString())?.toDomain()

    override fun save(patient: Patient): Patient {
        val entityId = patientCrudRepository.findByPersonId(patient.personId.toString())?.id
        return patientCrudRepository.save(patient.toEntity(entityId)).toDomain()
    }
}