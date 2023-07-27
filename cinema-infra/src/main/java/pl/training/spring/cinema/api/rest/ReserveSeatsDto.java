package pl.training.spring.cinema.api.rest;

import lombok.Data;

import java.util.List;

@Data
public class ReserveSeatsDto {
    private String seanceId;
    private String reservationNumber;
    private List<Integer> chosenSeats;
}