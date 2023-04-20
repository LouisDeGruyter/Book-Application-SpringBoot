package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.OneToMany;

import validation.Validator;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Gebruiker implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy ="gebruiker")
	private List<Favoriet> favorieten;
	private String email;
	private String wachtwoord;
	
	private int maxAantalFavorieten = new SecureRandom().nextInt(6)+5;
	
	public Gebruiker(String email, String wachtwoord) {
		setEmail(email);
		setWachtwoord(wachtwoord);
	}
	protected Gebruiker() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(Validator.isValidEmail(email)) {
			this.email = email;
		}
		else {
			throw new IllegalArgumentException(email+" is geen geldig emailadres");
		}
		
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	public boolean verifieerWachtwoord(String wachtwoord) {
		return wachtwoord.equals(this.wachtwoord);
	}

	public void setFavorieten(List<Favoriet> favorieten) {
		this.favorieten = favorieten;
	}

	public void setMaxAantalFavorieten(int maxAantalFavorieten) {
		this.maxAantalFavorieten = maxAantalFavorieten;
	}

	public List<Favoriet> getFavorieten() {
		return Collections.unmodifiableList(favorieten);
	}
	public void addFavoriet(Favoriet favoriet) {
		favorieten.add(favoriet);
	}

	public int getMaxAantalFavorieten() {
		return maxAantalFavorieten;
	}
}
