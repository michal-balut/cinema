package pl.training.spring.cinema.seance.dependencies.persistance;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pl.spring.training.cinema.domain.seance.Seance;
import pl.spring.training.cinema.domain.seance.SeanceRepository;

@Repository
public class InMemorySeanceRepository implements SeanceRepository {

	private final Map<String, Seance> seances = new HashMap<>();

	@Override
	public Optional<Seance> findById(String seanceId) {
		return Optional.ofNullable(seances.get(seanceId));
	}

	@Override
	public void save(Seance seance) {
		seances.put(seance.getId(), seance);
	}

}
