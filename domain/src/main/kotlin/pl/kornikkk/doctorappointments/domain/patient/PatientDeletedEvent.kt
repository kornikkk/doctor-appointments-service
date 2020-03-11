package pl.kornikkk.doctorappointments.domain.patient

import pl.kornikkk.doctorappointments.domain.commons.events.DomainEvent
import java.util.*

class PatientDeletedEvent(val patientId: UUID) : DomainEvent