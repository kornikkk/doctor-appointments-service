package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import io.kotlintest.shouldBe
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.spring.SpringListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@DataJpaTest
class SpringDataCrudAppointmentRepositoryTest : AnnotationSpec() {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    lateinit var patientRepository: SpringDataCrudPatientRepository

    @Autowired
    lateinit var doctorRepository: SpringDataCrudDoctorRepository

    @Autowired
    lateinit var appointmentRepository: SpringDataCrudAppointmentRepository

    @Test
    fun `Should find appointment for patient and doctor when querying for actual time`() {
        //given
        val patient = patientRepository.save(PatientEntity(null, "Test", "Patient", "Address"))
        val doctor = doctorRepository.save(DoctorEntity(null, "Test", "Doctor"))

        val dateTime = LocalDateTime.of(2020, 3, 8, 9, 30)

        //when
        appointmentRepository.save(AppointmentEntity(null, patient, doctor, "Test location", dateTime))

        //then
        appointmentRepository.existsAtDateTime(patient.id!!, doctor.id!!, dateTime) shouldBe true
    }

    @Test
    fun `Should not find appointment for patient and doctor when querying for another time`() {
        //given
        val patient = patientRepository.save(PatientEntity(null, "Test", "Patient", "Address"))
        val doctor = doctorRepository.save(DoctorEntity(null, "Test", "Doctor"))

        val dateTime = LocalDateTime.of(2020, 3, 8, 9, 30)
        val anotherDateTime = LocalDateTime.of(2020, 3, 8, 7, 30)

        //when
        appointmentRepository.save(AppointmentEntity(null, patient, doctor, "Test location", dateTime))

        //then
        appointmentRepository.existsAtDateTime(patient.id!!, doctor.id!!, anotherDateTime) shouldBe false
    }
}