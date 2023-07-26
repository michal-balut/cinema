package pl.spring.training.cinema.domain.seance;

import java.util.List;

public class SeatIsAvailableSpecification {

	public boolean check(List<Seat> seats, Integer chosenSeat) {
		return seats.stream()
				.filter(seat -> seat.isAvailable())
				.anyMatch(seat -> chosenSeat.intValue() == seat.getNumber());
	}
}
