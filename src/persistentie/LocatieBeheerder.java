package persistentie;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Favoriet;
import domein.Locatie;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class LocatieBeheerder {
	private GenericDao<Locatie> locatieRepo;
	
	public LocatieBeheerder() {
		this(new GenericDaoJpa<>(Locatie.class));
	}
	public LocatieBeheerder(GenericDao<Locatie> locatieRepo) {
		this.locatieRepo = locatieRepo;
	}
	
	public Locatie getById(Long id) {
		try {
			return locatieRepo.get(id);
			} catch(EntityNotFoundException | NoResultException ex) {
				throw new IllegalArgumentException("Locatie met id: "+id+" bestaat niet!");
			}
	}
	public void addLocatie(Locatie l) {
		GenericDaoJpa.startTransaction();
		locatieRepo.insert(l);
        GenericDaoJpa.commitTransaction();
	}
	public void updateLocatie(Locatie l) {
		GenericDaoJpa.startTransaction();
		locatieRepo.update(l);
        GenericDaoJpa.commitTransaction();
	}
	public void removeLocatie(Locatie l) {
		GenericDaoJpa.startTransaction();
		locatieRepo.delete(l);
        GenericDaoJpa.commitTransaction();
	}
	public List<Locatie> getLocaties(){
		return Collections.unmodifiableList(locatieRepo.findAll());
	}

	

}
