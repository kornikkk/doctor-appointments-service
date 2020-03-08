package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "appointment")
class AppointmentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Access(AccessType.PROPERTY)
        var id: UUID?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "patient_id")
        var patient: PatientEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "doctor_id")
        var doctor: DoctorEntity,

        @Column(nullable = false)
        var location: String,

        @Column(nullable = false, columnDefinition = "TIMESTAMP")
        var dateTime: LocalDateTime
)