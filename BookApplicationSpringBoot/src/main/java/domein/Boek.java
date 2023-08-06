package domein;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.FavorietRepository;
import repository.LocatieRepository;
import utils.*;

@Entity
@NamedQueries({	
	@NamedQuery(
		    name = "Boek.findMostPopular",
		    query = "SELECT b FROM Boek b ORDER BY SIZE(b.favorieten) DESC" ),
	@NamedQuery(
		    name = "Boek.findByAuteur",
		    query = "SELECT b FROM Boek b JOIN b.auteurs a WHERE a.voornaam = :voornaam AND a.achternaam= :achternaam"
		)

})
@NoArgsConstructor()
@EqualsAndHashCode(of = { "ISBNNummer"})
@JsonPropertyOrder({ "id","ISBNNummer","naam","aankoopprijs","imageURL"})
@AllArgsConstructor
public class Boek implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@Getter @Setter private String naam;
	@Getter   private String ISBNNummer;
	@JsonSerialize(using= AankoopprijsSerializer.class)
	@JsonDeserialize(using= AankoopprijsDeserializer.class)
	@Getter @Setter private Double aankoopprijs;
	@Getter @Setter private String imageURL;

	@OneToMany(mappedBy="boek")
	@JsonIgnore
	private Set<Favoriet> favorieten = new HashSet<>(); // length= aantalfavorieten
	@ManyToMany(mappedBy="boeken",fetch=FetchType.EAGER)
	@JsonIgnore
	private  Set<Auteur> auteurs = new MaxSizeSet<>(3);
	@JsonIgnore
	@OneToMany(mappedBy="boek", fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
	private Set<Locatie> locaties = new MaxSizeSet<>(3);
	
	
	
	public Boek(String naam , String ISBNNummer, double aankoopPrijs) {
		setNaam(naam);
		setISBNNummer(ISBNNummer);
		setAankoopprijs(aankoopPrijs);
		
	}
	public void setISBNNummer(String iSBNNummer2) {
		this.ISBNNummer = iSBNNummer2.replaceAll("[^0-9]", "").replaceAll("\\s", "");
		
	}
	public Boek(String naam , String ISBNNummer, double aankoopPrijs, String imageUrl) {
		setNaam(naam);
		setISBNNummer(ISBNNummer);
		setAankoopprijs(aankoopPrijs);
		setImageURL(imageUrl);
		
	}
	
	public Set<Favoriet> getFavorieten() {
		return Collections.unmodifiableSet(favorieten);
	}
	public void setFavorieten(Set<Favoriet> favorieten) {
		for (Favoriet favoriet : favorieten) {
			favoriet.setBoek(this);
		}
		this.favorieten = favorieten;
	}
	
	public Set<Auteur> getAuteurs() {
		return Collections.unmodifiableSet(auteurs);
	}
	public void setAuteurs(Set < Auteur > auteurs) {
		this.auteurs = auteurs;
	}

	public void addAuteur(Auteur auteur) {
		auteur.addBoek(this);
		auteurs.add(auteur);
		
	}
	public Set<Locatie> getLocaties() {
		return Collections.unmodifiableSet(locaties);
	}
	public void setLocaties(Set<Locatie> locaties) {
		for (Locatie locatie : locaties) {
			locatie.setBoek(this);
		}
		this.locaties = locaties;
	}
	public void addLocatie(Locatie locatie) {
		locatie.setBoek(this);
		locaties.add(locatie);
		
	}
	public void removeLocatie(Locatie locatie) {
		locatie.setBoek(null);
		locaties.remove(locatie);
	}
	public void clearLocaties() {
		for (Locatie locatie : locaties) {
			locatie.setBoek(null);
			
		}
		locaties.clear();
	}
	public void clearAuteurs() {
		for(Auteur auteur : auteurs) {
			auteur.removeBoek(this);
		}
		auteurs.clear();
		
	}
	@Override
	public String toString() {
	    return "Boek: naam=" + naam + ", ISBNNummer=" + ISBNNummer + ", aankoopprijs=" + aankoopprijs + (auteurs.size() > 1 ? ", auteurs=" : "")
	            + auteurs.stream().map(Auteur::getNaam).collect(Collectors.joining(", "));
	}
	public Boek(Long id2, String naam2, String isbn, Double aankoopprijs2, String URL) {
		setId(id2);
		setNaam(naam2);
		setISBNNummer(isbn);
		setAankoopprijs(aankoopprijs2);
		setImageURL(URL);
	}
	
	


	


}
