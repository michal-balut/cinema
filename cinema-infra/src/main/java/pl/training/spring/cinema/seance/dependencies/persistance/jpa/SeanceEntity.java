package pl.training.spring.cinema.seance.dependencies.persistance.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Seance")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class SeanceEntity {

	@Id
	private String id;

	private String reservations;

	private String seats;

}
