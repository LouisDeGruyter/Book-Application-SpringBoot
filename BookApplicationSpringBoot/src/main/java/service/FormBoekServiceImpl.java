package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domein.Auteur;
import domein.Boek;
import domein.Locatie;
import formObjecten.FormBoek;
import jakarta.validation.Valid;
import repository.AuteurRepository;
import repository.BoekRepository;
import repository.LocatieRepository;

@Service
public class FormBoekServiceImpl implements FormBoekService {
	@Autowired
	AuteurRepository ar;
	@Autowired 
	BoekRepository br;
	@Autowired
	LocatieRepository lr;
	
	@Override
	public void updateOrAddFormBoek(@Valid FormBoek formBoek) {
		Long id = formBoek.getId();
	    Boek boek = null;
	    if (id == null) {
	    	boek= new Boek();
	    }
	    else {
	    	boek=br.findById(formBoek.getId()).orElse(null);
	    }
	        // Update properties of the existing book
	        boek.setNaam(formBoek.getNaam());
	        boek.setISBNNummer(formBoek.getISBNNummer());
	        boek.setAankoopprijs(formBoek.getAankoopprijs());
	        boek.setImageURL(formBoek.getImageURL());

	        // Update locations
	        boek.clearLocaties();
	        boek.clearAuteurs();
	        br.save(boek);
	        updateLocatie(boek,formBoek.getPlaatscode11(), formBoek.getPlaatscode12(), formBoek.getPlaatsnaam1());
	        updateLocatie(boek,formBoek.getPlaatscode21(), formBoek.getPlaatscode22(), formBoek.getPlaatsnaam2());
	        updateLocatie(boek,formBoek.getPlaatscode31(), formBoek.getPlaatscode32(), formBoek.getPlaatsnaam3());
	          

	        

	        // Update authors
	        // Auteur 1
	        String voornaamAuteur1 = formBoek.getVoornaam1();
	        String achternaamAuteur1 = formBoek.getAchternaam1();
	        // Auteur 2
	        String voornaamAuteur2 = formBoek.getVoornaam2();
	        String achternaamAuteur2 = formBoek.getAchternaam2();
	        // Auteur 3
	        String voornaamAuteur3 = formBoek.getVoornaam3();
	        String achternaamAuteur3 = formBoek.getAchternaam3();

	        updateAuthor(boek, voornaamAuteur1, achternaamAuteur1);
	        updateAuthor(boek, voornaamAuteur2, achternaamAuteur2);
	        updateAuthor(boek, voornaamAuteur3, achternaamAuteur3);
	    }
	    
	

	private void updateAuthor(Boek boek, String voornaam, String achternaam) {
	    if (!voornaam.isEmpty()) {
	        Auteur auteur = ar.findByName(voornaam, achternaam);
	        if (auteur == null) {
	            auteur = new Auteur(voornaam, achternaam);
	        }
	        auteur.addBoek(boek);
	        ar.save(auteur);
	    }
	}
	private void updateLocatie(Boek boek,Integer plaatscode1, Integer plaatscode2, String plaatsnaam) {
		if(plaatscode1 !=null) {
			Locatie locatie =lr.findByAttributes(plaatscode1, plaatscode2, plaatsnaam);
			if(locatie==null) {
				locatie=new Locatie(plaatscode1, plaatscode2, plaatsnaam);
			}
			locatie.setBoek(boek);
			lr.save(locatie);
		}
	}


}
