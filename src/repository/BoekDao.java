package repository;

import java.util.List;

import domein.Boek;

public interface BoekDao {
	public Boek getByISBN(String isbn);
	public List<Boek> getMostPopular(int aantal);

}
