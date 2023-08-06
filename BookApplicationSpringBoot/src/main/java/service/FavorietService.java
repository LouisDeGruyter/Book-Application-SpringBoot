package service;

import domein.Boek;
import domein.Favoriet;
import domein.Gebruiker;

public interface FavorietService {

	Favoriet findByGebruikerAndBoek(Gebruiker gebruiker, Boek boek);

	Boolean AddOrRemoveFavoriet(Long boekId);

	void setBoekAndGebruiker(Favoriet favoriet, Boek boek, Gebruiker gebruiker);

}
