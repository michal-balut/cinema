package pl.training.spring.cinema.api.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.user.User;
import pl.training.spring.cinema.application.api.commands.CreateReservationCommand;
import pl.training.spring.cinema.application.api.commands.ReserveSeatsCommand;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = {User.class, ReservationNumber.class})
public interface RestReservationMapper {

    @Mapping(target = "user", expression = "java(new User(createReservationDto.getEmail()))")
    CreateReservationCommand toDomain(CreateReservationDto createReservationDto);

    @Mapping(target = "reservationNumber", expression = "java(new ReservationNumber(reserveSeatsDto.getReservationNumber()))")
    ReserveSeatsCommand toDomain(ReserveSeatsDto reserveSeatsDto);
}
