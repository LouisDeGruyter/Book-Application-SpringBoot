package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Favoriet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Gebruiker gebruiker;
	@ManyToOne
	private Boek boek;
	
	public Favoriet(Boek boek, Gebruiker gebruiker) {
		setBoek(boek);
		setGebruiker(gebruiker);
		
	}
	protected Favoriet() {
		
	}
	public Gebruiker getGebruiker() {
		return gebruiker;
	}
	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
	public Boek getBoek() {
		return boek;
	}
	public void setBoek(Boek boek) {
		this.boek = boek;
	}
	@Override
	public int hashCode() {
		return Objects.hash(boek, gebruiker);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favoriet other = (Favoriet) obj;
		return Objects.equals(boek, other.boek) && Objects.equals(gebruiker, other.gebruiker);
	}
	@Override
	public String toString() {
		return "Favoriet [gebruiker=" + gebruiker.getEmail() + ", boek=" + boek.getNaam() + "]";
	}
	
}
