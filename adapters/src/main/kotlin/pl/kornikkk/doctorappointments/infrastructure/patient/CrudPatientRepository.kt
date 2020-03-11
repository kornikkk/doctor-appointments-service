package pl.kornikkk.doctorappointments.infrastructure.patient

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.patient.Patient
import pl.kornikkk.doctorappointments.domain.patient.PatientRepository
import java.util.*

class CrudPatientRepository(private val crudRepository: SpringDataCrudPatientRepository) : PatientRepository {

    override fun findById(id: UUID): Patient? =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun existsById(id: UUID): Boolean =
            crudRepository.existsById(id)

    override fun save(patient: Patient): Patient =
            crudRepository.save(patient.toEntity()).toDomain()

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }
}