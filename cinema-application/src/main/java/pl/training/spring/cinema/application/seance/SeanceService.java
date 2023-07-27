package pl.training.spring.cinema.application.seance;

import java.util.List;

import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.seance.SeanceRepository;
import pl.spring.training.cinema.domain.user.User;

public class SeanceService {

	private final SeanceRepository seanceRepository;
    private final UuidGenerator uuidGenerator;

	public SeanceService(final SeanceRepository seanceRepository, final UuidGenerator uuidGenerator) {
		this.seanceRepository = seanceRepository;
        this.uuidGenerator = uuidGenerator;
	}

	public void createReservation(String seanceId, User user) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new SeanceNotFoundException(
            String.format("Seance with id %s was not found", seanceId)));
		currentSeance.addReservation(user, uuidGenerator.getNext());
		seanceRepository.save(currentSeance);
	}

	public void reserveSeats(String seanceId, ReservationNumber reservationNumber, List<Integer> chosenSeats) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new SeanceNotFoundException(
            String.format("Seance with id %s was not found", seanceId)));
		currentSeance.reserveSeats(reservationNumber, chosenSeats);
		seanceRepository.save(currentSeance);
	}
}
