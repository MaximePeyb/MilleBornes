package tests_fonctionnels;

import utils.GestionCartes;
import cartes.JeuDeCartes;
import cartes.Carte;

import java.util.*;

public class TestUtils {

    public static <T> boolean verifierOccurrences(List<T> original, List<T> melangee) {
        Set<T> elements = new HashSet<>(original);
        for (T e : elements) {
            int freqOriginal = Collections.frequency(original, e);
            int freqMelangee = Collections.frequency(melangee, e);
            if (freqOriginal != freqMelangee) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        JeuDeCartes jeu = new JeuDeCartes();

        List<Carte> listeCarteNonMelangee = new ArrayList<>(Arrays.asList(jeu.donnerCartes()));

        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);

        
        System.out.println("Liste originale :");
        System.out.println(listeCartes);

        
        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println("\nListe mélangée :");
        System.out.println(listeCartes);

        
        boolean melangeCorrect = GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes);
        System.out.println("\nListe mélangée sans erreur ? " + melangeCorrect);

        
        boolean occurrencesCorrectes = verifierOccurrences(listeCarteNonMelangee, listeCartes);
        System.out.println("Occurrences conservées ? " + occurrencesCorrectes);

        
        List<Carte> listeRassemblee = GestionCartes.rassembler(listeCartes);
        System.out.println("\nListe rassemblée :");
        System.out.println(listeRassemblee);

        
        boolean rassemblementCorrect = GestionCartes.verifierRassemblement(listeRassemblee);
        System.out.println("Liste rassemblée sans erreur ? " + rassemblementCorrect);

        
        List<List<Integer>> petitesListes = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(1,1,2,1,3),
            Arrays.asList(1,4,3,2),
            Arrays.asList(1,1,2,3,1)
        );

        for (List<Integer> l : petitesListes) {
            List<Integer> copie = new ArrayList<>(l);
            List<Integer> melange = GestionCartes.melanger(copie);
            boolean melangeOk = GestionCartes.verifierMelange(l, melange);
            List<Integer> rassemble = GestionCartes.rassembler(melange);
            boolean rassembleOk = GestionCartes.verifierRassemblement(rassemble);

            System.out.println("\nListe initiale : " + l);
            System.out.println("Mélangée : " + melange + " → vérification : " + melangeOk);
            System.out.println("Rassemblée : " + rassemble + " → vérification : " + rassembleOk);
        }
    }
}