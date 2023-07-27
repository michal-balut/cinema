package pl.spring.training.cinema.domain.reservation;

import java.util.ArrayList;
import java.util.List;

import pl.spring.training.cinema.domain.user.User;

public class Reservation {

	private ReservationNumber number;

	private User user;

	private List<Integer> reservedSeats = new ArrayList<>();

	private ReservationStatus status;

	public Reservation() {
		// needed for mapstruct
	}

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

	public ReservationNumber getNumber() {
		return number;
	}

	public User getUser() {
		return user;
	}

	public List<Integer> getReservedSeats() {
		return reservedSeats;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(final ReservationStatus status) {
		this.status = status;
	}
}
