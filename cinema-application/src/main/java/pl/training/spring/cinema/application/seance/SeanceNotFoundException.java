package pl.training.spring.cinema.application.seance;

public class SeanceNotFoundException extends RuntimeException {
    public SeanceNotFoundException(final String message) {
        super(message);
    }
}
