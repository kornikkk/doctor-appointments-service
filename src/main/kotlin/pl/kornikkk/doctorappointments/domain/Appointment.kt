package pl.kornikkk.doctorappointments.domain

import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class Appointment(
        val id: UUID,
        val patient: Patient,
        val doctor: Doctor,
        val date: LocalDate,
        var time: LocalTime) {

    constructor(patient: Patient, doctor: Doctor, date: LocalDate, time: LocalTime) :
            this(UUID.randomUUID(), patient, doctor, date, time)
}