package repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domein.Boek;

public interface BoekRepository extends CrudRepository<Boek,Long> {
	
	Boek findByISBNNummer(String ISBNNummer);
	List<Boek> findMostPopular(Pageable page);
	List<Boek> findByAuteur(@Param("voornaam") String voornaam, @Param("achternaam") String achternaam);
}
