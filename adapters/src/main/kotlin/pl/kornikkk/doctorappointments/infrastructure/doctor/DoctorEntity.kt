package pl.kornikkk.doctorappointments.infrastructure.doctor

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "doctor")
class DoctorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID?,

        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String,

        var specialization: String
)