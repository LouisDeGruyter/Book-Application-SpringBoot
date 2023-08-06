package com.springBoot_ExamenOpdracht_ewd_spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginGet() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void accessDeniedPageGet() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"));
    }

   

    @WithUserDetails("user1")
    @Test
    public void testAccessMaakBoekWithWrongRole() throws Exception {
    	loginUser();
        mockMvc.perform(get("/maakBoek"))
                .andExpect(status().isForbidden());
    }


	@WithUserDetails("admin")
    @Test
    public void testAccessMaakBoekWithAdminRole() throws Exception {
        
        mockMvc.perform(get("/maakBoek"))
                .andExpect(status().isOk()).
                andExpect(view().name("boekForm"));
    }
	@Test
	void testWrongPassword() throws Exception {
		mockMvc.perform(formLogin("/login")
				.user("username", "user1")
				.password("password", "wrongpassword"))
				.andExpect(status().isFound()) 
				.andExpect(redirectedUrl("/login?error"));
	}

	@Test
	void testCorrectPassword() throws Exception {
		mockMvc.perform(formLogin("/login")
				.user("username", "user1")
				.password("password", "password1"))
				.andExpect(status().isFound()) 
				.andExpect(redirectedUrl("/boeken"));
	}
	
	
	public void loginUser() throws Exception{
		mockMvc.perform(formLogin("/login")
				.user("username", "user1")
				.password("password", "password1"));
				
	}
	public void loginAdmin() throws Exception{
		mockMvc.perform(formLogin("/login")
				.user("username", "admin")
				.password("password", "admin"));
				
	}
}




