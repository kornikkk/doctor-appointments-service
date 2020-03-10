package pl.kornikkk.doctorappointments.domain

import java.util.*

class Patient(id: UUID?, firstName: String, lastName: String, var address: String) :
        Person(id, firstName, lastName) {

    constructor(firstName: String, lastName: String, address: String) :
            this(null, firstName, lastName, address)
}