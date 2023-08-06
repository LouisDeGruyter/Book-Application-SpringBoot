package com.springBoot_ExamenOpdracht_ewd_spring;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import ch.qos.logback.core.status.Status;
import domein.Boek;
import domein.Favoriet;
import domein.Gebruiker;
import formObjecten.FormBoek;
import repository.BoekRepository;
import service.GebruikerPrincipal;

@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoekControllerTest {
	@Autowired
	private BoekRepository br;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithUserDetails("admin")
	public void testGetRequestListBoeken() throws Exception {
		mockMvc.perform(get("/boeken")).andExpect(view().name("boeken")).andExpect(status().isOk())
				.andExpect(model().attributeExists("favoriet", "boekenList", "gebruiker", "activePage"));
	}

	@Test
	@WithUserDetails("admin")
	public void testShowDetailBoek() throws Exception {
		// Mock any dependencies if needed
		Long id = 1L;

		mockMvc.perform(get("/boeken/{id}", id)).andExpect(model().attributeExists("gebruiker", "boek"))
				.andExpect(view().name("grade/detailBoek")).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("admin")
	public void testListPopulaireBoeken() throws Exception {

		mockMvc.perform(get("/boeken/populair"))
				.andExpect(model().attributeExists("gebruiker", "boekenList", "activePage"))
				.andExpect(view().name("populaireBoeken")).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("admin")
	public void testGetMaakBoek() throws Exception {
		GebruikerPrincipal gebruikerPrincipal = (GebruikerPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		Gebruiker gebruiker= gebruikerPrincipal.getGebruiker();	
		mockMvc.perform(get("/maakBoek")).andExpect(model().attributeExists("gebruiker", "formBoek", "activePage"))
				.andExpect(view().name("boekForm")).andExpect(status().isOk()).andExpect(model().attribute("gebruiker", gebruiker));
	}
	@Test
	@WithUserDetails("admin")
	public void testGetUpdateBoek() throws Exception {

		mockMvc.perform(get("/maakBoek")).andExpect(model().attributeExists("gebruiker", "formBoek", "activePage"))
				.andExpect(view().name("boekForm")).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("admin")
	public void testAddBoek() throws Exception {
		FormBoek formBoek = new FormBoek();
		formBoek.setNaam("De hulp");
		formBoek.setISBNNummer("9789032520267");
		formBoek.setAankoopprijs(4.99);
		formBoek.setVoornaam1("Freida");
		formBoek.setAchternaam1("Mcfadden");
		formBoek.setImageURL("https://media.s-bol.com/qpnOn3Q4KzgD/rVBvVW/546x840.jpg");
		formBoek.setPlaatscode11(50);
		formBoek.setPlaatscode12(100);
		formBoek.setPlaatsnaam1("Gent");

		mockMvc.perform(post("/maakBoek").flashAttr("formBoek", formBoek).with(csrf().asHeader()))
				.andExpect(status().is3xxRedirection()).andExpect(flash().attributeExists("succesMessage"))
				.andExpect(redirectedUrl("/maakBoek"));

	}

	@Test
	@WithUserDetails("admin")
	public void testAddBoekFout() throws Exception {
		FormBoek formBoek = new FormBoek();
		mockMvc.perform(post("/maakBoek").flashAttr("formBoek", formBoek).with(csrf().asHeader()))
				.andExpect(status().isOk()).andExpect(view().name("boekform"))
				.andExpect(model().attributeHasFieldErrors("formBoek", "naam", "ISBNNummer", "voornaam1", "imageURL",
						"plaatscode11"));

	}

	@Test
	@WithUserDetails("admin")
	public void testHandleFavorietForm() throws Exception {
		Long boekId = 1L;
		GebruikerPrincipal gebruikerPrincipal = (GebruikerPrincipal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Gebruiker gebruiker = gebruikerPrincipal.getGebruiker();
		Boek boek = br.findById(boekId).orElse(null);
		Favoriet favoriet = new Favoriet(boek, gebruiker);

		// Perform the request
		mockMvc.perform(post("/boeken/favoriet").flashAttr("favoriet", favoriet).param("boek", String.valueOf(boekId))
				.with(csrf().asHeader())).andExpect(status().is3xxRedirection())
				.andExpect(flash().attributeExists("favorietMessage"));

	}

	@Test
	@WithUserDetails("admin")
	public void testHandleFavorietFormTeveelFavorieten() throws Exception {
		Long boekId = 1L;
		GebruikerPrincipal gebruikerPrincipal = (GebruikerPrincipal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Gebruiker gebruiker = gebruikerPrincipal.getGebruiker();
		gebruiker.setMaxAantalFavorieten(5);//instellen  op 5 (admin heeft standaard al 5 favorieten bij het initialiseren)
		Boek boek = br.findById(boekId).orElse(null);
		Favoriet favoriet = new Favoriet(boek, gebruiker);

		// Perform the request
		mockMvc.perform(post("/boeken/favoriet").flashAttr("favoriet", favoriet).param("boek", String.valueOf(boekId))
				.with(csrf().asHeader())).andExpect(status().is3xxRedirection())
				.andExpect(flash().attributeExists("favorietError"));

	}

}
