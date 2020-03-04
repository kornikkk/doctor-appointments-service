package pl.kornikkk.doctorappointments.domain

import java.util.*

class Doctor(doctorId: UUID,
             firstName: String,
             lastName: String,
             val specialization: Specialization) :
        Person(doctorId, firstName, lastName) {

    constructor(firstName: String, lastName: String, specialization: Specialization) :
            this(UUID.randomUUID(), firstName, lastName, specialization)

}