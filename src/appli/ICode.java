package appli;


import codage.TypeChiffrement;

public interface ICode {
	/**
	 * Chiffre un message
	 *
	 * @see TypeChiffrement
	 */
	String chiffrer();

	/**
	 * Dechiffre une message
	 *
	 * @see TypeChiffrement
	 */
	String dechiffrer();

	/**
	 * Decrypte un message
	 *
	 * @return
	 */
	String decrypter();
}
