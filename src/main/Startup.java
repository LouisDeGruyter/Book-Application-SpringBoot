package main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import domein.Auteur;
import domein.Boek;
import domein.Locatie;
import persistentie.AuteurBeheerder;
import persistentie.BoekBeheerder;

public class Startup {

	public static void main(String[] args) {
//        Set<Locatie> locs = new HashSet<>();
//        Boek boek= new Boek("boek", "sdfsfsfsq", 2.05, locs );
        AuteurBeheerder ab= new AuteurBeheerder();
        
        BoekBeheerder bb= new BoekBeheerder();
        List<Boek>boeken= bb.getMostPopularBoeken(5);
        for (Boek boek : boeken) {
			System.out.println(boek.toString());
		}
//        for (Boek boek : boeken) {
//			boek.getFavorieten().forEach(f-> System.out.println(f.toString()));
//		}

	}

}
