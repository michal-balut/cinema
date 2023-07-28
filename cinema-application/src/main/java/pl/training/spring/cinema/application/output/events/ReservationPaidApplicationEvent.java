package pl.training.spring.cinema.application.output.events;

import java.util.List;

public record ReservationPaidApplicationEvent(String seanceId, String reservationNumber, List<Integer> seats,
                                              String email) {
}
