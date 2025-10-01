package testsFonctionnels;

import java.util.Iterator;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {
	public static void main(String[] args) {
		Carte[] cartes = creerJeuComplet();
		
        Sabot sabot = new Sabot(cartes);
        
//        while (!sabot.estVide()) {
//            Carte c = sabot.piocher();
//            System.out.println("je pioche " + c);
//        }
        
        Iterator<Carte> it = sabot.iterator();
        while (it.hasNext()) {
            Carte c = it.next();
            System.out.println("je pioche " + c);
            it.remove();
        }

        if (sabot.estVide()) {
            System.out.println("Le sabot est vide !");
        }
        
        System.out.println("\n---------------------------------------------------------------------------------------------\n");
        System.out.println("Test next ET piocher \n");
        
        sabot = new Sabot(creerJeuComplet()); 
        it = sabot.iterator();
        try {
            while (it.hasNext()) {
                Carte c = it.next();
                System.out.println("je pioche " + c);
                // Mauvais : on modifie le sabot directement pendant l’itération
                sabot.piocher(); 
            }
        } catch (Exception e) {
            System.out.println("Exception attendue (ConcurrentModification) ;\nreçue : " + e);
        }
        
        System.out.println("\n---------------------------------------------------------------------------------------------\n");
        System.out.println("Test ajout d'une carte \n");
        
        sabot = new Sabot(creerJeuComplet()); 
        sabot.piocher();

        it = sabot.iterator();
        try {
            while (it.hasNext()) {
                Carte c = it.next();
                System.out.println("je pioche " + c);
                sabot.ajouterCarte(new Botte(Type.ACCIDENT));
            }
        } catch (Exception e) {
            System.out.println("Exception attendue (ConcurrentModification) ;\nreçue : " + e);
        }
        
	}
	
	private static Carte[] creerJeuComplet() {
		Carte[] cartes = new Carte[106];
		int index = 0;

        // Distances
        for (int i = 0; i < 10; i++) cartes[index++] = new Borne(25);
        for (int i = 0; i < 10; i++) cartes[index++] = new Borne(50);
        for (int i = 0; i < 10; i++) cartes[index++] = new Borne(75);
        for (int i = 0; i < 12; i++) cartes[index++] = new Borne(100);
        for (int i = 0; i < 4; i++) cartes[index++] = new Borne(200);

        // Attaques
        for (int i = 0; i < 5; i++) cartes[index++] = new Attaque(Type.FEU);
        for (int i = 0; i < 4; i++) cartes[index++] = new Attaque(Type.ACCIDENT);
        for (int i = 0; i < 3; i++) cartes[index++] = new Attaque(Type.ESSENCE);
        for (int i = 0; i < 3; i++) cartes[index++] = new Attaque(Type.CREVAISON);
        for (int i = 0; i < 3; i++) cartes[index++] = new DebutLimite();

        // Parades
        for (int i = 0; i < 14; i++) cartes[index++] = new Parade(Type.FEU);
        for (int i = 0; i < 6; i++) cartes[index++] = new FinLimite();

        // Parades contre attaques
        for (int i = 0; i < 6; i++) cartes[index++] = new Parade(Type.ACCIDENT);
        for (int i = 0; i < 6; i++) cartes[index++] = new Parade(Type.ESSENCE);
        for (int i = 0; i < 6; i++) cartes[index++] = new Parade(Type.CREVAISON);

        // Bottes
        cartes[index++] = new Botte(Type.ACCIDENT);
        cartes[index++] = new Botte(Type.ESSENCE);
        cartes[index++] = new Botte(Type.CREVAISON);
        cartes[index] = new Botte(Type.FEU);
        
        return cartes;
	}
}
