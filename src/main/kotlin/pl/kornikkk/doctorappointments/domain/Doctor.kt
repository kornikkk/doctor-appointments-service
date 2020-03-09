package pl.kornikkk.doctorappointments.domain

import java.util.*

class Doctor(id: UUID?, firstName: String, lastName: String) :
        Person(id, firstName, lastName) {

    constructor(firstName: String, lastName: String) :
            this(null, firstName, lastName)

}