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
import exceptions.BoekNotFoundException;
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
public class RestBoekControllerMockTest {

	@Mock
	private BoekService boekServiceMock;
	private RestBoekController restController;
	private MockMvc mockMvc;
	@Autowired
	private MessageSource messageSource;
	
	private final Long ID = 10L;
    private final String NAME = "Test";
    private final String ISBN="9780060883287";
    private final String URL="https://image.url";
    private String expectedFormattedQuality;
    
	@BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        restController = new RestBoekController();
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
        ReflectionTestUtils.setField(restController, "boekService", boekServiceMock);
    }
	
	private Boek aBoek(Long id,String naam,String isbn,Double aankoopprijs,String URL) {
		Boek boek= new Boek(id,naam,isbn,aankoopprijs,URL);
		expectedFormattedQuality= FORMATTER.format(aankoopprijs);
		return boek;
	}
	private void performRest(String uri) throws Exception {
	    ResultActions result = mockMvc.perform(get(uri))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id").value(ID))
	            .andExpect(jsonPath("$.naam").value(NAME))
	            .andExpect(jsonPath("$.aankoopprijs").value(expectedFormattedQuality))
	            .andExpect(jsonPath("$.imageURL").value(URL));

	    // Print the JSONPath
	    System.out.println(result.andReturn().getResponse().getContentAsString());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"5\t5,00","5.1\t5,10","5.123\t5,12"}, delimiter='\t')
	public void testBoek_isOk(double aankoopprijs,String formattedQuality) throws Exception {
		Mockito.when(boekServiceMock.getByIsbn(ISBN)).thenReturn(aBoek(ID, NAME, ISBN, aankoopprijs, URL));
		performRest("/rest/boeken/isbn/"+ISBN);
		assertEquals(formattedQuality, expectedFormattedQuality);
		Mockito.verify(boekServiceMock).getByIsbn(ISBN);
	}
	@Test
	public void testBoekNotFound() {
		Mockito.when(boekServiceMock.getByIsbn(ISBN)).thenThrow(new BoekNotFoundException(ISBN, messageSource));
		Exception exception= assertThrows(Exception.class,()->{
			mockMvc.perform(get("/rest/boeken/isbn/"+ISBN)).andReturn();
		});
		assertTrue(exception.getCause() instanceof BoekNotFoundException);
		Mockito.verify(boekServiceMock).getByIsbn(ISBN);
	}
}
