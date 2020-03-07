package pl.kornikkk.doctorappointments.domain

import java.util.*

class Doctor(doctorId: UUID, firstName: String, lastName: String) :
        Person(doctorId, firstName, lastName) {

    constructor(firstName: String, lastName: String) :
            this(UUID.randomUUID(), firstName, lastName)

}