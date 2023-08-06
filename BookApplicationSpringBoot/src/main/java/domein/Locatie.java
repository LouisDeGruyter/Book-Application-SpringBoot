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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "plaatscode1","plaatscode2","plaatsnaam"})
@NamedQueries({
	@NamedQuery
	(name="Locatie.findByAttributes", query="select l from Locatie l where l.plaatscode1= :plaatscode1 and l.plaatscode2= :plaatscode2 and l.plaatsnaam= :plaatsnaam")
})
public class Locatie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter private int plaatscode1;
	@Getter @Setter private int plaatscode2;
	@Getter @Setter private String plaatsnaam;
	
	@ManyToOne
	@Getter @Setter private Boek boek;
	public Locatie(int plaatscode1, int plaatscode2, String plaatsnaam) {
		setPlaatscode1(plaatscode1);
		setPlaatscode2(plaatscode2);
		setPlaatsnaam(plaatsnaam);
	}
	
	@Override
	public String toString() {
		return "Locatie " + plaatsnaam + ", plaatscode1=" + plaatscode1 + ", plaatscode2=" + plaatscode2 + boek;
	}
	
	

}
