package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import java.util.*

class CrudPatientRepository(private val crudRepository: SpringDataCrudPatientRepository) : PatientRepository {
    override fun findByPersonId(id: UUID) =
            crudRepository.findByPersonId(id.toString())?.toDomain()

    override fun save(patient: Patient): Patient {
        val entityId = crudRepository.findByPersonId(patient.personId.toString())?.id
        return crudRepository.save(patient.toEntity(entityId)).toDomain()
    }
}