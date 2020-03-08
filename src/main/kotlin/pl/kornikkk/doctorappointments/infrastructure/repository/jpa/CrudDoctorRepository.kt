package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import org.springframework.data.repository.findByIdOrNull
import pl.kornikkk.doctorappointments.domain.Doctor
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class CrudDoctorRepository(private val crudRepository: SpringDataCrudDoctorRepository) : DoctorRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findById(id: UUID) =
            crudRepository.findByIdOrNull(id)?.toDomain()

    override fun save(doctor: Doctor): Doctor =
            crudRepository.save(doctor.toEntity(entityManager)).toDomain()

    override fun deleteById(id: UUID) {
        crudRepository.deleteById(id)
    }
}