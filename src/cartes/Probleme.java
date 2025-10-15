package cartes;

public abstract class Probleme extends Carte {
	private final Type type;
	
	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass())
			return ((Probleme) obj).getType() == this.getType();
		return false;
	} 
}
