package pl.spring.training.cinema.domain.reservation;

public record ReservationNumber(String value) {

		@Override
		public String toString() {
			return value;
		}

}
