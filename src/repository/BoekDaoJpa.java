package repository;

import java.util.List;

import domein.Auteur;
import domein.Boek;

public class BoekDaoJpa extends GenericDaoJpa implements BoekDao{

	public BoekDaoJpa() {
		super(Boek.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boek getByISBN(String isbn) {
		return em.createNamedQuery("Boek.findByISBN",Boek.class).setParameter("isbn", isbn).getSingleResult();
	}

	@Override
	public List<Boek> getMostPopular(int aantal) {
		return em.createNamedQuery("Boek.findMostPopular",Boek.class).setMaxResults(aantal).getResultList();
	}
	
	

}
