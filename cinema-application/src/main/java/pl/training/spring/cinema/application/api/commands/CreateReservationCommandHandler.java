package pl.training.spring.cinema.application.api.commands;

import pl.training.spring.cinema.application.seance.SeanceService;

public class CreateReservationCommandHandler {

	private final SeanceService seanceService;

	public CreateReservationCommandHandler(final SeanceService seanceService) {
		this.seanceService = seanceService;
	}

	public void handle(CreateReservationCommand command) {
		seanceService.createReservation(command.seanceId(), command.user());
	}
}
