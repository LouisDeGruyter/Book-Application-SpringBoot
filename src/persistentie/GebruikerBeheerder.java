package persistentie;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Favoriet;
import domein.Gebruiker;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class GebruikerBeheerder {
	private GenericDao<Gebruiker> gebruikerRepo;
	public GebruikerBeheerder() {
		this(new GenericDaoJpa<>(Gebruiker.class));
	}
	public GebruikerBeheerder(GenericDao<Gebruiker> gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
	}
	public Gebruiker getById(Long id) {
		try {
			return gebruikerRepo.get(id);
			} catch(EntityNotFoundException | NoResultException ex) {
				throw new IllegalArgumentException("Gebruiker met id: "+id+" bestaat niet!");
			}
	}
	public void addGebruiker(Gebruiker g) {
		GenericDaoJpa.startTransaction();
		gebruikerRepo.insert(g);
        GenericDaoJpa.commitTransaction();
	}
	public void updateGebruiker(Gebruiker g) {
		GenericDaoJpa.startTransaction();
		gebruikerRepo.update(g);
        GenericDaoJpa.commitTransaction();
	}
	public void removeGebruiker(Gebruiker g) {
		GenericDaoJpa.startTransaction();
		gebruikerRepo.delete(g);
        GenericDaoJpa.commitTransaction();
	}
	public List<Gebruiker> getGebruikers(){
		return Collections.unmodifiableList(gebruikerRepo.findAll());
	}

	
	
}
