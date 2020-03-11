package pl.kornikkk.doctorappointments.domain.patient

import pl.kornikkk.doctorappointments.domain.commons.events.EventPublisher
import java.util.*

class PatientServiceImpl(
        private val patientRepository: PatientRepository,
        private val eventPublisher: EventPublisher
) : PatientService {

    override fun get(id: UUID): Patient =
            patientRepository.findById(id) ?: throw PatientNotFoundException(id)

    override fun findAll(): List<Patient> =
            patientRepository.findAll()

    override fun existsById(id: UUID): Boolean =
            patientRepository.existsById(id)

    override fun create(firstName: String, lastName: String, address: Address) =
            patientRepository.save(Patient(firstName, lastName, address)).id!!

    override fun update(patient: Patient) {
        patientRepository.save(patient)
    }

    override fun delete(id: UUID) {
        patientRepository.deleteById(id)
        eventPublisher.send(PatientDeletedEvent(id))
    }
}