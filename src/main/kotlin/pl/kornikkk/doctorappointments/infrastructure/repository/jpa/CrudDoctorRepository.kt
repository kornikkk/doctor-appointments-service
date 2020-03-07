package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.Doctor
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import java.util.*

class CrudDoctorRepository(private val crudRepository: SpringDataCrudDoctorRepository) : DoctorRepository {
    override fun findById(id: UUID) =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun save(doctor: Doctor): Doctor =
            crudRepository.save(doctor.toEntity()).toDomain()

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }
}