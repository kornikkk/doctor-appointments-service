package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.Patient
import pl.kornikkk.doctorappointments.domain.repository.PatientRepository
import java.util.*

class CrudPatientRepository(private val crudRepository: SpringDataCrudPatientRepository) : PatientRepository {
    override fun findById(id: UUID) =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun save(patient: Patient): Patient =
            crudRepository.save(patient.toEntity()).toDomain()

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }
}