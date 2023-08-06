package service;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import domein.Gebruiker;
import jakarta.transaction.Transactional;
import repository.GebruikerRepository;

@Service
public class GebruikerPrincipalDetailsService implements UserDetailsService {
	@Autowired
	private GebruikerRepository gebruikerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Gebruiker gebruiker = gebruikerRepository.findByUsername(s);
		if (gebruiker == null) {
	        throw new UsernameNotFoundException("User not found with username: " + s);
	    }
		
		Hibernate.initialize(gebruiker.getFavorieten());
		GebruikerPrincipal gebruikerPrincipal=new GebruikerPrincipal(gebruiker);
		return gebruikerPrincipal;
	}

}
