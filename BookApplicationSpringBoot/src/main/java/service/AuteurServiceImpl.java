package service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import domein.Auteur;
import domein.Boek;
import exceptions.AuteurNotFoundException;
import repository.AuteurRepository;

@Service
public class AuteurServiceImpl implements AuteurService {
	@Autowired
	AuteurRepository ar;
	@Autowired
	MessageSource messageSource;

	@Override
	public Set<Boek> getBoeken(String voornaam, String achternaam) {
		Auteur auteur=ar.findByName(voornaam, achternaam);
		if(auteur!=null) {
			return auteur.getBoeken();
		}
		throw new AuteurNotFoundException(voornaam, achternaam,messageSource);
	}

	public Set<Boek> getBoeken(Long auteurId) {
		Auteur auteur=ar.findById(auteurId).orElse(null);
		if(auteur==null) {
			throw new AuteurNotFoundException(auteurId,messageSource);
		}
		return auteur.getBoeken();
	}

	@Override
	public Iterable<Auteur> findAll() {
		
		return ar.findAll();
	}

	@Override
	public Auteur findByName(String voornaam, String achternaam) {
		
		return ar.findByName(voornaam, achternaam);
	}

}
