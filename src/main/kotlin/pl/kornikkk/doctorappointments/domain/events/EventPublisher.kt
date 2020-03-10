package pl.kornikkk.doctorappointments.domain.events

interface EventPublisher {
    fun send(event: DomainEvent)
}