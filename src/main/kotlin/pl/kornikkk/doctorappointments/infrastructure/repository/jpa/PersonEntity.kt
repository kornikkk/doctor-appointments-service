package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import javax.persistence.*

@MappedSuperclass
open class PersonEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var personId: String,

        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String
)