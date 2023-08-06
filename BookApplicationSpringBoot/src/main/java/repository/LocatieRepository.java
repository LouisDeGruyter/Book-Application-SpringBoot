package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domein.Locatie;

public interface LocatieRepository extends CrudRepository<Locatie,Long> {
	Locatie findByAttributes(@Param("plaatscode1") Integer plaatscode1, @Param("plaatscode2") Integer plaatscode2,@Param("plaatsnaam") String plaatsnaam);
}
