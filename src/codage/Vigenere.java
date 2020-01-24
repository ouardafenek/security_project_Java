package codage;


import java.util.Arrays;

import utils.FileGetText;

public class Vigenere {
	
	/**
	 * <b>
	 * Chiffre un message dans un fichier avec une clef
	 * </b>
	 * <p>
	 * Les lettres du fichier d'entree sont traitees une a une et ajoutees
	 * dans une
	 * chaine de retour. La chaine de caracteres original est d'abord passe en
	 * minuscule. Seul des lettres sont traitees, les autres caracteres restent
	 * inchangees.
	 * <p>
	 *
	 * @param _cle
	 *            La clef a utiliser pour le chiffrement
	 * @param _nom_fichier
	 *            Le fichier contenant le message achiffrer
	 *
	 * @return Une chaine de caractere contenant le message chiffré
	 */
	
	public static String chiffrer(String _cle, String _nom_fichier) {
		String text = FileGetText.getText(_nom_fichier).toLowerCase();
		StringBuilder resultat = new StringBuilder();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int long_cle = _cle.length();
		// on remplit le tableau des decalages
		int decalages[] = new int[long_cle];
		for (int i = 0 ; i < _cle.length() ; i++) {
			decalages[i] = alphabet.indexOf(_cle.charAt(i));
		}
		int ind = 0; // Indice de la prochaine lettre de la clef
		for (char c : text.toCharArray()) {
			char charCode = c;

			// si le caractere lu est une lettre
			if (Character.isLetter(c)) {
				// on utilisera la fonction modulo pour trouver son codage
				charCode = (char) (charCode + decalages[ind % long_cle]);
				if (charCode > 'z') {
					charCode -= 26;
				}
				ind++;
			}
			resultat.append(charCode);
			
		}
		return resultat.toString();
	}
	
	/**
	 * Dechiffre un message avec Vigenère
	 *
	 * @param cle
	 *            la clé
	 * @param fichier
	 *            le fichier d'entrée
	 * @return une chaine de caractères représentant le fichier décodé
	 */
	public static String dechiffrer(String cle, String fichier) {
		cle = cle.toLowerCase(); // On ne traite que les cas minuscules
		int ind = 0; // L'indice de la lettre de la clef a utiliser
		
		// Lecture du fichier
		String text = FileGetText.getText(fichier).toLowerCase();
		StringBuilder retour = new StringBuilder();
		
		// Pour chaque caracteres de la chaine
		for (char c : text.toCharArray()) {
			char lettreDecode = c;
			// Si c'est une lettre
			if (Character.isLetter(c)) {
				lettreDecode -= (cle.charAt(ind % cle.length()) - 'a');
				++ind;
				if (lettreDecode < 'a') {
					lettreDecode = (char) (lettreDecode + 26);
				}
			}
			
			retour.append(lettreDecode);
		}
		
		return retour.toString();
	}
	
	/**
	 * Decrypte un texte avec Vigenere
	 *
	 * @param indice
	 *            l'indice
	 * @param fichierIn
	 *            le fichier d'entree
	 * @param typeDecrytage
	 *            la mthode de decryptage a utiliser
	 * @return le message decrypte
	 */
	public static String decrypter(String indice, String fichierIn, TypeDecrytage typeDecrytage) {
		switch (typeDecrytage) {
			case Statistique:
				return Vigenere.decrypterStats(indice, fichierIn);
			
			default:
				System.err.println("Methode de decryptage non implmentee");
				return "";
		}
	}
	
	/**
	 * Decrypte un message avec Vigenere et des stats
	 *
	 * @param indice
	 *            le nombre de caracteres
	 * @param fichierIn
	 *            le fichier d'entree
	 * @return le message dechiffe
	 */
	private static String decrypterStats(String indice, String fichierIn) {

		long startTime = System.currentTimeMillis();

		int longueurCle = 0;
		String clePotentiel = "";
		try {
			longueurCle = Integer.parseInt(indice);
		} catch (NumberFormatException e) {
			System.err.println("L'indice n'est pas un nombre");
			return "";
		}
		
		// Chaque char d'une string est code par le meme char de la cle
		String[] lettres = new String[longueurCle];
		Arrays.fill(lettres, "");
		
		String message = FileGetText.getText(fichierIn);
		
		int cpt = 0;
		for (char c : message.toCharArray()) {
			if (Character.isLetter(c)) {
				lettres[cpt++ % longueurCle] += String.valueOf(c);
			}
		}
		
		int[][] nbLettreMessage = new int[longueurCle][26];
		
		for (int i = 0 ; i < longueurCle ; ++i) {
			Arrays.fill(nbLettreMessage[i], 0);
			String s = lettres[i];
			for (char c : s.toCharArray()) {
				
				nbLettreMessage[i][c - 'a'] += 1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int[] s : nbLettreMessage) {
			// indice de la lettre la plus frequente -> probablement un 'e',
			// - 4 pour retomber sur le 'e'
			sb.append((char) (('a' + utils.Utils.getIndMax(s)) - 4));
		}
		clePotentiel = sb.toString();

		long endTime = System.currentTimeMillis();
		System.err.println("Temps de :" + (endTime - startTime) + "ms");

		return new Code("v", clePotentiel, fichierIn).dechiffrer();
	}
	
}
