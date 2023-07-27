package pl.training.spring.cinema;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.spring.training.cinema.domain.seance.Seance;
import pl.spring.training.cinema.domain.seance.SeanceRepository;
import pl.spring.training.cinema.domain.seance.Seat;

@Transactional
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {

	private final SeanceRepository seanceRepository;

	@Override
	public void run(ApplicationArguments args) {
		var seance = new Seance(
				"123abc",
				List.of(new Seat(1, true), new Seat(2, true), new Seat(3, true), new Seat(4, false)),
				Collections.emptyMap()
		);
		seanceRepository.save(seance);
	}
}
