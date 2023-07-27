package pl.training.spring.cinema.application.api.commands;

import pl.training.spring.cinema.application.seance.SeanceService;

public class CreateReservationCommandHandler {

	private final SeanceService seanceService;

	public CreateReservationCommandHandler(final SeanceService seanceService) {
		this.seanceService = seanceService;
	}

	public String handle(CreateReservationCommand command) {
		return seanceService.createReservation(command.seanceId(), command.user());
	}
}
