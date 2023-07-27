package pl.spring.training.cinema.domain.reservation;

public record ReservationPaidEvent(String seanceId, ReservationNumber number) {

}
