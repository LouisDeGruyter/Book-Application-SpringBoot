package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery
	(name="Auteur.findByName", query="select a from Auteur a where a.voornaam= :voornaam and a.achternaam = :achternaam")
})
public class Auteur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private String voornaam;
	private String achternaam;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Boek> boeken = new HashSet<>();
	
public Auteur(String voornaam, String achternaam) {
	
	setVoornaam(voornaam);
	setAchternaam(achternaam);
}
protected Auteur() {
	
}
public Long getId(){
	return id;
}
public Set<Boek> getBoeken() {
	return Collections.unmodifiableSet(boeken);
}

public String getVoornaam() {
	return voornaam;
}
public void setVoornaam(String voornaam) {
	this.voornaam = voornaam;
}
public String getAchternaam() {
	return achternaam;
}
public void setAchternaam(String achternaam) {
	this.achternaam = achternaam;
}
public void addBoek(Boek boek) {
	boeken.add(boek);
}
public void removeBoek(Boek boek) {
	boeken.remove(boek);
}
public String getNaam() {
	return voornaam+ " " + achternaam;
}
@Override
public int hashCode() {
	return Objects.hash(achternaam, voornaam);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Auteur other = (Auteur) obj;
	return Objects.equals(achternaam, other.achternaam) && Objects.equals(voornaam, other.voornaam);
}
@Override
public String toString() {
	return "Auteur [voornaam=" + voornaam + ", achternaam=" + achternaam ;
}

}
