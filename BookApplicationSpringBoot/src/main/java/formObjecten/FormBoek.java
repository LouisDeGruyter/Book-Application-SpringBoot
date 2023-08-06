package formObjecten;


import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.Range;

import domein.Auteur;
import domein.Boek;
import domein.Locatie;
import domein.MaxSizeSet;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern.List;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter
@NoArgsConstructor
@ToString
public class FormBoek {
	@Override
	public int hashCode() {
		return Objects.hash(ISBNNummer, aankoopprijs, achternaam1, achternaam2, achternaam3, id, imageURL, naam,
				originalISBN, plaatscode11, plaatscode12, plaatscode21, plaatscode22, plaatscode31, plaatscode32,
				plaatsnaam1, plaatsnaam2, plaatsnaam3, voornaam1, voornaam2, voornaam3);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormBoek other = (FormBoek) obj;
		return Objects.equals(ISBNNummer, other.ISBNNummer) && Objects.equals(aankoopprijs, other.aankoopprijs)
				&& Objects.equals(achternaam1, other.achternaam1) && Objects.equals(achternaam2, other.achternaam2)
				&& Objects.equals(achternaam3, other.achternaam3) && Objects.equals(id, other.id)
				&& Objects.equals(imageURL, other.imageURL) && Objects.equals(naam, other.naam)
				&& Objects.equals(originalISBN, other.originalISBN) && Objects.equals(plaatscode11, other.plaatscode11)
				&& Objects.equals(plaatscode12, other.plaatscode12) && Objects.equals(plaatscode21, other.plaatscode21)
				&& Objects.equals(plaatscode22, other.plaatscode22) && Objects.equals(plaatscode31, other.plaatscode31)
				&& Objects.equals(plaatscode32, other.plaatscode32) && Objects.equals(plaatsnaam1, other.plaatsnaam1)
				&& Objects.equals(plaatsnaam2, other.plaatsnaam2) && Objects.equals(plaatsnaam3, other.plaatsnaam3)
				&& Objects.equals(voornaam1, other.voornaam1) && Objects.equals(voornaam2, other.voornaam2)
				&& Objects.equals(voornaam3, other.voornaam3);
	}
	public FormBoek(String isbn) {
		setISBNNummer(isbn);
	}
	//boek
	@NotBlank(message = "{validation.NotBlank.messageName}")
	private String naam="";
	//check in FormBoekValidation
	@NotBlank(message = "{validation.NotBlank.messageISBN}")
    private String ISBNNummer="";
	@Positive(message = "{validation.Positive.message}")
    @DecimalMax(value = "99.99", message = "{validation.DecimalMax.message}")
	@Nullable
	private Double aankoopprijs;
	@NotBlank(message = "{validation.NotBlank.messageImageURL}")
	private String imageURL="";
	
	
	//auteurs
	//auteur1
	@Nullable
	private String voornaam1="";
	@Nullable
	private String achternaam1="";
	//auteur2
	@Nullable
	private String voornaam2="";
	@Nullable
	private String achternaam2="";
	//auteur3
	@Nullable
	private String voornaam3="";
	@Nullable
	private String achternaam3="";
	
	//locaties
	//locatie1
	@Range(min = 50, max = 300, message = "{validation.Range.messagePlaatscode}")
	@Nullable
    private Integer plaatscode11;
	@Nullable
    @Range(min = 50, max = 300, message = "{validation.Range.messagePlaatscode}")
    private Integer plaatscode12;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "{validation.Pattern.messagePlaatsnaam}")
	@Nullable
	@NotBlank
	private String plaatsnaam1="";
    
	//locatie2
	@Range(min = 50, max = 300, message = "{validation.Range.messagePlaatscode}")
	@Nullable
	private Integer plaatscode21;
	@Range(min = 50, max = 300, message = "{validation.Range.messagePlaatscode}")
	@Nullable
	private Integer plaatscode22;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "{validation.Pattern.messagePlaatsnaam}")
	@Nullable
	private String plaatsnaam2="";

	
	 //locatie3
	@Range(min = 50, max = 300, message = "{validation.Range.messagePlaatscode}")
	@Nullable
	private Integer plaatscode31;
	@Range(min = 50, max = 300, message = "{validation.Range.messagePlaatscode}")
	@Nullable
	private Integer plaatscode32;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "{validation.Pattern.messagePlaatsnaam}")
	@Nullable
    private String plaatsnaam3="";
	
	
	//update attributes
	private Long id=null;
	private String originalISBN=null;
	public FormBoek(Boek boek,String originalISBN) {
		setOriginalISBN(originalISBN);
		setAankoopprijs(boek.getAankoopprijs());
		setId(boek.getId());
		setNaam(boek.getNaam());
		setISBNNummer(boek.getISBNNummer());
		setImageURL(boek.getImageURL());
		Set<Auteur> auteurs = boek.getAuteurs();
		int authorIndex = 1;
		for (Auteur auteur : auteurs) {
		    switch (authorIndex) {
		        case 1:
		            voornaam1 = auteur.getVoornaam();
		            achternaam1 = auteur.getAchternaam();
		            break;
		        case 2:
		            voornaam2 = auteur.getVoornaam();
		            achternaam2 = auteur.getAchternaam();
		            break;
		        case 3:
		            voornaam3 = auteur.getVoornaam();
		            achternaam3 = auteur.getAchternaam();
		            break;
		        default:
		            break;
		    }
		    
		    authorIndex++;
		}
		Set<Locatie> locaties = boek.getLocaties();

		int locatieIndex = 1; // Start with the first locatie

		for (Locatie locatie : locaties) {
		    if (locatieIndex == 1) {
		        plaatscode11 = locatie.getPlaatscode1();
		        plaatscode12 = locatie.getPlaatscode2();
		        plaatsnaam1 = locatie.getPlaatsnaam();
		    } else if (locatieIndex == 2) {
		        plaatscode21 = locatie.getPlaatscode1();
		        plaatscode22 = locatie.getPlaatscode2();
		        plaatsnaam2 = locatie.getPlaatsnaam();
		    } else if (locatieIndex == 3) {
		        plaatscode31 = locatie.getPlaatscode1();
		        plaatscode32 = locatie.getPlaatscode2();
		        plaatsnaam3 = locatie.getPlaatsnaam();
		    }

		    locatieIndex++;
		}
		
		
	}
}
