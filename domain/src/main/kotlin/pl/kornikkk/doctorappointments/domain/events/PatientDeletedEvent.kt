package pl.kornikkk.doctorappointments.domain.events

import java.util.*

class PatientDeletedEvent(val patientId: UUID) : DomainEvent