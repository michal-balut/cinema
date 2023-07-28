package pl.training.spring.cinema.output;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pl.training.spring.cinema.application.output.events.ReservationPaidApplicationEvent;
import pl.training.spring.cinema.application.output.events.SeanceApplicationEventsPublisher;

@Log
@Service
public class SeanceApplicationEventsPublisherImpl implements SeanceApplicationEventsPublisher {
    @Override
    public void publish(final ReservationPaidApplicationEvent event) {
        log.info(String.format(
            "Payment confirmation email sent to address: %s for reservation number: %s, seance id: %s, seleced places: %s",
            event.email(),
            event.reservationNumber(),
            event.seanceId(),
            event.seats()
        ));
    }
}
