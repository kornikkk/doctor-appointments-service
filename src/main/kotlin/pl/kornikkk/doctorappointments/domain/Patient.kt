package pl.kornikkk.doctorappointments.domain

import java.util.*

class Patient(patientId: UUID, firstName: String, lastName: String, var address: String) :
        Person(patientId, firstName, lastName) {

    constructor(firstName: String, lastName: String, address: String) :
            this(UUID.randomUUID(), firstName, lastName, address)
}