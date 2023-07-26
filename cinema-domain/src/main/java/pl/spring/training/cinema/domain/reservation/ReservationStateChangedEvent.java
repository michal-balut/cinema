package pl.spring.training.cinema.domain.reservation;

public record ReservationStateChangedEvent(ReservationNumber number, ReservationStatus status) {

}
