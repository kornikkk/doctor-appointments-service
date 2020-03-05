package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "patients")
class PatientEntity(
        id: Long?,
        personId: String,
        firstName: String,
        lastName: String,

        @Column(nullable = false)
        var address: String

) : PersonEntity(id, personId, firstName, lastName)