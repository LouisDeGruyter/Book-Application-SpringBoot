package repository;

import domein.Auteur;

public interface AuteurDao {
	
	 public Auteur getByName(String voornaam, String achternaam);

	

}
