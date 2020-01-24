package codage;


public enum TypeChiffrement {
	Cesar("c"), Vigenere("v"), Permutation("p");

	private String lettreChiffrage;

	private TypeChiffrement(String lettreChiffrage) {
		this.lettreChiffrage = lettreChiffrage;
	}

	public String getLettreChiffrage() {
		return this.lettreChiffrage;
	}

	public static TypeChiffrement getType(String string) {
		for (TypeChiffrement t : TypeChiffrement.values()) {
			if (t.getLettreChiffrage().equals(string)) {
				return t;
			}
		}
		return null;
	}

}
