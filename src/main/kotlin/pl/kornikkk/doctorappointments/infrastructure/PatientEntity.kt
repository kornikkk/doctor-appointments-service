package pl.kornikkk.doctorappointments.infrastructure

import javax.persistence.*

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