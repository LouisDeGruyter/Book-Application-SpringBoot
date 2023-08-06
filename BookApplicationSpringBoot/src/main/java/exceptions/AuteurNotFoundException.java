package exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class AuteurNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AuteurNotFoundException(Long auteurId,MessageSource messageSource) {
		super(messageSource.getMessage("auteurNotFoundId", new Object[] { auteurId},LocaleContextHolder.getLocale()));
	  }

	public AuteurNotFoundException(String voornaam, String achternaam,MessageSource messageSource) {
		super(messageSource.getMessage("auteurNotFoundName", new Object[] { voornaam, achternaam},LocaleContextHolder.getLocale()));
		
	}
}
