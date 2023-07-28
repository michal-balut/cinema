package pl.spring.training.cinema.domain.seance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import pl.spring.training.cinema.domain.reservation.Reservation;
import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.reservation.ReservationPaidEvent;
import pl.spring.training.cinema.domain.reservation.ReservationStatus;
import pl.spring.training.cinema.domain.user.User;

public class Seance {

    private final String id;

    private final Map<ReservationNumber, Reservation> reservations;

    private final List<Seat> seats;

    private final SeatIsAvailableSpecification seatIsAvailableSpecification = new SeatIsAvailableSpecification();

	private final List<Consumer<ReservationPaidEvent>> eventListeners = new ArrayList<>();

    public Seance(final String id, List<Seat> seats, Map<ReservationNumber, Reservation> reservations) {
        this.id = id;
        this.seats = new ArrayList<>(seats);
		this.reservations = new HashMap<>(reservations);
    }

    public void addReservation(User user, String uuid) {
        var reservationNumber = /*generatpor*/ new ReservationNumber(uuid);
        reservations.put(reservationNumber, new Reservation(reservationNumber, user));
    }

    public void reserveSeats(ReservationNumber resNumber, List<Integer> chosenSeats) {
        var currentReservation = findReservation(resNumber)
            .orElseThrow(() -> new ReservationNotFoundException(String.format("Reservation with number: %s not found", resNumber)));
        var availableChosenSeats = seats.stream()
            .filter(Seat::isAvailable)
            .filter(seat -> chosenSeats.contains(seat.getNumber()))
            .toList();

        if (availableChosenSeats.size() != chosenSeats.size()) {
            chosenSeats.removeAll(availableChosenSeats.stream().map(Seat::getNumber).toList());
            throw new SeatsNotAvailableException(String.format("Seats: %s are not available!", chosenSeats));
        }
        availableChosenSeats.forEach(Seat::reserve);
        currentReservation.reserveSeats(chosenSeats);
    }

	public void markAsPaid(ReservationNumber resNumber) {
		var currentReservation = findReservation(resNumber)
				.orElseThrow(() -> new ReservationNotFoundException(String.format("Reservation with number: %s not found", resNumber)));
		currentReservation.setStatus(ReservationStatus.PAYMENT_COMPLETED);
		publishEvent(new ReservationPaidEvent(id, currentReservation.getNumber(), seats.stream().map(Seat::getNumber).collect(
            Collectors.toList()), currentReservation.getUser().email()));
	}

	private void publishEvent(ReservationPaidEvent event) {
		eventListeners.forEach(listener -> listener.accept(event));
	}

	public void addEventsListener(Consumer<ReservationPaidEvent> listener) {
		eventListeners.add(listener);
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
