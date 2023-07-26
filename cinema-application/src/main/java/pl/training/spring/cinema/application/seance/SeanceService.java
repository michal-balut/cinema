package pl.training.spring.cinema.application.seance;

import java.util.List;

import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.seance.SeanceRepository;
import pl.spring.training.cinema.domain.user.User;

public class SeanceService {

	private final SeanceRepository seanceRepository;

	public SeanceService(final SeanceRepository seanceRepository) {
		this.seanceRepository = seanceRepository;
	}

	public void createReservation(String seanceId, User user) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new RuntimeException());
		currentSeance.addReservation(user);
		seanceRepository.save(currentSeance);
	}

	public void reserveSeats(String seanceId, ReservationNumber reservationNumber, List<Integer> chosenSeats) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new RuntimeException());
		currentSeance.reserveSeats(reservationNumber, chosenSeats);
		seanceRepository.save(currentSeance);
	}
}
