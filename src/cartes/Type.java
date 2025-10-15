package cartes;

public enum Type {
	FEU("Feu rouge.", "Feu vert.", "Prioritaire !"),
	ESSENCE("Panne d'essence", "Faites le plein", "Citerne d'essence !"),
	CREVAISON("Crevaison", "Roue de secours", "Increvable !"), ACCIDENT("Accident", "Réparations", "As du volant !");

	private final String msgAttaque;
	private final String msgParade;
	private final String msgBotte;

	private Type(String attaque, String parade, String botte) {
		msgAttaque = attaque;
		msgParade = parade;
		msgBotte = botte;
	}

	public String attaque() {
		return msgAttaque;
	}

	public String parade() {
		return msgParade;
	}

	public String botte() {
		return msgBotte;
	}
}
