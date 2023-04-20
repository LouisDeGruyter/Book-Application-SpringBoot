package persistentie;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Favoriet;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class FavorietBeheerder {
	private GenericDao<Favoriet> favorietRepo;
	
	public FavorietBeheerder() {
		this(new GenericDaoJpa<>(Favoriet.class));
	}

	public FavorietBeheerder(GenericDaoJpa genericDaoJpa) {
		favorietRepo= genericDaoJpa;
	}
	public Favoriet getById(Long id) {
		try {
		return favorietRepo.get(id);
		} catch(EntityNotFoundException | NoResultException ex) {
			throw new IllegalArgumentException("Favoriet met id: "+id+" bestaat niet!");
		}
	}
	
	public void addFavoriet(Favoriet f) {
		GenericDaoJpa.startTransaction();
		favorietRepo.insert(f);
        GenericDaoJpa.commitTransaction();
	}
	public void removeFavoriet(Favoriet f) {
		GenericDaoJpa.startTransaction();
		favorietRepo.delete(f);
        GenericDaoJpa.commitTransaction();
	}
	public void updateFavoriet(Favoriet f) {
		GenericDaoJpa.startTransaction();
		favorietRepo.update(f);
        GenericDaoJpa.commitTransaction();
	}
	public List<Favoriet> getFavorieten() {
		return Collections.unmodifiableList(favorietRepo.findAll());
	}

}
