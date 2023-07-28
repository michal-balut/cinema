package pl.training.spring.cinema.api.rest;

import lombok.Data;

@Data
public class PayForReservationDto {
    private String seanceId;
    private String reservationNumber;
}
