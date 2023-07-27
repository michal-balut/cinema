package pl.training.spring.cinema.application.output.events;

public interface SeanceApplicationEventsPublisher {

    void publish(ReservationPaidApplicationEvent event);

}
