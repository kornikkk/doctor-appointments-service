package pl.kornikkk.doctorappointments.infrastructure.repository.jpa

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "doctor")
class DoctorEntity(
        id: UUID?,
        firstName: String,
        lastName: String
) : PersonEntity(id, firstName, lastName)