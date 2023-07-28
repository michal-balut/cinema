package pl.training.spring.cinema.application.api.commands;

public record PayForReservationCommand(String seanceId, String reservationNumber) {
};