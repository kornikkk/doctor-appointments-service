package pl.kornikkk.doctorappointments.domain.doctor

import pl.kornikkk.doctorappointments.domain.commons.events.DomainEvent
import java.util.*

class DoctorDeletedEvent(val doctorId: UUID) : DomainEvent