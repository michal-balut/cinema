package pl.spring.training.cinema.domain.seance;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(final String message) {
        super(message);
    }
}
