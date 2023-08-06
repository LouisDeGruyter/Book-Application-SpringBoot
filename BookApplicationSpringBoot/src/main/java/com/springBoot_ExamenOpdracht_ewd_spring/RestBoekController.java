package com.springBoot_ExamenOpdracht_ewd_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domein.Boek;
import service.BoekService;
import service.BoekServiceImpl;

@RestController
@RequestMapping(value="/rest/boeken")
public class RestBoekController {
	@Autowired
	private BoekService boekService;

	@GetMapping("/isbn/{isbn}")
	public Boek getBoekByISBN(@PathVariable String isbn) {
		return boekService.getByIsbn(isbn);
	}
}
