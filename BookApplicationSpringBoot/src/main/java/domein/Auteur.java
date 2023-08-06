package domein;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NamedQueries({
	@NamedQuery
	(name="Auteur.findByName", query="select a from Auteur a where a.voornaam= :voornaam and a.achternaam = :achternaam")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "voornaam","achternaam"})
public class Auteur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter private String voornaam;
	@Getter @Setter private String achternaam;
	@ManyToMany(fetch=FetchType.EAGER)
	@Setter private Set<Boek> boeken = new HashSet<>();
	
public Auteur(String voornaam, String achternaam) {
	
	setVoornaam(voornaam);
	setAchternaam(achternaam);
}

public Set<Boek> getBoeken() {
	return Collections.unmodifiableSet(boeken);
}



public void addBoek(Boek boek) {
	boeken.add(boek);
}
public void removeBoek(Boek boek) {
	boeken.remove(boek);
}
public String getNaam() {
	return voornaam + " "+ achternaam;
}
@Override
public String toString() {
	
	return getNaam();
}
}
