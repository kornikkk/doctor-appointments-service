package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "doctors")
data class DoctorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID?,

        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String

)