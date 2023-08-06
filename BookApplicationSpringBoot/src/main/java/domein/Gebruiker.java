package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import repository.FavorietRepository;
import repository.GebruikerRepository;


@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "username", "wachtwoord"})
public abstract class Gebruiker implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter private long id;
	
	@OneToMany(mappedBy ="gebruiker",fetch = FetchType.EAGER)
	@Setter private Set<Favoriet> favorieten= new HashSet<>();
	
	@Getter @Setter private String username;
	@Getter @Setter private String wachtwoord;
	@Getter @Setter private int maxAantalFavorieten = new SecureRandom().nextInt(6)+5;
	@Setter(AccessLevel.PROTECTED) private String roles;
	
	
	public Gebruiker(String username, String wachtwoord) {
		BCryptPasswordEncoder enc= new BCryptPasswordEncoder();
		setUsername(username);
		setWachtwoord(enc.encode(wachtwoord));
	}
	

	public boolean verifieerWachtwoord(String wachtwoord) {
		return wachtwoord.equals(this.wachtwoord);
	}

	

	public Set<Favoriet> getFavorieten() {
		return Collections.unmodifiableSet(favorieten);
	}
	public void addFavoriet(Favoriet favoriet) {
		favoriet.setGebruiker(this);
		favorieten.add(favoriet);
	}
	public void removeFavoriet(Favoriet favoriet) {
		System.out.println(favorieten);
		favorieten.remove(favoriet);
		System.out.println(favorieten);
		
	}
	
	public void removeFavoriet(Boek boek) {
		Long id= boek.getId();
		favorieten.stream().forEach(f-> System.out.println(f.getBoek().getId()+"id"+id));
		
	    Favoriet favoriet = favorieten.stream().filter(f -> f.getBoek().getId() == boek.getId()).findFirst().orElse(null);
	    
	    if (favoriet != null) {
	        favorieten.remove(favoriet);
	    }
	}


	public List<String> getRoleList(){
		if(this.roles.length()>0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	public Boolean hasFavoriet(Boek boek) {
		
		return favorieten.stream().anyMatch(favoriet -> favoriet.getBoek().equals(boek));
	}
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName()+" "+username+" "+wachtwoord;
	}

}
