package pl.spring.training.cinema.domain.seance;

import java.util.Optional;

public interface SeanceRepository {

	Optional<Seance> findById(String id);

	void save(Seance seance);
}
