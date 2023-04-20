package domein;

import javax.persistence.Entity;

@Entity
public class Admin extends Gebruiker{
	private static final long serialVersionUID = 1L;

	public Admin(String email, String wachtwoord) {
		super(email, wachtwoord);
	}
	protected Admin() {
	}
}
