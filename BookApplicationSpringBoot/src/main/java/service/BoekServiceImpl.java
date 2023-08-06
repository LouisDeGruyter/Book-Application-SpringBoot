package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import domein.Boek;
import exceptions.BoekNotFoundException;
import repository.BoekRepository;

@Service
public class BoekServiceImpl implements BoekService {
	@Autowired
	BoekRepository boekRepository;
	@Autowired
	MessageSource messageSource;
	@Override
	public Boek getByIsbn(String isbn) {
		
		Boek boek= boekRepository.findByISBNNummer(isbn);
		if(boek == null) {
			throw new BoekNotFoundException(isbn,messageSource);
		}
		return boek;
	}
	@Override
	public Iterable<Boek> findAll() {
		
		return boekRepository.findAll();
	}
	@Override
	public Boek findById(Long id) {
		
		return boekRepository.findById(id).orElse(null);
	}
	@Override
	public Iterable<Boek> findMostPopular(PageRequest of) {
		return boekRepository.findMostPopular(of);
	}
	
}
