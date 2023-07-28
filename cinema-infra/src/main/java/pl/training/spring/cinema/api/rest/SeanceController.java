package pl.training.spring.cinema.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.spring.cinema.application.api.commands.*;

import java.util.Optional;

@RestController
@RequestMapping("seance")
@RequiredArgsConstructor
public class SeanceController {

    private final CreateReservationCommandHandler createReservationCommandHandler;
    private final ReserveSeatsCommandHandler reserveSeatsCommandHandler;
    private final PayForReservationCommandHandler payForReservationCommandHandler;
    private final RestReservationMapper restReservationMapper;

    @PostMapping()
    public ResponseEntity<String> createReservation(@RequestBody @Valid CreateReservationDto createReservationDto) {
        var createReservationCommand = restReservationMapper.toDomain(createReservationDto);
        String reservationNumber = createReservationCommandHandler.handle(createReservationCommand);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Created reservation with number: " + reservationNumber);
    }

    @PutMapping("reservation")
    public ResponseEntity<Void> reserveSeats(@RequestBody ReserveSeatsDto reserveSeatsDto) {
        var reserveSeatsCommand = restReservationMapper.toDomain(reserveSeatsDto);
        reserveSeatsCommandHandler.handle(reserveSeatsCommand);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }

    @PutMapping("payment")
    public ResponseEntity<Void> payForReservation(@RequestBody PayForReservationDto payForReservationDto) {
        var payForReservationCommand = restReservationMapper.toDomain(payForReservationDto);
        payForReservationCommandHandler.handle(payForReservationCommand);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }

}
