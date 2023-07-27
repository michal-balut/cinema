package pl.training.spring.cinema.application.output.events;

import pl.spring.training.cinema.domain.reservation.ReservationNumber;

public record ReservationPaidApplicationEvent(String seanceId, ReservationNumber reservationNumber) {
}
