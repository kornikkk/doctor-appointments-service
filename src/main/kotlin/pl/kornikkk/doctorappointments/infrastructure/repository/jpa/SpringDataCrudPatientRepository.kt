package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataCrudPatientRepository : CrudRepository<PatientEntity, Long> {
    fun findByPersonId(personId: String): PatientEntity?
}