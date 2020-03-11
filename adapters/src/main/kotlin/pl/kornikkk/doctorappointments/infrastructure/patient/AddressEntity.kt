package pl.kornikkk.doctorappointments.infrastructure.patient

import javax.persistence.Embeddable

@Embeddable
class AddressEntity(var street: String, var city: String)