package com.springBoot_ExamenOpdracht_ewd_spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domein.Auteur;
import domein.Gebruiker;
import repository.AuteurRepository;
import service.AuteurService;
import service.GebruikerPrincipal;
import service.GebruikerService;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {
	
	@Autowired
	private AuteurService as;
	@Autowired
	private GebruikerService gebruikerService;
	
	@GetMapping
	public String listAuteurs(Model model, Authentication authentication) {
		
		model.addAttribute("gebruiker", gebruikerService.getIngelogdeGebruiker());
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("username", authentication.getName());
        model.addAttribute("userListRoles", listRoles);
		model.addAttribute("auteurList", as.findAll());
		model.addAttribute("auteurName", as.findByName("Jane", "Austen"));
		model.addAttribute("activePage", "auteur");
		return "auteur";
	}
}
