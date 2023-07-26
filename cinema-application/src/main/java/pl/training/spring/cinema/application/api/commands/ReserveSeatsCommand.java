package pl.training.spring.cinema.application.api.commands;

import java.util.List;

import pl.spring.training.cinema.domain.reservation.ReservationNumber;

public record ReserveSeatsCommand(String seanceId, ReservationNumber reservationNumber, List<Integer> chosenSeats) {

}
