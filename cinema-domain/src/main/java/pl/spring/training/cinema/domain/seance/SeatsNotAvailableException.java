package pl.spring.training.cinema.domain.seance;

public class SeatsNotAvailableException extends RuntimeException {

	public SeatsNotAvailableException(final String message) {
		super(message);
	}
}
