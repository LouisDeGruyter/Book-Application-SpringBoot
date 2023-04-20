package domein;

import javax.persistence.Entity;

@Entity
public class User extends Gebruiker {

	private static final long serialVersionUID = 1L;

	public User(String email, String wachtwoord) {
		super(email, wachtwoord);
	}
	protected User() {
		
	}
}

