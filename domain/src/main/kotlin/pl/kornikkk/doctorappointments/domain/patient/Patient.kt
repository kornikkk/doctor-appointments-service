package pl.kornikkk.doctorappointments.domain.patient

import java.util.*

class Patient(val id: UUID?, var firstName: String, var lastName: String, var address: Address) {

    constructor(firstName: String, lastName: String, address: Address) :
            this(null, firstName, lastName, address)
}