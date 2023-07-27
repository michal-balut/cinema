package pl.spring.training.cinema.domain.seance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pl.spring.training.cinema.domain.reservation.Reservation;
import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.user.User;

public class Seance {

	private final String id;

	private final Map<ReservationNumber, Reservation> reservations = new HashMap<>();

	private final List<Seat> seats;

	private final SeatIsAvailableSpecification seatIsAvailableSpecification = new SeatIsAvailableSpecification();

	public Seance(final String id, List<Seat> seats) {
		this.id = id;
		this.seats = new ArrayList<>(seats);
	}

	public void addReservation(User user) {
		var reservationNumber = /*generatpor*/ new ReservationNumber("resNumber");
		reservations.put(reservationNumber, new Reservation(reservationNumber, user));
	}

	public void reserveSeats(ReservationNumber resNumber, List<Integer> chosenSeats) {
		var currentReservation = findReservation(resNumber)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		var availableChosenSeats = seats.stream()
				.filter(Seat::isAvailable)
				.filter(seat -> chosenSeats.contains(seat.getNumber()))
				.toList();

		if (availableChosenSeats.size() != chosenSeats.size()) {
			//var availableSeatsNumbers = availableChosenSeats.stream().map(Seat::getNumber).collect(Collectors.toList());
			throw new SeatsNotAvailableException(""/* print unavailable seats numbers*/);
		}
		availableChosenSeats.forEach(Seat::reserve);
		currentReservation.reserveSeats(chosenSeats);
	}

	private Optional<Reservation> findReservation(ReservationNumber number) {
		return Optional.ofNullable(reservations.get(number));
	}

	public String getId() {
		return id;
	}

	public Map<ReservationNumber, Reservation> getReservations() {
		return reservations;
	}

	public List<Seat> getSeats() {
		return seats;
	}


}
