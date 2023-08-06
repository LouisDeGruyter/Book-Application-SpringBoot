package service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import domein.Boek;

public interface BoekService {
	public Boek getByIsbn(String isbn);

	public Iterable<Boek> findAll();

	public Boek findById(Long id);

	public Iterable<Boek> findMostPopular(PageRequest of);

}
