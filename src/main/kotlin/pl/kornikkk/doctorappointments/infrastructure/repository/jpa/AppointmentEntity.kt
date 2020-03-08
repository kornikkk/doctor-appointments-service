package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "appointments")
data class AppointmentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "patient_id")
        val patient: PatientEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "doctor_id")
        val doctor: DoctorEntity,

        @Column(nullable = false)
        val location: String,

        @Column(nullable = false, columnDefinition = "TIMESTAMP")
        val dateTime: LocalDateTime
)