package pl.training.spring.cinema.application.seance;

import java.util.List;

import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.reservation.ReservationPaidEvent;
import pl.spring.training.cinema.domain.seance.SeanceRepository;
import pl.spring.training.cinema.domain.user.User;
import pl.training.spring.cinema.application.output.events.ReservationPaidApplicationEvent;
import pl.training.spring.cinema.application.output.events.SeanceApplicationEventsPublisher;

public class SeanceService {

	private final SeanceRepository seanceRepository;
    private final UuidGenerator uuidGenerator;

	private final SeanceApplicationEventsPublisher seanceApplicationEventsPublisher;

	public SeanceService(final SeanceRepository seanceRepository, final UuidGenerator uuidGenerator, final SeanceApplicationEventsPublisher seanceApplicationEventsPublisher) {
		this.seanceRepository = seanceRepository;
        this.uuidGenerator = uuidGenerator;
		this.seanceApplicationEventsPublisher = seanceApplicationEventsPublisher;
	}

	public String createReservation(String seanceId, User user) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new SeanceNotFoundException(
            String.format("Seance with id %s was not found", seanceId)));
        final String uuid = uuidGenerator.getNext();
        currentSeance.addReservation(user, uuid);
		seanceRepository.save(currentSeance);
        return uuid;
	}

	public void reserveSeats(String seanceId, ReservationNumber reservationNumber, List<Integer> chosenSeats) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new SeanceNotFoundException(
            String.format("Seance with id %s was not found", seanceId)));
		currentSeance.reserveSeats(reservationNumber, chosenSeats);
		seanceRepository.save(currentSeance);
	}

	public void payForReservation(String seanceId, ReservationNumber reservationNumber) {
		var currentSeance = seanceRepository.findById(seanceId).orElseThrow(() -> new SeanceNotFoundException(
				String.format("Seance with id %s was not found", seanceId)));
		currentSeance.addEventsListener(this::publishApplicationEvent);
		// @FIXME inject payment operation delegation to external provider
		currentSeance.markAsPaid(reservationNumber);
		seanceRepository.save(currentSeance);
	}

	private void publishApplicationEvent(ReservationPaidEvent domainEvent) {
		var applicationEvent = new ReservationPaidApplicationEvent(domainEvent.seanceId(), domainEvent.number().value(), domainEvent.seats(),
            domainEvent.email());
		seanceApplicationEventsPublisher.publish(applicationEvent);
	}
}
