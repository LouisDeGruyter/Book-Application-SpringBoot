package com.springBoot_ExamenOpdracht_ewd_spring;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domein.Boek;
import domein.Favoriet;
import domein.Gebruiker;
import formObjecten.FormBoek;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import service.BoekService;
import service.FavorietService;
import service.FormBoekService;
import service.GebruikerService;
import validator.FavorietValidation;
import validator.FormBoekValidation;
;




@Controller
public class BoekController {
	
	
	@Autowired
	private GebruikerService gebruikerService;
	
	 @Autowired
	 private FavorietValidation favorietValidation;
	 @Autowired
	 private MessageSource messageSource;
	 @Autowired 
	 private FormBoekValidation formBoekValidation;
	 @Autowired
	 private FormBoekService formBoekService;
	 @Autowired
	 private BoekService boekService;
	 @Autowired
	 private FavorietService favorietService;
	 
	@GetMapping("/boeken")
	public String listBoeken(Model model) {
		model.addAttribute("favoriet",new Favoriet());
		model.addAttribute("boekenList", boekService.findAll());
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		model.addAttribute("activePage", "boek");
		return "boeken";
	}

	@GetMapping("/boeken/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		Boek boek= boekService.findById(id);
		if(boek == null) {
			return "redirect:/boeken";
		}
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		model.addAttribute("boek", boek);
		return "grade/detailBoek";
	}
	@GetMapping("/boeken/populair")
	public String listPopulaireBoeken(Model model) {
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		model.addAttribute("boekenList", boekService.findMostPopular(PageRequest.of(0, 5)));
		model.addAttribute("activePage", "populaireBoeken");
		
		return "populaireBoeken";
	}
	@GetMapping("/maakBoek")
	public String maakBoek(Model model) {
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		model.addAttribute("formBoek",new FormBoek());
		model.addAttribute("activePage", "boekForm");
		return "boekForm";
	}
	@GetMapping("/updateBoek")
	public String updateBoek(Model model,@RequestParam("boek") Long boekId) {
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		Boek boek=boekService.findById(boekId); 
		model.addAttribute("formBoek",new FormBoek(boek,boek.getISBNNummer()));
		return "boekForm";
	}
	
	@PostMapping("/maakBoek")
	public String addBoek(@Valid FormBoek formboek, BindingResult bindingResult,Model model,RedirectAttributes ra) {
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		formBoekValidation.validate(formboek, bindingResult);
	    if (bindingResult.hasErrors()) {
	        return "boekform";
	    }
	    formBoekService.updateOrAddFormBoek(formboek);
	    if(formboek.getId()==null) { 	
		    ra.addFlashAttribute("succesMessage",messageSource.getMessage("boekAangemaakt", null, LocaleContextHolder.getLocale()));
	    }
	    else {
	 	    ra.addFlashAttribute("succesMessage",messageSource.getMessage("boekGeupdate", null, LocaleContextHolder.getLocale()));
	    }
	    
	    return "redirect:/maakBoek";
	}
	

	@PostMapping("/boeken/favoriet")
	public String handleFavorietForm( Favoriet favoriet,BindingResult result,Model model,HttpServletRequest request, @RequestParam("boek") Long boekId, RedirectAttributes ra) {    
	    Gebruiker gebruiker = gebruikerService.getIngelogdeGebruiker();
	    Boek boek= boekService.findById(boekId);
	    favorietService.setBoekAndGebruiker(favoriet,boek,gebruiker);
	    Favoriet favorietFind= favorietService.findByGebruikerAndBoek(favoriet.getGebruiker(), favoriet.getBoek());
	    String referer = request.getHeader("Referer");
	    
	    if (favorietFind==null) {	
	    	favorietValidation.validate(favoriet, result);
	    
	    	if (result.hasFieldErrors("gebruiker")) {
	    		
	    		String errorMessage = result.getFieldError("gebruiker").getDefaultMessage();
	            ra.addFlashAttribute("favorietError", errorMessage);
	            return "redirect:" + referer;
	        }
	    	ra.addFlashAttribute("favorietMessage",messageSource.getMessage("favorietToegevoegd", new Object[] {boek.getNaam()}, LocaleContextHolder.getLocale()));
	    	gebruikerService.addFavoriet(gebruiker,favoriet );
	    	
	        
	    } else {
	    	ra.addFlashAttribute("favorietMessage",messageSource.getMessage("favorietVerwijderd", new Object[] {boek.getNaam()}, LocaleContextHolder.getLocale()));
	    	gebruikerService.removeFavoriet(gebruiker, favorietFind);
	    }
	    
	    
	    return "redirect:" + referer; 
	}
	

}
