package pl.training.spring.cinema.application.seance;

import java.util.UUID;

public class ReservationUuidGenerator implements UuidGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
