package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domein.Gebruiker;


public interface GebruikerRepository extends CrudRepository<Gebruiker,Long> {
	
	Gebruiker findByUsername(String username);
}
