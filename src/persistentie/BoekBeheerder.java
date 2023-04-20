package persistentie;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Boek;
import repository.BoekDaoJpa;
import repository.GenericDaoJpa;

public class BoekBeheerder {
	private BoekDaoJpa boekRepo;
	
	public BoekBeheerder() {
		this(new BoekDaoJpa());
	}

	public BoekBeheerder(BoekDaoJpa boekDaoJpa) {
		this.boekRepo= boekDaoJpa;
	}
	
	public Boek getByISBN(String isbn) {
		try {
		return boekRepo.getByISBN(isbn);
		} catch(EntityNotFoundException | NoResultException ex) {
			throw new IllegalArgumentException("Boek met isbn nummer: "+isbn+" bestaat niet!");
		}	
	}
	public Boolean boekExists(String isbn) {
		try {
			boekRepo.getByISBN(isbn);
			return true;
		} catch(EntityNotFoundException | NoResultException ex){
			return false;
		}
	}
	public void addBoek(Boek boek) {
		if(!boekExists(boek.getISBNNummer())) {
			GenericDaoJpa.startTransaction();
			boekRepo.insert(boek);
	        GenericDaoJpa.commitTransaction();
		}
		else {
			throw new IllegalArgumentException("Boek met isbn nummer: "+boek.getISBNNummer()+" bestaat al");
		}
		
	}
	
	public Boek getById(Long id) {
		try {
			return (Boek) boekRepo.get(id);
			} catch(EntityNotFoundException | NoResultException ex) {
				throw new IllegalArgumentException("Boek met id: "+id+" bestaat niet!");
			}	
	}
	
	public void updateBoek(Boek boek) {
		 GenericDaoJpa.startTransaction();
		 boekRepo.update(boek);
		 GenericDaoJpa.commitTransaction();
	}
	public List<Boek> getBoeken(){
		return Collections.unmodifiableList(boekRepo.findAll());
	}
	public List<Boek> getMostPopularBoeken(int aantal){
		return Collections.unmodifiableList(boekRepo.getMostPopular(aantal));
	}
	public void removeBoek(Boek boek) {
		boekRepo.delete(boek);
	}
	
	

	
	

}
