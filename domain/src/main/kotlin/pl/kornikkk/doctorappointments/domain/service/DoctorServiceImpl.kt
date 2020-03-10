package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Doctor
import pl.kornikkk.doctorappointments.domain.events.DoctorDeletedEvent
import pl.kornikkk.doctorappointments.domain.events.EventPublisher
import pl.kornikkk.doctorappointments.domain.exception.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import java.util.*

class DoctorServiceImpl(
        private val doctorRepository: DoctorRepository,
        private val eventPublisher: EventPublisher
) : DoctorService {

    override fun get(id: UUID) =
            doctorRepository.findById(id) ?: throw DoctorNotFoundException(id)

    override fun existsById(id: UUID): Boolean =
            doctorRepository.existsById(id)

    override fun create(firstName: String, lastName: String) =
            doctorRepository.save(Doctor(firstName, lastName)).id!!

    override fun update(doctor: Doctor) {
        doctorRepository.save(doctor)
    }

    override fun delete(id: UUID) {
        doctorRepository.deleteById(id)
        eventPublisher.send(DoctorDeletedEvent(id))
    }

}