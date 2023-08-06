package service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import domein.Favoriet;
import domein.Gebruiker;
import jakarta.transaction.Transactional;


public interface GebruikerService {

	@Transactional
	void updateFavorieten(Gebruiker gebruiker, Set<Favoriet> favorieten);
	@Transactional
	void addFavoriet(Gebruiker gebruiker, Favoriet favoriet);
	@Transactional
	void removeFavoriet(Gebruiker gebruiker, Favoriet favoriet);
	
	Gebruiker getIngelogdeGebruiker();
}
