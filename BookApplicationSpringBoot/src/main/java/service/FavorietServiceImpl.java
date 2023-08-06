package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import domein.Boek;
import domein.Favoriet;
import domein.Gebruiker;
import repository.BoekRepository;
import repository.FavorietRepository;

@Service
public class FavorietServiceImpl implements FavorietService {
	@Autowired
	private FavorietRepository fr;
	@Autowired
	private BoekRepository br;
	
	@Override
	public Favoriet findByGebruikerAndBoek(Gebruiker gebruiker, Boek boek) {
		
		return fr.findByGebruikerAndBoek(gebruiker, boek);
	}
	@Override
	public Boolean AddOrRemoveFavoriet(Long boekId) {
		
		GebruikerPrincipal gebruikerPrincipal = (GebruikerPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		Gebruiker gebruiker= gebruikerPrincipal.getGebruiker();
		Boek boek=br.findById(boekId).orElse(null);
		 Favoriet favorietFind= findByGebruikerAndBoek(gebruiker, boek);
		 if (favorietFind==null) {	
		    return true;
		    	
		        
		    }
		 else {
			 return false;
		 }
		
	}
	@Override
	public void setBoekAndGebruiker(Favoriet favoriet, Boek boek,Gebruiker gebruiker) {
		favoriet.setGebruiker(gebruiker);
		favoriet.setBoek(boek);
		
	}

}
