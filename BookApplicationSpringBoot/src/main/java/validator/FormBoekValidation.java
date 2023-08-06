package validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import formObjecten.FormBoek;
import repository.BoekRepository;

public class FormBoekValidation implements Validator {
	@Autowired
	BoekRepository boekRepository;
	@Override
	public boolean supports(Class<?> klass) {
		return FormBoek.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FormBoek form = (FormBoek) target;
		String isbnNummer = form.getISBNNummer();
		
		isbnNummer = isbnNummer.replaceAll("[^0-9]", "");
		boolean isbnNummerError=false;
		if(isbnNummer.length()!=13) {
			isbnNummerError=true;
			errors.rejectValue("ISBNNummer","ongeldigIsbnLength", new Object[] {13,isbnNummer.length()},
					"Isbn must be"+13+" characters long");
		}
		
		if(!isbnNummerError&&boekRepository.findByISBNNummer(isbnNummer)!=null&&!isbnNummer.equals(form.getOriginalISBN())) {
			System.out.println(isbnNummer);
			System.out.println(form.getOriginalISBN());
			isbnNummerError=true;
			errors.rejectValue("ISBNNummer","duplicateIsbn",
					"Isbn is already present in the database");
		}
		
		if (!isbnNummerError && berekenISBNControleCijfer(isbnNummer) != Character.getNumericValue(isbnNummer.charAt(isbnNummer.length() - 1))) {
		    isbnNummerError = true;
		  
		    errors.rejectValue("ISBNNummer", "ongeldigIsbn", new Object[]{isbnNummer},
		            "Isbn " + isbnNummer + " is not a valid isbn");
		}

		if (form.getVoornaam1() == "" && form.getAchternaam1() == "" && form.getVoornaam2() == ""
				&& form.getAchternaam2() == "" && form.getVoornaam3() == "" && form.getAchternaam3() == "") {
				errors.rejectValue("voornaam1", "validation.atLeastOneAuthor.message");
				}
		if(isAtLeastOneLocationFilledIn(form)==false) {
			errors.rejectValue("plaatscode11","validation.atLeastOneLocation.message");
		}
		if (!checkPlaatscodeDifference(form)) {
		    errors.rejectValue("plaatscode11", "validation.plaatscodeDifference.message");
		}
		validateFormBoekLocationsFilledIn(form,errors);
		validateFormBoekAuteursFilledIn(form,errors);

		
	}
	

	public static int berekenISBNControleCijfer(String isbn) {
	    int somOnevenPosities = 0;
	    int somEvenPosities = 0;
	    
	    for (int i = 0; i < isbn.length()-1; i++) {
	        int cijfer = Character.getNumericValue(isbn.charAt(i));
	        if (i % 2 == 0) {
	            somOnevenPosities += cijfer;
	        } else {
	            somEvenPosities += cijfer;
	        }
	    }
	    
	    int som = somOnevenPosities + somEvenPosities * 3;
	    int rest = som % 10;
	    int controleCijfer = rest == 0 ? 0 : 10 - rest;
	    return controleCijfer;
	}
	public boolean isAtLeastOneLocationFilledIn(FormBoek form) {
	    if ((form.getPlaatscode11() == null && form.getPlaatscode12() == null && form.getPlaatsnaam1() == "") 
	        && (form.getPlaatscode21() == null && form.getPlaatscode22() == null && form.getPlaatsnaam2() == "")
	        && (form.getPlaatscode31() == null && form.getPlaatscode32() == null && form.getPlaatsnaam3() == "")) {
	            return false;
	    }
	    return true;
	}
	public boolean checkPlaatscodeDifference(FormBoek form) {
	    boolean result = true;
	    
	    //locatie1
	    if (form.getPlaatscode11() != null && form.getPlaatscode12() != null && Math.abs(form.getPlaatscode11() - form.getPlaatscode12()) < 50) {
	        result = false;
	    }
	    
	    //locatie2
	    if (form.getPlaatscode21() != null && form.getPlaatscode22() != null && Math.abs(form.getPlaatscode21() - form.getPlaatscode22()) < 50) {
	        result = false;
	    }
	    
	    //locatie3
	    if (form.getPlaatscode31() != null && form.getPlaatscode32() != null && Math.abs(form.getPlaatscode31() - form.getPlaatscode32()) < 50) {
	        result = false;
	    }
	    
	    return result;
	}
	public void validateFormBoekLocationsFilledIn(FormBoek formBoek, Errors errors) {
	    // Check each location
	    validateLocation(formBoek.getPlaatscode11(), formBoek.getPlaatscode12(), formBoek.getPlaatsnaam1(), 1, errors);
	    validateLocation(formBoek.getPlaatscode21(), formBoek.getPlaatscode22(), formBoek.getPlaatsnaam2(), 2, errors);
	    validateLocation(formBoek.getPlaatscode31(), formBoek.getPlaatscode32(), formBoek.getPlaatsnaam3(), 3, errors);
	}

	private void validateLocation(Integer plaatscode1, Integer plaatscode2, String plaatsnaam, int locatie, Errors errors) {
	    // If any attribute is filled in, all attributes must be filled in
	    if ((plaatscode1 != null || plaatscode2 != null || plaatsnaam != "") && 
	        (plaatscode1 == null || plaatscode2 == null || plaatsnaam == "")) {
	    
	        errors.rejectValue("plaatscode"+locatie+"1","locatieNietIngevuld",new Object[] {locatie},"All field of location "+locatie+ " must be filled in");
	    }
	}
	private void validateFormBoekAuteursFilledIn(FormBoek form, Errors errors) {
		validateAuteur(form.getVoornaam1(), form.getAchternaam1(),1, errors);
		validateAuteur(form.getVoornaam2(), form.getAchternaam2(),2, errors);
		validateAuteur(form.getVoornaam3(), form.getAchternaam3(),3, errors);
		
	}
	private void validateAuteur(String voornaam, String achternaam,int auteur, Errors errors) {
		if((!voornaam.isBlank() || !achternaam.isBlank())&&
				(voornaam.isBlank()||achternaam.isBlank())) {
			
			
			errors.rejectValue("voornaam"+auteur, "auteurNietIngevuld",new Object[] {auteur},"All field of auteur "+auteur+ " must be filled in");
		}
	}






}
