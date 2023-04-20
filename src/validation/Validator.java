package validation;

public class Validator {
	
	public static boolean isValidEmail(String email) {
        String emailValidatie = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.trim().matches(emailValidatie);
    }
}
