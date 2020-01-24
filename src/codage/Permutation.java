package codage;


import java.util.Map;

import utils.FileGetText;

/**
 * La classe Permutation est utilisé pour l'encodage et le décodage par
 * permutation.Chaque lettre du texte, s'il est dans la clé est remplacé par la
 * suivante dans la clé, si c'est le dernier, on prend le premier.
 * Les lettres qui ne sont pas dans la clé rentent inchangées
 *
 */
public class Permutation {
	
	private static final Map<Character, Double> frequences_letters = FileGetText.createMap();
	
	/**
	 * <b>
	 * Chiffre un message dans un fichier avec une clef
	 * </b>
	 * <p>
	 * Les lettres du fichier d'entree sont traitées une a une et ajoutées
	 * dans une chaine de retour. La chaine de caracteres original est d'abord
	 * passe en minuscule. Seul des lettres sont traitees, les autres caracteres
	 * restent inchanges.
	 * <p>
	 *
	 * @param _cle
	 *            La clef a utiliser pour le chiffrement
	 * @param _nom_fichier
	 *            Le fichier contenant le message a chiffrer
	 *
	 * @return Une chaine de caractere contenant le message chiffre
	 */
	public static String chiffrer(String _cle, String _nom_fichier) {
		String text = FileGetText.getText(_nom_fichier).toLowerCase();
		StringBuilder resultat = new StringBuilder();
		
		if (!cleEstValide(_cle)) {
			System.err.println("La clé est invalide");
			return "";
		}
		
		char[] cle = _cle.toCharArray();
		char lettreCode;
		
		for (char c : text.toCharArray()) {
			lettreCode = c;
			int indCle = _cle.indexOf(c);
			if (indCle != -1) {
				lettreCode = cle[(indCle + 1) % cle.length];
			}
			resultat.append(lettreCode);
			
		}
		return resultat.toString();
	}
	
	/**
	 * Déchiffre un message encodé par permutation
	 *
	 * @param cle
	 *            la clef utilisé
	 * @param fichier
	 *            le fichier dans lequel se trouve le texte
	 * @return une chaine représentant le texte décodé
	 */
	public static String dechiffrer(String cle, String fichier) {
		String text = FileGetText.getText(fichier).toLowerCase();
		StringBuilder retour = new StringBuilder();

		if (!cleEstValide(cle)) {
			System.err.println("La clé est invalide");
			return "";
		}
		
		for (char c : text.toCharArray()) {
			char lettreDecode = c;
			if (Character.isLetter(c)) {
				int posLettre = cle.indexOf(c);
				if (posLettre != -1) {
					lettreDecode = cle.charAt(((posLettre - 1) + cle.length()) % cle.length());
				}
			}

			retour.append(lettreDecode);
		}
		return retour.toString();

	}
	
	/**
	 * Determine si une clé pour une permutation est correcte<br>
	 * Une clé correcte est une clé où chaque caractere de la clé n'est présente
	 * qu'une seule fois
	 *
	 * @param cle
	 *            la clé
	 * @return true si la clé est correcte, false sinon
	 */
	private static boolean cleEstValide(String cle) {
		for (int i = 0 ; i < (cle.length() - 1) ; ++i) {
			if (cle.indexOf(cle.charAt(i), i + 1) != -1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Decrypte un message par permutation
	 *
	 * @param indice
	 *            l'indice
	 * @param fichierIn
	 *            le fichier d'entre
	 * @param typeDecrytage
	 *            la methode de decryptage
	 * @return le texte decrypte
	 */
	public static String decrypter(String clef, String fichierIn, TypeDecrytage typeDecrytage) {
		switch (typeDecrytage) {
			case Statistique:
				return decryptStats(fichierIn);
			
			default:
				System.err.println("La methode de decryptage n'est pas encore implemente");
				return "";
		}
	}
	
	/**
	 * retourne l'indice de l'élément e dans le tableau t s'il s'y trouve
	 * -1 sinon
	 **/
	private static int indexOf(char[] t, char e) {
		int i = 0;
		while (i < t.length) {
			if (t[i] == e) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	
	private static String decryptStats(String nom_fichier) {
		
		long startTime = System.currentTimeMillis();
		
		String text = FileGetText.getText(nom_fichier).toLowerCase();
		
		Map<Character, Double> frequences_letters_text;
		frequences_letters_text = utils.Utils.frequences_text(text);
		
		char[] lettres_initiales = new char[10];
		char[] lettres_correspondantes = new char[10];
		
		// System.out.println("Fréquences des lettres dans la langue : ");
		int i = 0;
		for (Map.Entry<Character, Double> mapentry : frequences_letters.entrySet()) {
			lettres_initiales[i] = mapentry.getKey();
			i++;
			if (i == 9) {
				break;
			}
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
		i = 0;
		// System.out.println("Fréquences des lettres dans le texte : ");
		for (Map.Entry<Character, Double> mapentry : frequences_letters_text.entrySet()) {
			lettres_correspondantes[i] = mapentry.getKey();
			i++;
			if (i == 9) {
				break;
			}
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
		
		// on réalisera un décryptage partiel sur les lettres de l'alphabet
		// ayant une fréquence > 5 % , car les autres comme elles sont de
		// probas petites , il y a de fortes chances qu'on n'est pas la bonne
		// clé
		StringBuilder textDecrypte = new StringBuilder();
		for (int j = 0 ; j < text.length() ; j++) {
			char c = text.charAt(j);
			int index = indexOf(lettres_correspondantes, c);
			if (index != -1) {
				c = lettres_initiales[index];

			}
			textDecrypte.append(c);
		}
		
		long endTime = System.currentTimeMillis();
		System.err.println("Temps de :" + (endTime - startTime) + "ms");

		return textDecrypte.toString();
	}
	
}