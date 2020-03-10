package pl.kornikkk.doctorappointments.domain.events

import java.util.*

class DoctorDeletedEvent(val doctorId: UUID) : DomainEvent