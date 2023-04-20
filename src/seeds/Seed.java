package seeds;

import domein.Admin;
import domein.Auteur;
import domein.Boek;
import domein.Favoriet;
import domein.Locatie;
import domein.User;
import persistentie.AuteurBeheerder;
import persistentie.BoekBeheerder;
import persistentie.FavorietBeheerder;
import persistentie.GebruikerBeheerder;
import persistentie.LocatieBeheerder;

public class Seed {
	private final AuteurBeheerder ab;
	private final BoekBeheerder bb;
	private final FavorietBeheerder fb;
	private final GebruikerBeheerder gb;
	private final LocatieBeheerder lb;
public Seed() {
	ab= new AuteurBeheerder();
	bb= new BoekBeheerder();
	fb= new FavorietBeheerder();
	gb= new GebruikerBeheerder();
	lb= new LocatieBeheerder();
	populeerData();
}

private void populeerData() {
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
	Boek book1 = new Boek("One Hundred Years of Solitude", "9780060883287", 12.99);
	Boek book2 = new Boek("Love in the Time of Cholera", "9780307389732", 9.99);
	Boek book3 = new Boek("The Lord of the Rings", "9780261102361", 19.99);
	Boek book4 = new Boek("The Hobbit", "9780261103306", 9.99);
	Boek book5 = new Boek("Pride and Prejudice", "9780141439518", 7.99);
	Boek book6 = new Boek("War and Peace", "9781853260629", 14.99);
	Boek book7 = new Boek("The Great Gatsby", "9780141182636", 8.99);
	Boek book8 = new Boek("Mrs. Dalloway", "9781853261916", 10.99);
	Boek book9 = new Boek("A Farewell to Arms", "9780684801469", 9.99);
	Boek book10 = new Boek("Adventures of Huckleberry Finn", "9780141199009", 6.99);
	Boek book11 = new Boek("Wuthering Heights", "9780141439556", 7.99);
	Boek book12 = new Boek("Jane Eyre", "9780141441146", 7.99);
	Boek book13 = new Boek("The Shining", "9780385121675", 11.99);
	Boek book14 = new Boek("It", "9780670813025", 10.99);
	Boek book15 = new Boek("The Stand", "9780307743688", 9.99);
	Boek book16 = new Boek("Harry Potter and the Philosopher's Stone", "9780747532743", 8.99);
	Boek book17 = new Boek("Harry Potter and the Chamber of Secrets", "9780747538486", 8.99);
	Boek book18 = new Boek("Harry Potter and the Prisoner of Azkaban", "9780747542155", 8.99);
	Boek book19 = new Boek("Murder on the Orient Express", "9780062073501", 7.99);
	Boek book20 = new Boek("And Then There Were None", "9780062073488", 6.99);
	Boek book21 = new Boek("The Da Vinci Code", "9780307474278", 12.99);
	Boek book22 = new Boek("Angels & Demons", "9780743486224", 11.99);
	Boek book23 = new Boek("Gone Girl", "9780307588371", 10.99);
	Boek book24 = new Boek("Sharp Objects", "9780307341556", 9.99);
	Boek book25 = new Boek("Fahrenheit 451", "9781451673319", 8.99);
	Boek book26 = new Boek("The Illustrated Man", "9781451678185", 7.99);
	Boek book27 = new Boek("American Gods", "9780062572110", 14.99);
	Boek book28 = new Boek("Nineteen Eighty-Four", "9780451524935", 6.99);
	Boek book29 = new Boek("Brave New World", "9780060850524", 9.99);
	Boek book2Authors= new Boek("The Talisman","9781501192272",18.99);

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
	User user1 = new User("user1@example.com", "password1");
	User user2 = new User("user2@example.com", "password2");
	User user3 = new User("user3@example.com", "password3");
	User user4 = new User("user4@example.com", "password4");
	User user5 = new User("user5@example.com", "password5");
	//create admin
	Admin admin = new Admin("admin@example.com","admin");
	
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
	Favoriet fav22 = new Favoriet(book22, user5);
	Favoriet fav23 = new Favoriet(book23, user5);
	Favoriet fav24 = new Favoriet(book24, user5);
	Favoriet fav25 = new Favoriet(book25, user5);
	
	//create favorites for admin
	Favoriet fav26 = new Favoriet(book1, admin);
	Favoriet fav27 = new Favoriet(book2, admin);
	Favoriet fav28 = new Favoriet(book3, admin);
	Favoriet fav29 = new Favoriet(book4, admin);
	Favoriet fav30 = new Favoriet(book5, admin);
	
	
	

	//persist books
	bb.addBoek(book1);
	bb.addBoek(book2);
	bb.addBoek(book3);
	bb.addBoek(book4);
	bb.addBoek(book5);
	bb.addBoek(book6);
	bb.addBoek(book7);
	bb.addBoek(book8);
	bb.addBoek(book9);
	bb.addBoek(book10);
	bb.addBoek(book11);
	bb.addBoek(book12);
	bb.addBoek(book13);
	bb.addBoek(book14);
	bb.addBoek(book15);
	bb.addBoek(book16);
	bb.addBoek(book17);
	bb.addBoek(book18);
	bb.addBoek(book19);
	bb.addBoek(book20);
	bb.addBoek(book21);
	bb.addBoek(book22);
	bb.addBoek(book23);
	bb.addBoek(book24);
	bb.addBoek(book25);
	bb.addBoek(book26);
	bb.addBoek(book27);
	bb.addBoek(book28);
	bb.addBoek(book29);
	bb.addBoek(book2Authors);

	
	// persist authors
	ab.addAuteur(author1);
	ab.addAuteur(author2);
	ab.addAuteur(author3);
	ab.addAuteur(author4);
	ab.addAuteur(author5);
	ab.addAuteur(author6);
	ab.addAuteur(author7);
	ab.addAuteur(author8);
	ab.addAuteur(author9);
	ab.addAuteur(author10);
	ab.addAuteur(author11);
	ab.addAuteur(author12);
	ab.addAuteur(author13);
	ab.addAuteur(author14);
	ab.addAuteur(author15);
	ab.addAuteur(author16);
	ab.addAuteur(author17);
	ab.addAuteur(author18);
	ab.addAuteur(author19);
	ab.addAuteur(author20);
	
	//persist locations
	lb.addLocatie(locatie1);
	lb.addLocatie(locatie2);
	lb.addLocatie(locatie3);
	lb.addLocatie(locatie4);
	lb.addLocatie(locatie5);
	lb.addLocatie(locatie6);
	lb.addLocatie(locatie7);
	lb.addLocatie(locatie8);
	lb.addLocatie(locatie9);
	lb.addLocatie(locatie10);
	lb.addLocatie(locatie11);
	lb.addLocatie(locatie12);
	lb.addLocatie(locatie13);
	lb.addLocatie(locatie14);
	lb.addLocatie(locatie15);
	lb.addLocatie(locatie16);
	lb.addLocatie(locatie17);
	lb.addLocatie(locatie18);
	lb.addLocatie(locatie19);
	lb.addLocatie(locatie20);
	lb.addLocatie(locatie21);
	lb.addLocatie(locatie22);
	lb.addLocatie(locatie23);
	lb.addLocatie(locatie24);
	lb.addLocatie(locatie25);
	lb.addLocatie(locatie26);
	lb.addLocatie(locatie27);
	lb.addLocatie(locatie28);
	lb.addLocatie(locatie29);
	lb.addLocatie(locatie30);
	lb.addLocatie(locatie31);
	lb.addLocatie(locatie32);
	lb.addLocatie(locatie33);
	lb.addLocatie(locatie34);
	lb.addLocatie(locatie35);
	
	//persist users
	gb.addGebruiker(user1);
	gb.addGebruiker(user2);
	gb.addGebruiker(user3);
	gb.addGebruiker(user4);
	gb.addGebruiker(user5);
	gb.addGebruiker(admin);
	
	//persist favorites
	fb.addFavoriet(fav1);
	fb.addFavoriet(fav2);
	fb.addFavoriet(fav3);
	fb.addFavoriet(fav4);
	fb.addFavoriet(fav5);
	fb.addFavoriet(fav6);
	fb.addFavoriet(fav7);
	fb.addFavoriet(fav8);
	fb.addFavoriet(fav9);
	fb.addFavoriet(fav10);
	fb.addFavoriet(fav11);
	fb.addFavoriet(fav12);
	fb.addFavoriet(fav13);
	fb.addFavoriet(fav14);
	fb.addFavoriet(fav15);
	fb.addFavoriet(fav16);
	fb.addFavoriet(fav17);
	fb.addFavoriet(fav18);
	fb.addFavoriet(fav19);
	fb.addFavoriet(fav20);
	fb.addFavoriet(fav21);
	fb.addFavoriet(fav22);
	fb.addFavoriet(fav23);
	fb.addFavoriet(fav24);
	fb.addFavoriet(fav25);
	fb.addFavoriet(fav26);
	fb.addFavoriet(fav27);
	fb.addFavoriet(fav28);
	fb.addFavoriet(fav29);
	fb.addFavoriet(fav30);
	
	
	


	
	
	
	System.out.println("populating database");
	
}
}
