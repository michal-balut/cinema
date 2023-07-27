package pl.training.spring.cinema.seance.dependencies.persistance;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pl.spring.training.cinema.domain.seance.Seance;
import pl.spring.training.cinema.domain.seance.SeanceRepository;
import pl.spring.training.cinema.domain.seance.Seat;

@Repository
public class InMemorySeanceRepository implements SeanceRepository {

    private final Map<String, Seance> seances = new HashMap<>(Map.of("123abc", new Seance(
        "123abc",
        List.of(new Seat(1, true), new Seat(2, true), new Seat(3, true)),
		Collections.emptyMap()
    )));

    @Override
    public Optional<Seance> findById(String seanceId) {
        return Optional.ofNullable(seances.get(seanceId));
    }

    @Override
    public void save(Seance seance) {
        seances.put(seance.getId(), seance);
    }

}
