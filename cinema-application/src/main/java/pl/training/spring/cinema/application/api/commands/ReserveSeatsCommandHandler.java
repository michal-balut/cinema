package pl.training.spring.cinema.application.api.commands;

import pl.training.spring.cinema.application.seance.SeanceService;

public class ReserveSeatsCommandHandler {

	private final SeanceService seanceService;

	public ReserveSeatsCommandHandler(final SeanceService seanceService) {
		this.seanceService = seanceService;
	}

	public void handle(ReserveSeatsCommand command) {
		seanceService.reserveSeats(command.seanceId(), command.reservationNumber(), command.chosenSeats());
	}
}
