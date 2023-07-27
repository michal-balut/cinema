package pl.training.spring.cinema.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.spring.cinema.application.api.commands.CreateReservationCommandHandler;
import pl.training.spring.cinema.application.api.commands.ReserveSeatsCommandHandler;

@RestController
@RequestMapping("seance")
@RequiredArgsConstructor
public class SeanceController {

    private final CreateReservationCommandHandler createReservationCommandHandler;
    private final ReserveSeatsCommandHandler reserveSeatsCommandHandler;
    private final RestReservationMapper restReservationMapper;

    @PostMapping()
    public ResponseEntity<Void> createReservation(@RequestBody CreateReservationDto createReservationDto) {
        var createReservationCommand = restReservationMapper.toDomain(createReservationDto);
        createReservationCommandHandler.handle(createReservationCommand);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PostMapping("reserve")
    public ResponseEntity<Void> reserveSeats(@RequestBody ReserveSeatsDto reserveSeatsDto) {
        var reserveSeatsCommand = restReservationMapper.toDomain(reserveSeatsDto);
        reserveSeatsCommandHandler.handle(reserveSeatsCommand);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }

}
