package tests_fonctionnels;

import cartes.*;

public class TestMethodEquals {

	public static void main(String[] args) {
		System.out.println("Premier test : deux bornes de 25km (expect true)");
		System.out.println(new Borne(25).equals(new Borne(25)));

		System.out.println("Second test : deux cartes feu rouge (expect true)");
		System.out.println(new Attaque(Type.FEU).equals(new Attaque(Type.FEU)));

		System.out.println("Troisième test : un feu rouge, un feu vert (expect false) ");
		System.out.println(new Attaque(Type.FEU).equals(new Parade(Type.FEU)));

	}
}
