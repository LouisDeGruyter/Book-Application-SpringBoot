package repository;

import java.util.List;

import domein.Auteur;

public class AuteurDaoJpa extends GenericDaoJpa implements AuteurDao {

	public AuteurDaoJpa() {
		super(Auteur.class);
		
	}

	@Override
	public Auteur getByName(String voornaam, String achternaam) {
		return em.createNamedQuery("Auteur.findByName",Auteur.class).setParameter("voornaam", voornaam).setParameter("achternaam", achternaam).getSingleResult();
	}
	
	 @Override
	    public List<Auteur> findAll() {
	        return em.createQuery("select entity from Auteur entity", Auteur.class).getResultList();
	    }


	


}
