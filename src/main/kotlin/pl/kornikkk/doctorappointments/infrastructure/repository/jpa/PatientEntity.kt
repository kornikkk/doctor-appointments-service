package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "patient")
class PatientEntity(
        id: UUID?,
        firstName: String,
        lastName: String,

        @Column(nullable = false)
        var address: String,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "patient")
        var appointments: MutableList<AppointmentEntity> = mutableListOf()

) : PersonEntity(id, firstName, lastName)