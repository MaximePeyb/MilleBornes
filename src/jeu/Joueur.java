package jeu;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Limite;

public class Joueur {
	private String nom;
	private ZoneDeJeu zdj;
	private MainJoueur main;

	public Joueur(String nom, ZoneDeJeu zdj) {
		this.nom = nom;
		this.zdj = zdj;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			return nom.equals(((Joueur) obj).getNom());
		}
		return false;
	}

	private Object getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	public void donner(Carte carte) {
		main.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide())
			return null;
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}

	public int donnerKmParcourus() {
		return zdj.donnerKmParcourus();
	}

	public void deposer(Carte carte) {
		zdj.deposer(carte);
	}

	public boolean peutAvancer() {
		return zdj.peutAvancer();
	}

	public boolean estDepotAutorise(Carte carte) {
		return zdj.estDepotAutorise(carte);
	}
}
