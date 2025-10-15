package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	
	public Jeu() {
		Carte[] tableauCartes = new JeuDeCartes().donnerCartes();
		List<Carte> listeCartes = new ArrayList<>(Arrays.asList(tableauCartes));
		listeCartes = GestionCartes.melanger(listeCartes);
		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
}
