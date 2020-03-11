package pl.kornikkk.doctorappointments.domain.commons.events

interface EventPublisher {
    fun send(event: DomainEvent)
}