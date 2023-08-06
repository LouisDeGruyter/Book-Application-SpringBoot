package com.springBoot_ExamenOpdracht_ewd_spring;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.commons.annotation.Testable;
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
import domein.Gebruiker;
import exceptions.AuteurNotFoundException;
import exceptions.BoekNotFoundException;
import service.AuteurService;
import service.BoekService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.InitFormatter.FORMATTER;
@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestAuteurControllerMockTest {

	@Mock
	private AuteurService auteurServiceMock;
	private RestAuteurController restController;
	private MockMvc mockMvc;
	@Autowired
	private MessageSource messageSource;
	private final Long auteurID=1L;
	
	private final Long ID1 = 10L;
	private final Long ID2 = 10L;
    private final String NAME = "Test";
    private final String ISBN1="9780060883287";
    private final String ISBN2="9780060883286";
    private final String URL="https://image.url";

    private String expectedFormattedQuality;
//    private final Set<Boek> boekenAuteur= new HashSet<>();
    
	@BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        restController = new RestAuteurController();
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
        ReflectionTestUtils.setField(restController, "auteurService", auteurServiceMock);
    }
	
	private Boek aBoek(Long id,String naam,String isbn,Double aankoopprijs,String URL) {
		Boek boek= new Boek(id,naam,isbn,aankoopprijs,URL);
		expectedFormattedQuality= FORMATTER.format(aankoopprijs);
		return boek;
	}
	
	
	@Test
	public void testGetAllBoekenAuteur() throws Exception {
		Boek boek1= aBoek(ID1, NAME, ISBN1, 15.1, URL);
		String expectedFormattedPrijs1=expectedFormattedQuality;
		Boek boek2= aBoek(ID2, NAME, ISBN2, 11.1555, URL);
		String expectedFormattedPrijs2=expectedFormattedQuality;
		Set<Boek> boekenAuteur= Set.of(boek1,boek2);
		Mockito.when(auteurServiceMock.getBoeken(auteurID)).thenReturn(boekenAuteur);
		mockMvc.perform(get("/rest/auteurs/boeken/"+auteurID)).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isNotEmpty())
		.andExpect(jsonPath("$[0].id").value(ID1))
        .andExpect(jsonPath("$[0].naam").value(NAME))
        .andExpect(jsonPath("$[0].aankoopprijs").value(expectedFormattedPrijs1))
        .andExpect(jsonPath("$[0].imageURL").value(URL))
		.andExpect(jsonPath("$[1].id").value(ID2))
        .andExpect(jsonPath("$[1].naam").value(NAME))
        .andExpect(jsonPath("$[1].aankoopprijs").value(expectedFormattedPrijs2))
        .andExpect(jsonPath("$[1].imageURL").value(URL));
		Mockito.verify(auteurServiceMock).getBoeken(auteurID);
	
	}
	@Test
	public void testAuteurNotFound() {
		Mockito.when(auteurServiceMock.getBoeken(auteurID)).thenThrow(new AuteurNotFoundException(auteurID, messageSource));
		Exception exception= assertThrows(Exception.class,()->{
			mockMvc.perform(get("/rest/auteurs/boeken/"+auteurID)).andReturn();
		});
		assertTrue(exception.getCause() instanceof AuteurNotFoundException);
		Mockito.verify(auteurServiceMock).getBoeken(auteurID);
	}
}
