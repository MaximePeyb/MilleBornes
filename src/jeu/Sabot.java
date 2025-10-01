package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.*;

public class Sabot implements Iterable<Carte> {

    private Carte[] cartes;       
    private int nbCartes;         
    private int modCount = 0;     

    public Sabot(Carte[] cartes) {
        this.cartes = new Carte[cartes.length];
        for (int i = 0; i <cartes.length; i++) {
            this.cartes[i] = cartes[i];
        }
        this.nbCartes = cartes.length;
    }

    
    public boolean estVide() {
        return nbCartes == 0;
    }

    
    public void ajouterCarte(Carte c) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Sabot plein : impossible d'ajouter une carte.");
        }
        cartes[nbCartes++] = c;
        modCount++;
    }

    
    @Override
    public Iterator<Carte> iterator() {
        return new Iterator<Carte>() {
            private int index = 0;                     
            private int expectedModCount = modCount;   
            private boolean canRemove = false;         

            @Override
            public boolean hasNext() {
                return index < nbCartes;
            }

            @Override
            public Carte next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                canRemove = true;
                return cartes[index++];
            }

            @Override
            public void remove() {
                if (!canRemove) 
                    throw new IllegalStateException("remove() doit suivre un next().");
                
                if (expectedModCount != modCount) 
                    throw new ConcurrentModificationException();
                
                for (int i = index - 1; i < nbCartes - 1; i++) {
                    cartes[i] = cartes[i + 1];
                }
                cartes[--nbCartes] = null; 
                index--;                   
                canRemove = false;
                expectedModCount = ++modCount;
            }
        };
    }

    public Carte piocher() {
        if (estVide()) 
            throw new IllegalStateException("Impossible de piocher : sabot vide.");
        
        Iterator<Carte> it = this.iterator();
        Carte c = it.next();
        it.remove(); 
        return c;
    }
}
