package pl.training.spring.cinema.application.api.commands;

import pl.spring.training.cinema.domain.user.User;

public record CreateReservationCommand(String seanceId, User user) {

}
