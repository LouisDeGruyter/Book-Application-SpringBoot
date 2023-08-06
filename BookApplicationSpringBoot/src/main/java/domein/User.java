package domein;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Gebruiker {

	private static final long serialVersionUID = 1L;

	public User(String email, String wachtwoord) {
		super(email, wachtwoord);
		setRoles("ROLE_USER");
	}
	
}

