package domein;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
 @NoArgsConstructor
@EqualsAndHashCode(of = { "gebruiker","boek"})
@NamedQueries({
	@NamedQuery(
		    name = "Favoriet.findByGebruikerAndBoek",
		    query = "SELECT f FROM Favoriet f WHERE f.gebruiker = :gebruiker AND f.boek = :boek"
		)
})
public class Favoriet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter private Long id;
	
	@ManyToOne
	@Getter @Setter private Gebruiker gebruiker;
	@ManyToOne
	@Getter @Setter private Boek boek;
	
	public Favoriet(Boek boek, Gebruiker gebruiker) {
		setBoek(boek);
		setGebruiker(gebruiker);
		
	}
	
	@Override
	public String toString() {
	    return "Favoriet: gebruiker=" + (gebruiker != null ? gebruiker.getUsername() : "geen gebruiker") +
	            ", boek=" + (boek != null ? boek.getNaam() : "geen boek");
	}

	
}
