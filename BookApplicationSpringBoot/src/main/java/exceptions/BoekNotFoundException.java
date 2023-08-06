package exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BoekNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	public BoekNotFoundException(Integer id,MessageSource messageSource) {
		super(messageSource.getMessage("BoekNotFoundId", new Object[] { id},LocaleContextHolder.getLocale()));
	    
	  }
	public BoekNotFoundException(String isbn,MessageSource messageSource) {
		super(messageSource.getMessage("BoekNotFoundIsbn", new Object[] { isbn},LocaleContextHolder.getLocale()));
	    
	  }

}
