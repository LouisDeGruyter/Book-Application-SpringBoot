package com.springBoot_ExamenOpdracht_ewd_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domein.Admin;
import domein.Auteur;
import domein.Boek;
import domein.Favoriet;
import domein.Locatie;
import domein.User;
import repository.AuteurRepository;
import repository.BoekRepository;
import repository.FavorietRepository;
import repository.GebruikerRepository;
import repository.LocatieRepository;

@Component
public class InitDataConfig implements CommandLineRunner {
	@Autowired
	private AuteurRepository ar;
	@Autowired
	private BoekRepository br;
	@Autowired
	private FavorietRepository fr;
	@Autowired
	private GebruikerRepository gr;
	@Autowired
	private LocatieRepository lr;
	
	@Override
	public void run(String ... args) {
		//Create authors
		Auteur author1 = new Auteur("Gabriel", "García Márquez");
		Auteur author2 = new Auteur("J.R.R.", "Tolkien");
		Auteur author3 = new Auteur("Jane", "Austen");
		Auteur author4 = new Auteur("Leo", "Tolstoy");
		Auteur author5 = new Auteur("F. Scott", "Fitzgerald");
		Auteur author6 = new Auteur("Virginia", "Woolf");
		Auteur author7 = new Auteur("Ernest", "Hemingway");
		Auteur author8 = new Auteur("Mark", "Twain");
		Auteur author9 = new Auteur("Emily", "Bronte");
		Auteur author10 = new Auteur("Charlotte", "Bronte");
		Auteur author11 = new Auteur("Stephen", "King");
		Auteur author12 = new Auteur("J.K.", "Rowling");
		Auteur author13 = new Auteur("Agatha", "Christie");
		Auteur author14 = new Auteur("Dan", "Brown");
		Auteur author15 = new Auteur("Gillian", "Flynn");
		Auteur author16 = new Auteur("Ray", "Bradbury");
		Auteur author17 = new Auteur("Neil", "Gaiman");
		Auteur author18 = new Auteur("George", "Orwell");
		Auteur author19 = new Auteur("Aldous", "Huxley");
		Auteur author20 = new Auteur("Peter","Straub");
		

		
		// Create some books for each author
		Boek book1 = new Boek("One Hundred Years of Solitude", "9780060883287", 12.99,"https://m.media-amazon.com/images/I/81MI6+TpYkL._AC_UF1000,1000_QL80_.jpg");
		Boek book2 = new Boek("Love in the Time of Cholera", "9780307389732", 9.99,"https://m.media-amazon.com/images/M/MV5BMTQ3OTE0OTM4N15BMl5BanBnXkFtZTcwNDg3OTI1MQ@@._V1_.jpg");
		Boek book3 = new Boek("The Lord of the Rings", "9780261102361", 19.99,"https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg");
		Boek book4 = new Boek("The Hobbit", "9780261103306", 9.99,"https://m.media-amazon.com/images/I/710+HcoP38L._AC_UF1000,1000_QL80_.jpg");
		Boek book5 = new Boek("Pride and Prejudice", "9780141439518", 7.99,"https://images.squarespace-cdn.com/content/v1/58c180edff7c50dd0e51a2ad/1596041993633-UW2GTN4JZP8XLPZKKXCJ/9781847493699.jpg");
		Boek book6 = new Boek("War and Peace", "9781853260629", 14.99,"https://m.media-amazon.com/images/I/51s4UBf-y8L._AC_UF1000,1000_QL80_.jpg");
		Boek book7 = new Boek("The Great Gatsby", "9780141182636", 8.99,"https://kbimages1-a.akamaihd.net/c5742da9-e63f-4ed5-acb6-074fab5e3f41/1200/1200/False/the-great-gatsby-11.jpg");
		Boek book8 = new Boek("Mrs. Dalloway", "9781853261916", 10.99,"https://kbimages1-a.akamaihd.net/dc043919-597a-4d28-bfe7-f2638a464ce1/1200/1200/False/mrs-dalloway-141.jpg");
		Boek book9 = new Boek("A Farewell to Arms", "9780684801469", 9.99,"https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9780684801469/a-farewell-to-arms-9780684801469_hr.jpg");
		Boek book10 = new Boek("Adventures of Huckleberry Finn", "9780141199009", 6.99,"https://images.ucpress.edu/covers/isbn13/9780520343641.jpg");
		Boek book11 = new Boek("Wuthering Heights", "9780141439556", 7.99,"https://res.cloudinary.com/bloomsbury-atlas/image/upload/w_568,c_scale/jackets/9781847493217.jpg");
		Boek book12 = new Boek("Jane Eyre", "9780141441146", 7.99,"https://static.faber.co.uk/wp-content/uploads/2022/09/Jane-Eyre-445x690.jpg");
		Boek book13 = new Boek("The Shining", "9780385121675", 11.99,"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1353277730i/11588.jpg");
		Boek book14 = new Boek("It", "9780670813025", 10.99,"https://m.media-amazon.com/images/I/712+f2W4uoL.jpg");
		Boek book15 = new Boek("The Stand", "9780307743688", 9.99,"https://m.media-amazon.com/images/I/A103E3hh-wL._AC_UF1000,1000_QL80_.jpg");
		Boek book16 = new Boek("Harry Potter and the Philosopher's Stone", "9780747532743", 8.99,"https://res.cloudinary.com/bloomsbury-atlas/image/upload/w_568,c_scale/jackets/9781408855652.jpg");
		Boek book17 = new Boek("Harry Potter and the Chamber of Secrets", "9780747538486", 8.99,"https://m.media-amazon.com/images/I/81S0LnPGGUL.jpg");
		Boek book18 = new Boek("Harry Potter and the Prisoner of Azkaban", "9780747542155", 8.99,"https://res.cloudinary.com/bloomsbury-atlas/image/upload/w_568,c_scale/jackets/9781408855676.jpg");
		Boek book19 = new Boek("Murder on the Orient Express", "9780062073501", 7.99,"https://m.media-amazon.com/images/I/81YVXgvXoAL.jpg");
		Boek book20 = new Boek("And Then There Were None", "9780062073488", 6.99,"https://m.media-amazon.com/images/I/51MlxNgCsyL._AC_SY1000_.jpg");
		Boek book21 = new Boek("The Da Vinci Code", "9780307474278", 12.99,"https://m.media-amazon.com/images/I/91Q5dCjc2KL._AC_UF1000,1000_QL80_.jpg");
		Boek book22 = new Boek("Angels & Demons", "9780743486224", 11.99,"https://m.media-amazon.com/images/I/81adkKbGZgL.jpg");
		Boek book23 = new Boek("Gone Girl", "9780307588371", 10.99,"https://www.pluggedin.com/wp-content/uploads/2020/01/gone-girl-cover.jpg");
		Boek book24 = new Boek("Sharp Objects", "9780307341556", 9.99,"https://kbimages1-a.akamaihd.net/6c7ede20-3831-443d-9398-cf6ef04e6622/1200/1200/False/sharp-objects.jpg");
		Boek book25 = new Boek("Fahrenheit 451", "9781451673319", 8.99,"https://m.media-amazon.com/images/I/61z7RDG3OIL._AC_UF1000,1000_QL80_.jpg");
		Boek book26 = new Boek("The Illustrated Man", "9781451678185", 7.99,"https://upload.wikimedia.org/wikipedia/en/b/b1/Illustrated_man.jpg");
		Boek book27 = new Boek("American Gods", "9780062572110", 14.99,"https://m.media-amazon.com/images/I/51Pb-OAREFL._AC_UF1000,1000_QL80_.jpg");
		Boek book28 = new Boek("Nineteen Eighty-Four", "9780451524935", 6.99,"https://m.media-amazon.com/images/I/519zR2oIlmL._SX305_BO1,204,203,200_.jpg");
		Boek book29 = new Boek("Brave New World", "9780060850524", 9.99,"https://upload.wikimedia.org/wikipedia/en/6/62/BraveNewWorld_FirstEdition.jpg");
		Boek book2Authors= new Boek("The Talisman","9781501192272",18.99,"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1170530286i/59219.jpg");

		// Add the books to the authors' book sets
		author1.addBoek(book1);
		author1.addBoek(book2);
		author2.addBoek(book3);
		author2.addBoek(book4);
		author3.addBoek(book5);
		author4.addBoek(book6);
		author5.addBoek(book7);
		author6.addBoek(book8);
		author7.addBoek(book9);
		author8.addBoek(book10);
		author9.addBoek(book11);
		author10.addBoek(book12);
		author11.addBoek(book13);
		author11.addBoek(book14);
		author11.addBoek(book15);
		author12.addBoek(book16);
		author12.addBoek(book17);
		author12.addBoek(book18);
		author13.addBoek(book19);
		author13.addBoek(book20);
		author14.addBoek(book21);
		author14.addBoek(book22);
		author15.addBoek(book23);
		author15.addBoek(book24);
		author16.addBoek(book25);
		author16.addBoek(book26);
		author17.addBoek(book27);
		author18.addBoek(book28);
		author19.addBoek(book29);
		
		author20.addBoek(book2Authors);
		author11.addBoek(book2Authors);
		
		
		
		//Create locations
		Locatie locatie1 = new Locatie(50, 150, "Amsterdam");
		Locatie locatie2 = new Locatie(75, 225, "Rotterdam");
		Locatie locatie3 = new Locatie(100, 250, "Utrecht");
		Locatie locatie4 = new Locatie(150, 300, "Den Haag");
		Locatie locatie5 = new Locatie(200, 250, "Eindhoven");
		Locatie locatie6 = new Locatie(50, 200, "Groningen");
		Locatie locatie7 = new Locatie(80, 220, "Delft");
		Locatie locatie8 = new Locatie(110, 280, "Arnhem");
		Locatie locatie9 = new Locatie(130, 290, "Nijmegen");
		Locatie locatie10 = new Locatie(90, 240, "Amersfoort");
		Locatie locatie11 = new Locatie(70, 170, "Haarlem");
		Locatie locatie12 = new Locatie(120, 270, "Apeldoorn");
		Locatie locatie13 = new Locatie(60, 170, "Leiden");
		Locatie locatie14 = new Locatie(70, 220, "Gouda");
		Locatie locatie15 = new Locatie(140, 250, "Zwolle");
		Locatie locatie16 = new Locatie(200, 275, "Tilburg");
		Locatie locatie17 = new Locatie(80, 190, "Alkmaar");
		Locatie locatie18 = new Locatie(90, 250, "Zeist");
		Locatie locatie19 = new Locatie(110, 270, "Deventer");
		Locatie locatie20 = new Locatie(60, 200, "Assen");
		Locatie locatie21 = new Locatie(75, 175, "Zandvoort");
		Locatie locatie22 = new Locatie(160, 250, "Enschede");
		Locatie locatie23 = new Locatie(220, 290, "Breda");
		Locatie locatie24 = new Locatie(100, 200, "Lelystad");
		Locatie locatie25 = new Locatie(200, 300, "Maastricht");
		Locatie locatie26 = new Locatie(70, 240, "Zeewolde");
		Locatie locatie27 = new Locatie(80, 230, "Naaldwijk");
		Locatie locatie28 = new Locatie(60, 250, "Emmen");
		Locatie locatie29 = new Locatie(140, 290, "Zutphen");
		Locatie locatie30 = new Locatie(70, 160, "IJmuiden");
		Locatie locatie31 = new Locatie(150, 80, "Londen");
		Locatie locatie32 = new Locatie(200, 120, "Berlijn");
		Locatie locatie33 = new Locatie(110, 50, "Madrid");
		Locatie locatie34 = new Locatie(280, 220, "Parijs");
		Locatie locatie35 = new Locatie(70, 130, "Rome");

		// Add the books to a Location
		locatie1.setBoek(book1);
		locatie2.setBoek(book2);
		locatie3.setBoek(book3);
		locatie4.setBoek(book4);
		locatie5.setBoek(book5);
		locatie6.setBoek(book6);
		locatie7.setBoek(book7);
		locatie8.setBoek(book8);
		locatie9.setBoek(book9);
		locatie10.setBoek(book10);
		locatie11.setBoek(book11);
		locatie12.setBoek(book12);
		locatie13.setBoek(book13);
		locatie14.setBoek(book14);
		locatie15.setBoek(book15);
		locatie16.setBoek(book16);
		locatie17.setBoek(book17);
		locatie18.setBoek(book18);
		locatie19.setBoek(book19);
		locatie20.setBoek(book20);
		locatie21.setBoek(book21);
		locatie22.setBoek(book22);
		locatie23.setBoek(book23);
		locatie24.setBoek(book24);
		locatie25.setBoek(book25);
		locatie26.setBoek(book26);
		locatie27.setBoek(book27);
		locatie28.setBoek(book28);
		locatie29.setBoek(book29);
		locatie30.setBoek(book2Authors);
		locatie31.setBoek(book2Authors);
		locatie32.setBoek(book1);
		locatie33.setBoek(book1);
		locatie34.setBoek(book2);
		locatie35.setBoek(book3);
		
		
		//create users
		User user1 = new User("user1", "password1");
		User user2 = new User("user2", "password2");
		User user3 = new User("user3", "password3");
		User user4 = new User("user4", "password4");
		User user5 = new User("user5", "password5");
		//create admin
		Admin admin = new Admin("admin","admin");
		
		//create favorieten
		// create favorites for user1
		Favoriet fav1 = new Favoriet(book1, user1);
		Favoriet fav2 = new Favoriet(book2, user1);
		Favoriet fav3 = new Favoriet(book3, user1);
		Favoriet fav4 = new Favoriet(book4, user1);
		Favoriet fav5 = new Favoriet(book5, user1);

		// create favorites for user2
		Favoriet fav6 = new Favoriet(book6, user2);
		Favoriet fav7 = new Favoriet(book7, user2);
		Favoriet fav8 = new Favoriet(book8, user2);
		Favoriet fav9 = new Favoriet(book9, user2);
		Favoriet fav10 = new Favoriet(book10, user2);

		// create favorites for user3
		Favoriet fav11 = new Favoriet(book11, user3);
		Favoriet fav12 = new Favoriet(book12, user3);
		Favoriet fav13 = new Favoriet(book13, user3);
		Favoriet fav14 = new Favoriet(book14, user3);
		Favoriet fav15 = new Favoriet(book15, user3);

		// create favorites for user4
		Favoriet fav16 = new Favoriet(book16, user4);
		Favoriet fav17 = new Favoriet(book17, user4);
		Favoriet fav18 = new Favoriet(book18, user4);
		Favoriet fav19 = new Favoriet(book19, user4);
		Favoriet fav20 = new Favoriet(book20, user4);

		// create favorites for user5
		Favoriet fav21 = new Favoriet(book21, user5);
		Favoriet fav22 = new Favoriet(book4, user5);
		Favoriet fav23 = new Favoriet(book8, user5);
		Favoriet fav24 = new Favoriet(book24, user5);
		Favoriet fav25 = new Favoriet(book12, user5);
		
		//create favorites for admin
		Favoriet fav26 = new Favoriet(book7, admin);
		Favoriet fav27 = new Favoriet(book11, admin);
		Favoriet fav28 = new Favoriet(book12, admin);
		Favoriet fav29 = new Favoriet(book4, admin);
		Favoriet fav30 = new Favoriet(book3, admin);
		
		//persist books
		br.save(book1);
		br.save(book2);
		br.save(book3);
		br.save(book4);
		br.save(book5);
		br.save(book6);
		br.save(book7);
		br.save(book8);
		br.save(book9);
		br.save(book10);
		br.save(book11);
		br.save(book12);
		br.save(book13);
		br.save(book14);
		br.save(book15);
		br.save(book16);
		br.save(book17);
		br.save(book18);
		br.save(book19);
		br.save(book20);
		br.save(book21);
		br.save(book22);
		br.save(book23);
		br.save(book24);
		br.save(book25);
		br.save(book26);
		br.save(book27);
		br.save(book28);
		br.save(book29);
		br.save(book2Authors);
		
		// persist authors
		ar.save(author1);
		ar.save(author2);
		ar.save(author3);
		ar.save(author4);
		ar.save(author5);
		ar.save(author6);
		ar.save(author7);
		ar.save(author8);
		ar.save(author9);
		ar.save(author10);
		ar.save(author11);
		ar.save(author12);
		ar.save(author13);
		ar.save(author14);
		ar.save(author15);
		ar.save(author16);
		ar.save(author17);
		ar.save(author18);
		ar.save(author19);
		ar.save(author20);
		
		//persist locations
		lr.save(locatie1);
		lr.save(locatie2);
		lr.save(locatie3);
		lr.save(locatie4);
		lr.save(locatie5);
		lr.save(locatie6);
		lr.save(locatie7);
		lr.save(locatie8);
		lr.save(locatie9);
		lr.save(locatie10);
		lr.save(locatie11);
		lr.save(locatie12);
		lr.save(locatie13);
		lr.save(locatie14);
		lr.save(locatie15);
		lr.save(locatie16);
		lr.save(locatie17);
		lr.save(locatie18);
		lr.save(locatie19);
		lr.save(locatie20);
		lr.save(locatie21);
		lr.save(locatie22);
		lr.save(locatie23);
		lr.save(locatie24);
		lr.save(locatie25);
		lr.save(locatie26);
		lr.save(locatie27);
		lr.save(locatie28);
		lr.save(locatie29);
		lr.save(locatie30);
		lr.save(locatie31);
		lr.save(locatie32);
		lr.save(locatie33);
		lr.save(locatie34);
		lr.save(locatie35);
		
		//persist users
		gr.save(user1);
		gr.save(user2);
		gr.save(user3);
		gr.save(user4);
		gr.save(user5);
		gr.save(admin);
		
		//persist favorites
		fr.save(fav1);
		fr.save(fav2);
		fr.save(fav3);
		fr.save(fav4);
		fr.save(fav5);
		fr.save(fav6);
		fr.save(fav7);
		fr.save(fav8);
		fr.save(fav9);
		fr.save(fav10);
		fr.save(fav11);
		fr.save(fav12);
		fr.save(fav13);
		fr.save(fav14);
		fr.save(fav15);
		fr.save(fav16);
		fr.save(fav17);
		fr.save(fav18);
		fr.save(fav19);
		fr.save(fav20);
		fr.save(fav21);
		fr.save(fav22);
		fr.save(fav23);
		fr.save(fav24);
		fr.save(fav25);
		fr.save(fav26);
		fr.save(fav27);
		fr.save(fav28);
		fr.save(fav29);
		fr.save(fav30);
		
		
		


		
		
		
		System.out.println("populating database");
		
	}
	

}
