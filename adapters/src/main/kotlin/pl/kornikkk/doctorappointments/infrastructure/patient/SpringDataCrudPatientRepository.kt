package pl.kornikkk.doctorappointments.infrastructure.patient

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SpringDataCrudPatientRepository : CrudRepository<PatientEntity, UUID> {
}