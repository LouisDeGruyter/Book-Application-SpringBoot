package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domein.Auteur;
import domein.Boek;
import domein.Favoriet;
import domein.Gebruiker;

public interface FavorietRepository extends CrudRepository<Favoriet,Long>{
	Favoriet findByGebruikerAndBoek(@Param("gebruiker") Gebruiker gebruiker, @Param("boek") Boek boek);
}
