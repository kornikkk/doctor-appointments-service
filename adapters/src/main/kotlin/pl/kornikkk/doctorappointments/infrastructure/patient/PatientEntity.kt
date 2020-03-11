package pl.kornikkk.doctorappointments.infrastructure.patient

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "patient")
class PatientEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID?,

        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String,

        @Embedded
        var address: AddressEntity
)