package pl.training.spring.cinema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.spring.training.cinema.domain.seance.SeanceRepository;
import pl.training.spring.cinema.application.api.commands.CreateReservationCommandHandler;
import pl.training.spring.cinema.application.api.commands.ReserveSeatsCommandHandler;
import pl.training.spring.cinema.application.seance.ReservationUuidGenerator;
import pl.training.spring.cinema.application.seance.SeanceService;

@Configuration
public class CinemaConfiguration {

	@Bean
	public SeanceService seanceService(SeanceRepository repository) {
		return new SeanceService(repository, new ReservationUuidGenerator());
	}

	@Bean
	public CreateReservationCommandHandler createReservationCommandHandler(SeanceService seanceService) {
		return new CreateReservationCommandHandler(seanceService);
	}

	@Bean
	public ReserveSeatsCommandHandler reserveSeatsCommandHandler(SeanceService seanceService) {
		return new ReserveSeatsCommandHandler(seanceService);
	}



}
