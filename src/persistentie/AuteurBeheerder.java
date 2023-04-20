package persistentie;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Auteur;
import domein.Boek;
import repository.AuteurDao;
import repository.AuteurDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class AuteurBeheerder {
	private AuteurDaoJpa auteurRepo;
	

	public AuteurBeheerder() {
		this(new AuteurDaoJpa());
	}
	public AuteurBeheerder(AuteurDaoJpa auteurDaoJpa) {
		this.auteurRepo = auteurDaoJpa;
	}
	
	public void addAuteur(Auteur a) {
		GenericDaoJpa.startTransaction();
        auteurRepo.insert(a);
        GenericDaoJpa.commitTransaction();
	}
	
	public Auteur getAuteurByName(String naam) {
		String voornaam= naam.substring(0, naam.indexOf(" "));
		String achternaam = naam.substring(naam.indexOf(" ")+1, naam.length());
		try {
			Auteur a = auteurRepo.getByName(voornaam, achternaam);
			return a;
		} catch(EntityNotFoundException | NoResultException ex) {
			throw new IllegalArgumentException("auteur " + voornaam+" "+achternaam+ " komt niet voor");
		}	
	}
	public Auteur getAuteur(Long id) {
		try {
			Auteur a = (Auteur) auteurRepo.get(id);
			return a;
		} catch(EntityNotFoundException | NoResultException ex) {
			throw new IllegalArgumentException("auteur met id " + id+" komt niet voor");
		}	
		
	}
	public void updateAuteur(Auteur a) {
		 GenericDaoJpa.startTransaction();
		 auteurRepo.update(a);
		 GenericDaoJpa.commitTransaction();
	}
	public List<Auteur> getAuteurs(){
		return Collections.unmodifiableList(auteurRepo.findAll());
		
	}
	
	public void removeAuteur(Auteur a) {
		auteurRepo.delete(a);
	}
	
	
}
