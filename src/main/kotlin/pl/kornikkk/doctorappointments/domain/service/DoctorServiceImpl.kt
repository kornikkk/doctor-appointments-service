package pl.kornikkk.doctorappointments.domain.service

import pl.kornikkk.doctorappointments.domain.Doctor
import pl.kornikkk.doctorappointments.domain.exception.DoctorNotFoundException
import pl.kornikkk.doctorappointments.domain.repository.DoctorRepository
import java.util.*

class DoctorServiceImpl(private val doctorRepository: DoctorRepository) : DoctorService {

    override fun createDoctor(firstName: String, lastName: String) =
            doctorRepository.save(Doctor(firstName, lastName))

    override fun updateDoctor(doctor: Doctor): Doctor =
            doctorRepository.save(doctor)

    override fun getDoctor(id: UUID) =
            doctorRepository.findById(id) ?: throw DoctorNotFoundException(id)

}