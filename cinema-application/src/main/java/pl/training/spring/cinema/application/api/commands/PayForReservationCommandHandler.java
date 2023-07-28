package pl.training.spring.cinema.application.api.commands;

import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.training.spring.cinema.application.seance.SeanceService;

public class PayForReservationCommandHandler {

    private final SeanceService seanceService;

    public PayForReservationCommandHandler(final SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    public void handle(PayForReservationCommand command) {
        seanceService.payForReservation(command.seanceId(), new ReservationNumber(command.reservationNumber()));
    }
}
