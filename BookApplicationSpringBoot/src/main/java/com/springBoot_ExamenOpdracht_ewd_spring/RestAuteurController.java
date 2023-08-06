package com.springBoot_ExamenOpdracht_ewd_spring;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domein.Boek;
import service.AuteurService;
import service.AuteurServiceImpl;

@RestController
@RequestMapping(value="/rest/auteurs")
public class RestAuteurController {
	@Autowired
	private AuteurService auteurService;
	
	@GetMapping("/boeken/{id}")
	public Set<Boek> getBoekenAuteur(@PathVariable("id") Long auteurId) {
		return auteurService.getBoeken(auteurId);
	}
}
