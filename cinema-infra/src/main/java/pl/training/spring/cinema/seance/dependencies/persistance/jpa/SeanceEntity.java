package pl.training.spring.cinema.seance.dependencies.persistance.jpa;

import jakarta.persistence.*;
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

    @Column(length = 2000)
	private String reservations;

	private String seats;

}
