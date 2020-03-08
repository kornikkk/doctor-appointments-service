package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "doctor")
class DoctorEntity(
        id: UUID?,
        firstName: String,
        lastName: String,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "doctor")
        var appointments: MutableList<AppointmentEntity> = mutableListOf()

) : PersonEntity(id, firstName, lastName)