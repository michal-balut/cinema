package pl.training.spring.cinema.seance.dependencies.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSeanceRepository extends JpaRepository<SeanceEntity, String> {

}
