package pl.spring.training.cinema.domain.reservation;

import java.util.List;

public record ReservationPaidEvent(String seanceId, ReservationNumber number, List<Integer> seats, String email) {

}
