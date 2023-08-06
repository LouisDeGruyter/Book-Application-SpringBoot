package service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import domein.Favoriet;
import domein.Gebruiker;
import repository.FavorietRepository;
import repository.GebruikerRepository;
@Service
public class GebruikerServiceImpl implements GebruikerService{
	@Autowired
	private GebruikerRepository gr;
	@Autowired 
	FavorietRepository fr;
	@Override
	public void updateFavorieten(Gebruiker gebruiker, Set<Favoriet> favorieten) {
		gebruiker.setFavorieten(favorieten);
		gr.save(gebruiker);
		
	}

	@Override
	public void addFavoriet(Gebruiker gebruiker, Favoriet favoriet) {
		if(fr.findByGebruikerAndBoek(gebruiker, favoriet.getBoek())!=null) {
			return;
		}
		fr.save(favoriet);
		gebruiker.addFavoriet(favoriet);
//		gr.save(gebruiker);
		
	}

	@Override
	public void removeFavoriet(Gebruiker gebruiker, Favoriet favoriet) {
		gebruiker.removeFavoriet(favoriet);
		
		fr.delete(favoriet);
//		gr.save(gebruiker);
		
	}

	@Override
	public Gebruiker getIngelogdeGebruiker() {
		GebruikerPrincipal gebruikerPrincipal = (GebruikerPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		Gebruiker gebruiker= gebruikerPrincipal.getGebruiker();	
		return gebruiker;
	}

	
	
	
}
