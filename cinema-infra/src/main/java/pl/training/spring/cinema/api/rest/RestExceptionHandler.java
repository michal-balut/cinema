package pl.training.spring.cinema.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.spring.training.cinema.domain.seance.ReservationNotFoundException;
import pl.spring.training.cinema.domain.seance.SeatsNotAvailableException;
import pl.training.spring.cinema.application.seance.SeanceNotFoundException;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@RequiredArgsConstructor
@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(SeanceNotFoundException.class)
    public ResponseEntity<String> onSeanceNotFoundException(SeanceNotFoundException seanceNotFoundException) {
        return createResponseEntity(404, seanceNotFoundException.getMessage());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> onReservationNotFoundException(ReservationNotFoundException reservationNotFoundException) {
        return createResponseEntity(404, reservationNotFoundException.getMessage());
    }

    @ExceptionHandler(SeatsNotAvailableException.class)
    public ResponseEntity<String> onSeatsNotAvailableException(SeatsNotAvailableException seatsNotAvailableException) {
        return createResponseEntity(400, seatsNotAvailableException.getMessage());
    }

    private static ResponseEntity<String> createResponseEntity(final int httpCode, final String body) {
        return ResponseEntity.status(HttpStatusCode.valueOf(httpCode)).body(body);
    }
}
