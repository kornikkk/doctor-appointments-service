package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "patients")
data class PatientEntity(
        @Id
        var id: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String,

        @Column(nullable = false)
        var address: String

)