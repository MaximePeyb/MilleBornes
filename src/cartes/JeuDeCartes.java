package cartes;

import java.util.ArrayList;
import java.util.List;

public class JeuDeCartes {
	private Configuration[] config = new Configuration[19];

	private class Configuration extends Carte{
		private Carte carte;
		private int nbExemplaires;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte=carte;
			this.nbExemplaires=nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}
	
	public String affichageJeuCartes() {
		String str = "";
		
		config[0]=new Configuration(new Borne(25), 10);
		config[1]=new Configuration(new Borne(50), 10);
		config[2]=new Configuration(new Borne(75), 10);
		config[3]=new Configuration(new Borne(100), 12);
		config[4]=new Configuration(new Borne(200), 4);
		config[5]=new Configuration(new Parade(Type.FEU), 14);
		config[6]=new Configuration(new FinLimite(), 6);
		config[7]=new Configuration(new Parade(Type.ESSENCE), 6);
		config[8]=new Configuration(new Parade(Type.CREVAISON), 6);
		config[9]=new Configuration(new Parade(Type.ACCIDENT), 6);
		config[10]=new Configuration(new Attaque(Type.FEU), 5);
		config[11]=new Configuration(new DebutLimite(), 4);
		config[12]=new Configuration(new Attaque(Type.ESSENCE), 3);
		config[13]=new Configuration(new Attaque(Type.CREVAISON), 3);
		config[14]=new Configuration(new Attaque(Type.ACCIDENT), 3);
		config[15]=new Configuration(new Botte(Type.FEU), 1);
		config[16]=new Configuration(new Botte(Type.ESSENCE), 1);
		config[17]=new Configuration(new Botte(Type.CREVAISON), 1);
		config[18]=new Configuration(new Botte(Type.ACCIDENT), 1);
		
		for (Configuration carte:config) {
			System.out.println(carte.getNbExemplaires()+" "+ carte.getCarte().toString());
		}
		
		return str;
	}
	
	public Carte[] donnerCartes() {
//		Carte[] paquet;
//		int nbCartesTotal=0;
//		int paquetIndex=0;
//		
//		for (Configuration configCarte:config) {
//			nbCartesTotal+=configCarte.getNbExemplaires();
//		}
//		
//		paquet = new Carte[nbCartesTotal];
//		
//		for (Configuration configCarte:config) {
//			for(int i=0;i<configCarte.getNbExemplaires();i++) {
//				paquet[paquetIndex++] = configCarte.getCarte();
//			}
//		}
		
		List<Carte> paquet = new ArrayList<>();
	    for (Configuration configCarte : config) {
	        for (int i = 0; i < configCarte.getNbExemplaires(); i++) {
	            paquet.add(configCarte.getCarte());
	        }
	    }
	    return paquet.toArray(new Carte[0]);
	}
}
