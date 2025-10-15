package utils;

import java.util.*;

public class GestionCartes {
	
	private static final Random random = new Random();
	
	
	public static <T> T extraire(List<T> liste) {
		if(liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne doit pas être vide !");
		}
		int index = random.nextInt(liste.size());
        return liste.remove(index);
	}
	
	public static <T> T extraireIterator(List<T> liste) {
		if (liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne doit pas être vide !");
        }

        int index = random.nextInt(liste.size());
        ListIterator<T> it = liste.listIterator();
        T element = null;
        for (int i = 0; i <= index; i++) {
            element = it.next();
        }
        it.remove(); // supprime l'élément courant
        return element;
	}

	public static <T> List<T> melanger(List<T> liste) {
		List<T> melangee = new ArrayList<>();
		while(!liste.isEmpty()) {
			melangee.add(extraire(liste));
		}
		return melangee;
	}
	
	public static <T> Boolean verifierMelange(List<T> l1,List<T> l2) {
		if (l1 == null || l2 == null) {return false;}
		
        if (l1.size() != l2.size()) {return false;}
        
        for (T elt : new HashSet<>(l1)) {
            int freq1 = Collections.frequency(l1, elt);
            int freq2 = Collections.frequency(l2, elt);
            if (freq1 != freq2) {
                return false; 
            }
        }
        return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {		
		List<T> listeRangee = new ArrayList<>();
		Set<T> dejaVus = new HashSet<>();
		
		for (T elt : new HashSet<>(liste)) {
			if(! dejaVus.contains(elt)) {
				for (int i=0;i<Collections.frequency(liste,elt);i++) {
					listeRangee.add(elt);
				}
				dejaVus.add(elt);
			}
		}
		return listeRangee;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
        if (liste == null || liste.size() <= 1) {
        	return true;
        }

        ListIterator<T> it1 = liste.listIterator();
        T precedent = it1.next();

        while (it1.hasNext()) {
            T courant = it1.next();
            if (!Objects.equals(precedent, courant)) {
                ListIterator<T> it2 = liste.listIterator(it1.nextIndex());
                while (it2.hasNext()) {
                    T suivant = it2.next();
                    if (Objects.equals(precedent, suivant)) {
                    	return false;
                    }
                }
            }
            precedent = courant;
        }
        return true;
    }
}
