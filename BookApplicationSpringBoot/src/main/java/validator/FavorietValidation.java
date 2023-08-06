package validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domein.Favoriet;
import domein.Gebruiker;

public class FavorietValidation implements Validator{
	@Autowired
	MessageSource messageSource;
	@Override
	public boolean supports(Class<?> klass) {
		return Favoriet.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Favoriet favoriet = (Favoriet) target;
		Gebruiker gebruiker=favoriet.getGebruiker();
		int maxFavorieten=gebruiker.getMaxAantalFavorieten();
		int huidigAantalFavorieten=gebruiker.getFavorieten().size();
		if(maxFavorieten <=huidigAantalFavorieten) {
			  errors.rejectValue("gebruiker",
	                    "teveelFavorieten",new Object[] {maxFavorieten},
	                    messageSource.getMessage("teveelFavorieten", new Object[] { maxFavorieten }, LocaleContextHolder.getLocale()));
		}
		
	}
}


