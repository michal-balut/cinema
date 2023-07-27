package pl.training.spring.cinema.api.rest;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CreateReservationDto {
    private String seanceId;
    @Email
    private String email;
}