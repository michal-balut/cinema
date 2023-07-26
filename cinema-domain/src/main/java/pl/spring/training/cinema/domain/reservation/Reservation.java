package pl.spring.training.cinema.domain.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import pl.spring.training.cinema.domain.user.User;

public class Reservation {

	private final ReservationNumber number;

	private final User user;

	private final List<Integer> reservedSeats = new ArrayList<>();

	private ReservationStatus status;

	private final List<Consumer<ReservationStateChangedEvent>> eventListeners = new ArrayList<>();

	public Reservation(final ReservationNumber number, final User user) {
		this.number = number;
		this.user = user;
		this.status = ReservationStatus.PENDING;
	}

	public void reserveSeats(List<Integer> chosenSeats) {
		reservedSeats.clear();
		reservedSeats.addAll(chosenSeats);
		status = ReservationStatus.SEATS_CHOSEN;
	}



}
