package cartes;

public class JeuDeCartes {
	private Configuration[] config = { new Configuration(new Borne(25), 10), new Configuration(new Borne(50), 10),
			new Configuration(new Borne(75), 10), new Configuration(new Borne(100), 12),
			new Configuration(new Borne(200), 4), new Configuration(new Parade(Type.FEU), 14),
			new Configuration(new FinLimite(), 6), new Configuration(new Parade(Type.ESSENCE), 6),
			new Configuration(new Parade(Type.CREVAISON), 6), new Configuration(new Parade(Type.ACCIDENT), 6),
			new Configuration(new Attaque(Type.FEU), 5), new Configuration(new DebutLimite(), 4),
			new Configuration(new Attaque(Type.ESSENCE), 3), new Configuration(new Attaque(Type.CREVAISON), 3),
			new Configuration(new Attaque(Type.ACCIDENT), 3), new Configuration(new Botte(Type.FEU), 1),
			new Configuration(new Botte(Type.ESSENCE), 1), new Configuration(new Botte(Type.CREVAISON), 1),
			new Configuration(new Botte(Type.ACCIDENT), 1) };

	private static class Configuration extends Carte {
		private Carte carte;
		private int nbExemplaires;

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}

	public String affichageJeuCartes() {
		StringBuilder str = new StringBuilder();

		for (Configuration carte : config) {
			str.append(carte.getNbExemplaires());
			str.append(" ");
			str.append(carte.getCarte());
			str.append("\n");
		}

		return str.toString();
	}

	public Carte[] donnerCartes() {
		Carte[] paquet;
		int nbCartesTotal = 0;
		int paquetIndex = 0;

		for (Configuration configCarte : config) {
			nbCartesTotal += configCarte.getNbExemplaires();
		}

		paquet = new Carte[nbCartesTotal];

		for (Configuration configCarte : config) {
			for (int i = 0; i < configCarte.getNbExemplaires(); i++) {
				paquet[paquetIndex++] = configCarte.getCarte();
			}
		}
		return paquet;

//		List<Carte> paquet = new ArrayList<>();
//		for (Configuration configCarte : config) {
//			for (int i = 0; i < configCarte.getNbExemplaires(); i++) {
//				paquet.add(configCarte.getCarte());
//			}
//		}
//		return paquet.toArray(new Carte[0]);
	}

	public void checkCount(Carte[] cartes) {
		for (Configuration reference : config) {
			int nombreExemplaire = 0;
			for (Carte carte : cartes) {
				if (carte.equals(reference.getCarte())) {
					nombreExemplaire++;
				}
			}
			if (reference.getNbExemplaires() != nombreExemplaire) {
				throw new IllegalStateException("Pas le bon nombre de " + reference.getCarte());
			}
		}
	}
}
