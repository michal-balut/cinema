package pl.training.spring.cinema.seance.dependencies.persistance.jpa;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.spring.training.cinema.domain.seance.Seance;
import pl.spring.training.cinema.domain.seance.SeanceRepository;

@Primary
@Transactional
@Repository
@RequiredArgsConstructor
public class JpaSeanceRepositoryAdapter implements SeanceRepository {


	private final JpaSeanceRepository seanceRepository;
	private final JpaSeanceRepositoryMapper mapper;

	@Override
	public Optional<Seance> findById(final String id) {
		return seanceRepository.findById(id)
				.map(mapper::toDomain);
	}

	@Override
	public void save(final Seance seance) {
		var seanceEntity = mapper.toEntity(seance);
		seanceRepository.save(seanceEntity);
	}
}
