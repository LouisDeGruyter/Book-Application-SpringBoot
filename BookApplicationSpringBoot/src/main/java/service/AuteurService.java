package service;

import java.util.List;
import java.util.Set;

import domein.Auteur;
import domein.Boek;

public interface AuteurService {
 Set<Boek> getBoeken(String voornaam,String achternaam);

Set<Boek> getBoeken(Long auteurId);

Iterable<Auteur> findAll();

Auteur findByName(String voornaam, String achternaam);
}
