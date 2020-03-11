package pl.kornikkk.doctorappointments.application

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.commons.events.DomainEvent
import pl.kornikkk.doctorappointments.domain.commons.events.EventPublisher

@Configuration
class EventPublisherConfiguration {

    @Bean
    fun eventPublisher(applicationEventPublisher: ApplicationEventPublisher): EventPublisher = object : EventPublisher {
        override fun send(event: DomainEvent) {
            applicationEventPublisher.publishEvent(event)
        }
    }

}