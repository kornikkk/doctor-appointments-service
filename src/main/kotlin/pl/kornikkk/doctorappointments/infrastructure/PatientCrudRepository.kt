package pl.kornikkk.doctorappointments.infrastructure

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.kornikkk.doctorappointments.domain.Patient

@Repository
interface PatientCrudRepository : CrudRepository<PatientEntity, Long> {
    fun findByPersonId(personId: String): PatientEntity?
}