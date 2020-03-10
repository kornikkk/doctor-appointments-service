package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.*

@MappedSuperclass
class PersonEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID?,

        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String
)