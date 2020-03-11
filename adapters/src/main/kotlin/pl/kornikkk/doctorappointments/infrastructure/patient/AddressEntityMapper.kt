package pl.kornikkk.doctorappointments.infrastructure.patient

import pl.kornikkk.doctorappointments.domain.patient.Address

fun AddressEntity.toDomain() = Address(street, city)

fun Address.toEntity() = AddressEntity(street, city)