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

    private final Map<ReservationNumber, Reservation> reservations;

    private final List<Seat> seats;

    private final SeatIsAvailableSpecification seatIsAvailableSpecification = new SeatIsAvailableSpecification();

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
