package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "appointment")
class AppointmentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID?,

        @Column(nullable = false)
        var patientId: UUID,

        @Column(nullable = false)
        var doctorId: UUID,

        @Column(nullable = false)
        var location: String,

        @Column(nullable = false, columnDefinition = "TIMESTAMP")
        var dateTime: LocalDateTime
)