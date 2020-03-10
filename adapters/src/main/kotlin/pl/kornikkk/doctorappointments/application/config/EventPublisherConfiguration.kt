package pl.kornikkk.doctorappointments.application.config

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.kornikkk.doctorappointments.domain.events.DomainEvent
import pl.kornikkk.doctorappointments.domain.events.EventPublisher

@Configuration
class EventPublisherConfiguration {

    @Bean
    fun eventPublisher(applicationEventPublisher: ApplicationEventPublisher): EventPublisher = object : EventPublisher {
        override fun send(event: DomainEvent) {
            applicationEventPublisher.publishEvent(event)
        }
    }

}