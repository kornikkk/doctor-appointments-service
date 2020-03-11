package pl.kornikkk.doctorappointments.domain.doctor

import java.util.*

class Doctor(val id: UUID?, var firstName: String, var lastName: String) {

    constructor(firstName: String, lastName: String) :
            this(null, firstName, lastName)
}