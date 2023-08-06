package com.springBoot_ExamenOpdracht_ewd_spring;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import domein.Boek;
import domein.Favoriet;
import domein.Gebruiker;
import formObjecten.FormBoek;
import repository.BoekRepository;
import repository.FavorietRepository;
import repository.GebruikerRepository;
import service.BoekService;
import service.FavorietService;
import service.FormBoekService;
import service.GebruikerPrincipal;
import service.GebruikerService;
import utils.StandAloneMvcTestViewResolver;
import validator.FavorietValidation;
import validator.FormBoekValidation;

@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoekControllerMockTest {
	
	@Autowired
	private FormBoekValidation formBoekValidation;
	@Autowired 
	private FormBoekService formBoekService;
	@Autowired
	private MessageSource messageSource;
	@Mock
	private GebruikerService gebruikerServiceMock;
	@Autowired
	private FavorietValidation favorietValidation;
	@Mock 
	private BoekService mockBs;
	@Mock 
	private FavorietService mockFs;
	private BoekController controller;
	private MockMvc mockMvc;
	private Gebruiker expectedGebruiker;
	
	@BeforeEach
	public void before() {
		
		MockitoAnnotations.openMocks(this);
		controller= new BoekController();
		mockMvc= MockMvcBuilders.standaloneSetup(controller).setViewResolvers(new  StandAloneMvcTestViewResolver()).build();
		ReflectionTestUtils.setField(controller, "formBoekValidation", formBoekValidation);
		ReflectionTestUtils.setField(controller, "formBoekService", formBoekService);
		ReflectionTestUtils.setField(controller, "messageSource", messageSource);
		ReflectionTestUtils.setField(controller, "gebruikerService", gebruikerServiceMock);
		ReflectionTestUtils.setField(controller, "favorietValidation", favorietValidation);
		ReflectionTestUtils.setField(controller, "boekService", mockBs);
		ReflectionTestUtils.setField(controller, "favorietService", mockFs);
		GebruikerPrincipal gebruikerPrincipal = (GebruikerPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		expectedGebruiker = gebruikerPrincipal.getGebruiker();
		Mockito.when(gebruikerServiceMock.getIngelogdeGebruiker()).thenReturn(expectedGebruiker);
	}
	@Test
	@WithUserDetails("admin")
	public void testGetRequestListBoeken() throws Exception {
		
		List<Boek> expResult= new ArrayList<>();
		expResult.add(new Boek("boek", "9780060883287", 40, "https://image.url"));
		Mockito.when(mockBs.findAll()).thenReturn(expResult);
		mockMvc.perform(get("/boeken")).andExpect(view().name("boeken")).andExpect(status().isOk())
				.andExpect(model().attributeExists("favoriet", "boekenList", "gebruiker", "activePage"))
				.andExpect(model().attribute("boekenList",expResult))
				.andExpect(model().attribute("gebruiker", expectedGebruiker))
				.andExpect(model().attribute("activePage", "boek"))
				.andExpect(model().attribute("favoriet", new Favoriet()));
						
	}
	
	@Test
	@WithUserDetails("admin")
	public void testShowDetailBoek() throws Exception {
		Long id = 1L;
		Boek expResultBoek= new Boek("boek", "9780060883287", 40, "https://image.url");
		Mockito.when(mockBs.findById(id)).thenReturn(expResultBoek);
		mockMvc.perform(get("/boeken/{id}", id)).andExpect(model().attributeExists("gebruiker", "boek"))
				.andExpect(view().name("grade/detailBoek")).andExpect(status().isOk())
				.andExpect(model().attribute("boek",expResultBoek))
				.andExpect(model().attribute("gebruiker", expectedGebruiker));
	}

	@Test
	@WithUserDetails("admin")
	public void testListPopulaireBoeken() throws Exception {
		List<Boek> expResult= new ArrayList<>();
		expResult.add(new Boek("boek", "9780060883287", 40, "https://image.url"));
		Mockito.when(mockBs.findMostPopular(PageRequest.of(0, 5))).thenReturn(expResult);
		mockMvc.perform(get("/boeken/populair"))
				.andExpect(model().attributeExists("gebruiker", "boekenList", "activePage"))
				.andExpect(view().name("populaireBoeken")).andExpect(status().isOk())
				.andExpect(model().attribute("boekenList",expResult))
				.andExpect(model().attribute("gebruiker", expectedGebruiker))
				.andExpect(model().attribute("activePage", "populaireBoeken"));
	}

	@Test
	@WithUserDetails("admin")
	public void testGetMaakBoek() throws Exception {
	    mockMvc.perform(get("/maakBoek"))
	        .andExpect(model().attributeExists("gebruiker", "formBoek", "activePage"))
	        .andExpect(view().name("boekForm"))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("gebruiker", expectedGebruiker))
	        .andExpect(model().attribute("formBoek", new FormBoek()))
	        .andExpect(model().attribute("activePage", "boekForm"));
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
						"plaatscode11"))
				.andExpect(model().attribute("gebruiker", expectedGebruiker));

	}

	@Test
	@WithUserDetails("admin")
	public void testHandleFavorietForm() throws Exception {
		Long id = 1L;
		Boek expResultBoek= new Boek("boek", "9780060883287", 40, "https://image.url");
		expResultBoek.setId(id);
		Mockito.when(mockBs.findById(id)).thenReturn(expResultBoek);
		Mockito.when(mockFs.findByGebruikerAndBoek(expectedGebruiker, expResultBoek)).thenReturn(null);
		Favoriet favoriet = new Favoriet(expResultBoek, expectedGebruiker);

		// Perform the request
		mockMvc.perform(post("/boeken/favoriet").flashAttr("favoriet", favoriet).param("boek", String.valueOf(id))
				.with(csrf().asHeader())).andExpect(status().is3xxRedirection())
				.andExpect(flash().attributeExists("favorietMessage"));

	}

	@Test
	@WithUserDetails("admin")
	public void testHandleFavorietFormTeveelFavorieten() throws Exception {
		Long id = 1L;
		Boek expResultBoek= new Boek("boek", "9780060883287", 40, "https://image.url");
		expResultBoek.setId(id);
		expectedGebruiker.setMaxAantalFavorieten(5);
		Mockito.when(mockBs.findById(id)).thenReturn(expResultBoek);
		Mockito.when(mockFs.findByGebruikerAndBoek(expectedGebruiker, expResultBoek)).thenReturn(null);
		Favoriet favoriet = new Favoriet(expResultBoek, expectedGebruiker);

		// Perform the request
		mockMvc.perform(post("/boeken/favoriet").flashAttr("favoriet", favoriet).param("boek", String.valueOf(id))
				.with(csrf().asHeader())).andExpect(status().is3xxRedirection())
				.andExpect(flash().attributeExists("favorietError"));

	}
	
	
	
}
