package pl.kornikkk.doctorappointments.domain.doctor

import pl.kornikkk.doctorappointments.domain.commons.events.EventPublisher
import java.util.*

class DoctorServiceImpl(
        private val doctorRepository: DoctorRepository,
        private val eventPublisher: EventPublisher
) : DoctorService {

    override fun get(id: UUID) =
            doctorRepository.findById(id) ?: throw DoctorNotFoundException(id)

    override fun existsById(id: UUID): Boolean =
            doctorRepository.existsById(id)

    override fun create(firstName: String, lastName: String, specialization: String) =
            doctorRepository.save(Doctor(firstName, lastName, specialization)).id!!

    override fun update(doctor: Doctor) {
        doctorRepository.save(doctor)
    }

    override fun delete(id: UUID) {
        doctorRepository.deleteById(id)
        eventPublisher.send(DoctorDeletedEvent(id))
    }

}