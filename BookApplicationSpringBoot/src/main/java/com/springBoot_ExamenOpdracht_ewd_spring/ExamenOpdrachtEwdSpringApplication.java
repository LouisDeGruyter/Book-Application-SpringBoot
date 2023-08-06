package com.springBoot_ExamenOpdracht_ewd_spring;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import service.GebruikerService;
import service.GebruikerServiceImpl;
import validator.FavorietValidation;
import validator.FormBoekValidation;




@SpringBootApplication
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"domein"})
@ComponentScans({
	@ComponentScan("service"),
	@ComponentScan("domein"),
	@ComponentScan("repository")
})
public class ExamenOpdrachtEwdSpringApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ExamenOpdrachtEwdSpringApplication.class, args);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
	    registry.addViewController("/403").setViewName("403");
	}

//	@Bean
//	LocaleResolver localeResolver() {
//	    SessionLocaleResolver slr = new SessionLocaleResolver();
//	    slr.setDefaultLocale(Locale.ENGLISH);
//	    return slr;
//	}
	@Bean
	LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(new Locale("nl", "NL"));
	    return slr;
	}
	@Bean
	FavorietValidation favorietValidation() {
		return new FavorietValidation();
	}
	@Bean
	FormBoekValidation formBoekValidation() {
		return new FormBoekValidation();
	}



	
	

}
