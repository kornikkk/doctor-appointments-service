package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "patient")
class PatientEntity(
        id: UUID?,
        firstName: String,
        lastName: String,

        @Column(nullable = false)
        var address: String
) : PersonEntity(id, firstName, lastName)