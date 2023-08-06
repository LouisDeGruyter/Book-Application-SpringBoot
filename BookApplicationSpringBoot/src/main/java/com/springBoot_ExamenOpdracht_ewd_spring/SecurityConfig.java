package com.springBoot_ExamenOpdracht_ewd_spring;

import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import service.GebruikerPrincipalDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private GebruikerPrincipalDetailsService gebruikerPrincipalDetailsService;
    @Autowired
    DataSource dataSource;

    
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
       
        
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder()).usersByUsernameQuery("SELECT email, wachtwoord, enabled FROM gebruiker WHERE email=?");
//       
//        
//    }
   
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().and()
                .authorizeHttpRequests(requests -> 
                		requests.requestMatchers("/login**").permitAll()
            	        		.requestMatchers("/css/**").permitAll()
            	        		.requestMatchers("/403**").permitAll()
            	        		.requestMatchers("/rest/***").permitAll()
            	        		.requestMatchers("/rest/boeken/isbn/*").permitAll()
            	        		.requestMatchers("/rest/auteurs/boeken/*").permitAll()
            	        		.requestMatchers("/rest/auteurs/boeken/**").permitAll()
            	        		.requestMatchers("/rest/boeken/isbn").permitAll()
            	        		.requestMatchers("/maakBoek").hasRole("ADMIN")
            	        		.requestMatchers("/**").hasAnyRole("USER","ADMIN")
                )
                
                .formLogin(form -> 
                		form.defaultSuccessUrl("/boeken", true)
                         	.loginPage("/login")
                         	.usernameParameter("username").passwordParameter("password"))
                .exceptionHandling().accessDeniedPage("/403");
        
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(this.gebruikerPrincipalDetailsService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	return daoAuthenticationProvider;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    
   
    
}