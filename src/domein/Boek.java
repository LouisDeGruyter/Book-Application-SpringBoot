package domein;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery
	(name="Boek.findByISBN", query="select b from Boek b where b.ISBNNummer= :isbn"),
	@NamedQuery(
		    name = "Boek.findMostPopular",
		    query = "SELECT b FROM Boek b ORDER BY SIZE(b.favorieten) DESC" )

})
public class Boek implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String naam;
	private String ISBNNummer;
	private Double aankoopprijs;

	@OneToMany(mappedBy="boek")
	private Set<Favoriet> favorieten = new HashSet<>(); // length= aantalfavorieten
	@ManyToMany(mappedBy="boeken",fetch=FetchType.EAGER)
	private final Set<Auteur> auteurs = new HashSet<>();//max3
	@OneToMany(mappedBy="boek", fetch=FetchType.EAGER)
	private Set<Locatie> locaties = new HashSet<>();
	
	
	public Boek(String naam , String ISBNNummer, double aankoopPrijs) {
		setNaam(naam);
		setISBNNummer(ISBNNummer);
		setAankoopprijs(aankoopPrijs);
		
	}
	protected Boek() {
		
	}
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getISBNNummer() {
		return ISBNNummer;
	}
	public void setISBNNummer(String iSBNNummer) {
		ISBNNummer = iSBNNummer;
	}
	public Double getAankoopprijs() {
		return aankoopprijs;
	}
	public void setAankoopprijs(Double aankoopPrijs) {
		this.aankoopprijs = aankoopPrijs;
	}
	public Set<Favoriet> getFavorieten() {
		return Collections.unmodifiableSet(favorieten);
	}
	public void setFavorieten(Set<Favoriet> favorieten) {
		this.favorieten = favorieten;
	}
	public Set<Auteur> getAuteurs() {
		return Collections.unmodifiableSet(auteurs);
	}
//	public void setAuteurs(Set<Auteur> auteurs) {
//		this.auteurs = auteurs;
//	}
	public void addAuteur(Auteur auteur) {
		auteurs.add(auteur);
	}
	public Set<Locatie> getLocaties() {
		return Collections.unmodifiableSet(locaties);
	}
	public void setLocaties(Set<Locatie> locaties) {
		this.locaties = locaties;
	}
	public void addLocatie(Locatie locatie) {
		locatie.setBoek(this);
		locaties.add(locatie);
	}
	@Override
	public int hashCode() {
		return Objects.hash(ISBNNummer);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boek other = (Boek) obj;
		return Objects.equals(ISBNNummer, other.ISBNNummer);
	}
	@Override
	public String toString() {
		return "Boek [naam=" + naam + ", ISBNNummer=" + ISBNNummer + ", aankoopprijs=" + aankoopprijs + ", auteurs="
				+ auteurs;
	}
	


}
