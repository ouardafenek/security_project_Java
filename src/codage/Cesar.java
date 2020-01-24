package codage;


import java.util.List;
import java.util.Map;

import utils.FileGetText;

/**
 * Classe pour l'encodage et le décodage par César
 *
 * @author ZHOU Eric
 *
 */
public class Cesar {
	
	private static final Map<Character, Double> frequences_letters = FileGetText.createMap();
	public static final double MIN_CORRECTE = 0.5;
	public static final int NB_MOTS_A_TESTER = 1000;
	
	/**
	 * <b>
	 * Chiffre un message dans un fichier avec une clef
	 * </b>
	 * <p>
	 * Les lettres du fichier d'entree sont traitées une a une et ajoutées dans
	 * une chaine de retour. La chaine de caracteres original est d'abord passe
	 * en minuscule. Seul des lettres sont traitees, les autres caracteres
	 * restent inchangés.
	 * <p>
	 *
	 * @param _cle
	 *            La clef a utiliser pour le chiffrement
	 * @param _nom_fichier
	 *            Le fichier contenant le message à chiffrer
	 *
	 * @return Une chaine de caractere contenant le message chiffre
	 */
	public static String chiffrer(String _cle, String _nom_fichier) {
		int cleCesar = 0;
		try {
			if (_cle != null) {
				cleCesar = Integer.parseInt(_cle) % 26;
			}
		} catch (Exception e) {
			System.out.println("Format errone de la clé");
			return "";
		}
		
		String text = FileGetText.getText(_nom_fichier).toLowerCase();
		StringBuilder resultat = new StringBuilder();
		
		// codage lettre par lettre
		for (char c : text.toCharArray()) {
			char charCode = c;
			
			// si c'est une lettre
			if (Character.isLetter(c)) {
				charCode = (char) (charCode + cleCesar);
				if (charCode > 'z') {
					charCode -= 26;
				}
			}
			resultat.append(charCode);
		}
		return resultat.toString();
	}
	
	/**
	 * <b>
	 * Dechiffre un message dans un fichier avec une clef
	 * </b>
	 * <p>
	 * Les lettres du fichier d'entree sont traite un a un et ajoute dans une
	 * chaine de retour. La chaine de caracteres original est d'abord passe en
	 * minuscule. Seul des lettres sont traitees, les autres caracteres restent
	 * inchangee.
	 * <p>
	 *
	 * @param cle
	 *            La clef pour a utiliser pour le dechiffrement
	 * @param fichier
	 *            Le fichier contenant le message a dechiffrer
	 *
	 * @return Une chaine de caractere contenant le message dechiffre
	 */
	public static String dechiffrer(String cle, String fichier) {
		cle = cle.toLowerCase(); // On ne traite que les cas minuscules
		// Lecture du fichier
		String text = FileGetText.getText(fichier).toLowerCase();
		StringBuilder retour = new StringBuilder();
		
		int cleCesar = 0;
		
		/*
		 * la cle ne doit pas etre null et doit commencer par
		 * une lettre ou un chiffre
		 */
		if (cle != null) {
			try {
				cleCesar = Integer.parseInt(cle) % 26;
			} catch (NumberFormatException e) {
				System.err.println("Erreur de format");
				return "";
			}
		}
		
		// Decodage lettre par lettre
		for (char c : text.toCharArray()) {
			char lettreDecode = c;
			// On ne decode que si c'est une lettre
			if (Character.isLetter(c)) {
				lettreDecode = (char) (c - cleCesar);
				if (lettreDecode < 'a') {
					lettreDecode += 26;
				}
			}
			retour.append(lettreDecode);
		}
		return retour.toString();
	}

	/**
	 * Dechiffre le message
	 *
	 * @param indice
	 *            l'indice pour decrypter
	 * @param fichierIn
	 *            le fichier d'entree
	 * @param methode
	 *            la methode a utiliser
	 * @return le message decrypte
	 */
	public static String decrypter(String indice, String fichierIn, TypeDecrytage typeDecryptage) {
		switch (typeDecryptage) {
			case BruteForce:
				return decrypterBruteForce(fichierIn);
			case Mot:
				return decrypterMot(indice, fichierIn);
			case Statistique:
				return decrypterStats(fichierIn);
			default:
				System.err.println("Mthode de decrytage non implementee");
				return "";
		}
	}

	/**
	 * Decrypte un message avec Cesar en brute force
	 *
	 * @param indice
	 *            est inutile
	 * @param fichierIn
	 *            le fichier d'entree
	 * @return le message decrypte
	 */
	private static String decrypterBruteForce(String fichierIn) {
		
		long startTime = System.currentTimeMillis();

		String messageTraduit = "";

		for (int i = 0 ; i < 26 ; ++i) {
			System.out.print("clé = " + i + " : ");
			messageTraduit = Cesar.dechiffrer(String.valueOf(i), fichierIn);
			
			String[] messageMots = messageTraduit.split(" ");
			
			List<String> mots = FileGetText.getMots();
			
			int nbMotsCorrecte = 0;
			int j = 0;
			for (j = 0 ; (j < NB_MOTS_A_TESTER) && (j < messageMots.length) ; ++j) {
				if (mots.contains(messageMots[j])) {
					++nbMotsCorrecte;
				}
			}
			
			System.out.println(((double) nbMotsCorrecte) / j);
			if ((((double) nbMotsCorrecte) / j) > MIN_CORRECTE) {
				
				break;
			}
			
		}
		long endTime = System.currentTimeMillis();
		System.err.println("Temps de :" + (endTime - startTime) + "ms");

		return messageTraduit;
	}
	
	/**
	 * Decrypte un message avec Cesar en connaissant un mot
	 *
	 * @param indice
	 *            le mot connu
	 * @param fichierIn
	 *            le fichier d'entree
	 * @return le message dechiffe
	 */
	private static String decrypterMot(String indice, String fichierIn) {

		long startTime = System.currentTimeMillis();

		// recupérer dans un tableau tout les mot du fichier encrypté
		String[] motDuTexte = FileGetText.getText(fichierIn).split(" ");
		int decalage = 0;
		// on parcours ces mots
		for (int i = 0 ; i < motDuTexte.length ; i++) {
			// on a trouvé un mot qui a la meme longueur que le mot donné
			// ceci dit il peut correspondre à ce mot
			if (motDuTexte[i].length() == indice.length()) {
				// on prend le décalage de la premiere lettre et on l'applique
				// au restant des lettres
				// si ça correpond, ie : on a obtenu le mot encrypté en
				// appliquant ce décalage sur le mot
				// alors on peut errêter et dire qu'on a trouvé le décalage
				decalage = motDuTexte[i].charAt(0) - indice.charAt(0);
				int j = 1;
				boolean trouv = true;
				while (j < indice.length()) {
					if ((indice.charAt(j) + decalage) == motDuTexte[i].charAt(j)) {
						j++;
					} else {
						trouv = false;
						break;
					}
				}
				if (trouv) {
					// on a trouvé le mot
					break;
				}
			}
		}

		long endTime = System.currentTimeMillis();
		System.err.println("Temps de :" + (endTime - startTime) + "ms");

		return decalage + "\0";

	}
	
	/**
	 * Decrypte un fichier encode avec cesar avec les statistiques
	 *
	 * @param _nom_fichier
	 *            le nom du fichier
	 * @return le message decrypte
	 */
	private static String decrypterStats(String _nom_fichier) {
		
		long startTime = System.currentTimeMillis();
		
		String text = FileGetText.getText(_nom_fichier).toLowerCase();
		
		Map<Character, Double> frequences_letters_text;
		frequences_letters_text = utils.Utils.frequences_text(text);
		
		char[] lettres_initiales = new char[26];
		char[] lettres_correspondantes = new char[26];
		
		int i = 0;
		for (Map.Entry<Character, Double> mapentry : frequences_letters.entrySet()) {
			lettres_initiales[i] = mapentry.getKey();
			i++;
		}
		i = 0;
		for (Map.Entry<Character, Double> mapentry : frequences_letters_text.entrySet()) {
			lettres_correspondantes[i] = mapentry.getKey();
			i++;
		}
		
		int[] diff = new int[26];
		for (int j = 0 ; j < 26 ; j++) {
			diff[j] = lettres_correspondantes[j] - lettres_initiales[j];
		}

		long endTime = System.currentTimeMillis();
		System.err.println("Temps de :" + (endTime - startTime) + "ms");

		return new Code("c", String.valueOf(utils.Utils.getPopularElement(diff)), _nom_fichier).dechiffrer();
	}

}
