package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Locatie implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private int plaatscode1;
	private int plaatscode2;
	private String plaatsnaam;
	
	@ManyToOne
	private Boek boek;
	public Locatie(int plaatscode1, int plaatscode2, String plaatsnaam) {
		setPlaatscode1(plaatscode1);
		setPlaatscode2(plaatscode2);
		setPlaatsnaam(plaatsnaam);
	}
	protected Locatie() {
		
	}
	public int getPlaatscode1() {
		return plaatscode1;
	}
	public void setPlaatscode1(int plaatscode1) {
		if(plaatscode1>300||plaatscode1<50) {
			throw new IllegalArgumentException("Plaatscode1 mag niet groter zijn dan 300 en niet kleiner dan 50");
		}
		this.plaatscode1 = plaatscode1;
	}
	public int getPlaatscode2() {
		return plaatscode2;
	}
	public void setPlaatscode2(int plaatscode2) {
		if(plaatscode2>300||plaatscode2<50||Math.abs(plaatscode1-plaatscode2)<50) {
			throw new IllegalArgumentException("Plaatscode2 mag niet groter zijn dan 300 en niet kleiner dan 50 en het verschil tussen plaatscode1 en plaatscode2 moet minstens  50 zijn.");
		}
		this.plaatscode2 = plaatscode2;
	}
	public String getPlaatsnaam() {
		return plaatsnaam;
	}
	public void setPlaatsnaam(String plaatsnaam) {
		this.plaatsnaam = plaatsnaam;
	}
	public Boek getBoek() {
		return boek;
	}
	public void setBoek(Boek boek) {
		this.boek = boek;
	}
	@Override
	public int hashCode() {
		return Objects.hash(plaatscode1, plaatscode2, plaatsnaam);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locatie other = (Locatie) obj;
		return plaatscode1 == other.plaatscode1 && plaatscode2 == other.plaatscode2
				&& Objects.equals(plaatsnaam, other.plaatsnaam);
	}
	@Override
	public String toString() {
		return "Locatie [plaatsnaam=" + plaatsnaam + ", plaatscode1=" + plaatscode1 + ", plaatscode2=" + plaatscode2
				+ "]";
	}
	
	

}
