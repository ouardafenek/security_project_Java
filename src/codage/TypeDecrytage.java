package codage;


public enum TypeDecrytage {
	BruteForce("b"), Statistique("s"), Mot("m");

	private String lettreType;

	private TypeDecrytage(String lettreType) {
		this.lettreType = lettreType;
	}

	public String getLettreDecryptage() {
		return this.lettreType;
	}
	
	public static TypeDecrytage getType(String string) {
		for (TypeDecrytage t : TypeDecrytage.values()) {
			if (t.getLettreDecryptage().equals(string)) {
				return t;
			}
		}
		return null;
	}
}
