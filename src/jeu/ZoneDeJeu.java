package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<>();
	private List<Bataille> batailles = new ArrayList<>();
	private List<Borne> bornes = new ArrayList<>();

	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || limites.getLast() instanceof FinLimite) {
			return 200;
		} else if (limites.getLast() instanceof DebutLimite) {
			return 50;
		} else {
			throw new IllegalStateException(
					"Carte de mauvais type dans limites : " + limites.getLast().getClass().getSimpleName());
		}
	}

	public int donnerKmParcourus() {
		int nbKmParcourus = 0;
		for (Borne b : bornes) {
			nbKmParcourus += b.getKm();
		}
		return nbKmParcourus;
	}

	public void deposer(Carte carte) {
		switch (carte) {
		case Limite limite -> limites.add(limite);

		case Bataille bataille -> batailles.add(bataille);

		case Borne borne -> bornes.add(borne);

		default -> throw new IllegalArgumentException(
				"Ajout d'une carte d'un mauvais type : " + carte.getClass().getSimpleName());
		}
	}

	public boolean peutAvancer() {
		return (!batailles.isEmpty() && batailles.getLast().equals(new Parade(Type.FEU)));
	}

	public boolean estDepotFeuVertAutorise() {
		return (batailles.isEmpty() || batailles.getLast().equals(new Attaque(Type.FEU))
				|| (batailles.getLast() instanceof Parade parade && parade.getType()!=Type.FEU));
	}

	public boolean estDepotBorneAutorise(Borne borne) {
		return (peutAvancer() && borne.getKm() <= donnerLimitationVitesse()
				&& borne.getKm() + donnerKmParcourus() <= 1000);
	}

	public boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return (limites.isEmpty() || limites.getLast() instanceof FinLimite);
		}
		return (!limites.isEmpty() && limites.getLast() instanceof DebutLimite);
	}

	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		if (bataille.getType()==Type.FEU){
			return estDepotFeuVertAutorise();
		}
		return (!batailles.isEmpty() && bataille.getType() == batailles.getLast().getType());
	}

	public boolean estDepotAutorise(Carte carte) {
		return switch (carte) {
		case Limite limite -> estDepotLimiteAutorise(limite);

		case Bataille bataille -> estDepotBatailleAutorise(bataille);

		case Borne borne -> estDepotBorneAutorise(borne);

		default -> throw new IllegalArgumentException(
				"Ajout d'une carte d'un mauvais type : " + carte.getClass().getSimpleName());
		};
	}
}
