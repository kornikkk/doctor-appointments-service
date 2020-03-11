package pl.kornikkk.doctorappointments.infrastructure.doctor

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.doctor.Doctor
import pl.kornikkk.doctorappointments.domain.doctor.DoctorRepository
import java.util.*

class CrudDoctorRepository(private val crudRepository: SpringDataCrudDoctorRepository) : DoctorRepository {

    override fun findById(id: UUID): Doctor? =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun findAll(): List<Doctor> =
            crudRepository.findAll().map(DoctorEntity::toDomain)

    override fun existsById(id: UUID): Boolean =
            crudRepository.existsById(id)

    override fun save(doctor: Doctor): Doctor =
            crudRepository.save(doctor.toEntity()).toDomain()

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }
}