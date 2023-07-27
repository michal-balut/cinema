package pl.training.spring.cinema.seance.dependencies.persistance.jpa;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.SneakyThrows;
import pl.spring.training.cinema.domain.reservation.Reservation;
import pl.spring.training.cinema.domain.reservation.ReservationNumber;
import pl.spring.training.cinema.domain.seance.Seance;
import pl.spring.training.cinema.domain.seance.Seat;

@Mapper(componentModel = SPRING)
public abstract class JpaSeanceRepositoryMapper {

	private static final TypeReference<List<Seat>> seatListType = new TypeReference<>() {};
	private static final TypeReference<Map<ReservationNumber, Reservation>> reservationMapType = new TypeReference<>() {};

    private final ObjectMapper jsonMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(FAIL_ON_EMPTY_BEANS, false);

    public abstract SeanceEntity toEntity(Seance seance);

    public Seance toDomain(SeanceEntity seanceEntity) {
        return new Seance(
				seanceEntity.getId(),
				seatsFromJson(seanceEntity.getSeats()),
				reservationsFromJson(seanceEntity.getReservations())
		);
    }

    @SneakyThrows
    protected String seatListToJson(List<Seat> seats) {
        return jsonMapper.writeValueAsString(seats);
    }

    @SneakyThrows
    protected List<Seat> seatsFromJson(String json) {
        return jsonMapper.readValue(json, seatListType);
    }

	@SneakyThrows
	protected String reservationMapToJson(Map<ReservationNumber, Reservation> reservations) {
		return jsonMapper.writeValueAsString(reservations);
	}

	@SneakyThrows
	protected Map<ReservationNumber, Reservation> reservationsFromJson(String json) {
		return jsonMapper.readValue(json, reservationMapType);
	}

}
