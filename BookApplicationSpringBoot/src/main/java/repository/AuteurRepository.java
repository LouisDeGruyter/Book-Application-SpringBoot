package repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domein.Auteur;
public interface AuteurRepository extends CrudRepository<Auteur,Long>{
	Auteur findByName(@Param("voornaam") String voornaam, @Param("achternaam") String achternaam);
}
