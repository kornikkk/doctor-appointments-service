package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SpringDataCrudDoctorRepository : CrudRepository<DoctorEntity, UUID> {
}