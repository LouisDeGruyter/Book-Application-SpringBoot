package domein;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Admin extends Gebruiker{
	private static final long serialVersionUID = 1L;

	public Admin(String email, String wachtwoord) {
		super(email, wachtwoord);
		setRoles("ROLE_ADMIN");
	}
	
}
