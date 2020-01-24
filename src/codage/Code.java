package codage;


import appli.ICode;

public class Code implements ICode {

	private TypeChiffrement typeCodage;
	private String clef;
	private String fichierIn;
	private TypeDecrytage typeDecrytage;

	/**
	 * Constructeur de la classe Code
	 *
	 * @param typeCodage
	 *            le type de codage
	 * @param clef
	 *            la clef pour le codage
	 * @param fichierIn
	 *            le fichier d'entree
	 */
	public Code(String typeCodage, String clef, String fichierIn) {
		this.typeCodage = TypeChiffrement.getType(typeCodage);
		this.clef = clef;
		this.fichierIn = fichierIn;
	}

	/**
	 * Constructeur de la classe Code pour le decryptage
	 *
	 * @param typeCodage
	 *            le type de codage
	 * @param indice
	 *            l'indice qu'on a
	 * @param fichierIn
	 *            le fichier d'entree
	 * @param methode
	 *            la méthode de decryptage
	 */
	public Code(String typeCodage, String indice, String fichierIn, String methode) {
		this.typeCodage = TypeChiffrement.getType(typeCodage);
		this.clef = indice;
		this.fichierIn = fichierIn;
		this.typeDecrytage = TypeDecrytage.getType(methode);
	}
	
	@Override
	public String chiffrer() {
		switch (this.typeCodage) {
			case Cesar:
				return Cesar.chiffrer(this.clef, this.fichierIn);
			case Permutation:
				return Permutation.chiffrer(this.clef, this.fichierIn);
			case Vigenere:
				return Vigenere.chiffrer(this.clef, this.fichierIn);

			default:
				System.err.println("Le type de chiffrement n'est pas implemente");
				return "";
		}
	}

	@Override
	public String dechiffrer() {
		switch (this.typeCodage) {
			case Cesar:
				return Cesar.dechiffrer(this.clef, this.fichierIn);
			case Permutation:
				return Permutation.dechiffrer(this.clef, this.fichierIn);
			case Vigenere:
				return Vigenere.dechiffrer(this.clef, this.fichierIn);

			default:
				System.err.println("Le type de chiffrement n'est pas implemente");
				return "";
		}

	}
	
	@Override
	public String decrypter() {
		switch (this.typeCodage) {
			case Cesar:
				return Cesar.decrypter(this.clef, this.fichierIn, this.typeDecrytage);
			case Permutation:
				return Permutation.decrypter(this.clef, this.fichierIn, this.typeDecrytage);
			case Vigenere:
				return Vigenere.decrypter(this.clef, this.fichierIn, this.typeDecrytage);

			default:
				System.err.println("Le type de chiffrement n'est pas implement�");
				return "";
		}
	}

}
