package jeu;

import java.util.Iterator;

import cartes.*;

public class Sabot implements Iterable<Carte>{
	Carte[] pioche;
	int nbCartes;
	
	public Sabot(Carte[] sabot) {
		pioche = sabot;
		nbCartes = sabot.length;
	}
	
	public Boolean estVide() {
		return nbCartes==0;
	}
	
	public void ajouterCarte(Carte carte) {
		if(nbCartes == pioche.length) {
			throw new IllegalStateException();
		} else {
			pioche[nbCartes++]=carte;
		}		
	}
	
	
	
}
