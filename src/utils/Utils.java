package utils;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Utils {
	/**
	 * Trouve l'indice qui a l'élément le plus grand dans in tableau
	 *
	 * @param array
	 *            le tableau
	 * @return l'indice avec l'element de plus grand
	 */
	public static int getIndMax(int[] array) {
		int indMax = 0;
		int max = array[0];
		for (int i = 1 ; i < array.length ; ++i) {
			if (array[i] > max) {
				indMax = i;
				max = array[i];
			}
		}
		
		return indMax;
	}
	
	/**
	 * Recherche l'element le plus present dans le tableau
	 * 
	 * @param tab
	 *            le tableau
	 * @return retourne la valeur de l'element le plus frequent dans le tableau
	 */
	public static int getPopularElement(int[] tab) {
		int count = 1, tempCount;
		int popular = tab[0];
		int temp = 0;
		for (int i = 0 ; i < (tab.length - 1) ; i++) {
			temp = tab[i];
			tempCount = 0;
			for (int j = 0 ; j < tab.length ; j++) {
				if (temp == tab[j]) {
					tempCount++;
				}
			}
			if (tempCount > count) {
				popular = temp;
				count = tempCount;
			}
		}
		return popular;
	}
	/*
	 * Trouver les fréquences de toutes les lettres dans une chaine de
	 * charactères text passée en paramètres
	 * elle retourne une Map de Character,Double ou le caractère correpond à la
	 * lettre et double à sa fréquence dans le text
	 * cette map est de taille 26 (nombre de lettres de l'alphabet) et est triée
	 *
	 * @param text
	 *            la chane de caracteres
	 **/
	public static Map<Character, Double> frequences_text(String text) {
		float[] occurrences_lettres = new float[26];
		int length = text.length();
		for (int i = 0 ; i < length ; i++) {
			if (Character.isLetter(text.charAt(i))) {
				occurrences_lettres[text.charAt(i) - 'a'] += 1;
			}
		}
		Map<Character, Double> frequences_letters_text = new HashMap<Character, Double>();
		frequences_letters_text.put('a', (double) occurrences_lettres[0] / length);
		frequences_letters_text.put('b', (double) occurrences_lettres[1] / length);
		frequences_letters_text.put('c', (double) occurrences_lettres[2] / length);
		frequences_letters_text.put('d', (double) occurrences_lettres[3] / length);
		frequences_letters_text.put('e', (double) occurrences_lettres[4] / length);
		frequences_letters_text.put('f', (double) occurrences_lettres[5] / length);
		frequences_letters_text.put('g', (double) occurrences_lettres[6] / length);
		frequences_letters_text.put('h', (double) occurrences_lettres[7] / length);
		frequences_letters_text.put('i', (double) occurrences_lettres[8] / length);
		frequences_letters_text.put('j', (double) occurrences_lettres[9] / length);
		frequences_letters_text.put('k', (double) occurrences_lettres[10] / length);
		frequences_letters_text.put('l', (double) occurrences_lettres[11] / length);
		frequences_letters_text.put('m', (double) occurrences_lettres[12] / length);
		frequences_letters_text.put('n', (double) occurrences_lettres[13] / length);
		frequences_letters_text.put('o', (double) occurrences_lettres[14] / length);
		frequences_letters_text.put('p', (double) occurrences_lettres[15] / length);
		frequences_letters_text.put('q', (double) occurrences_lettres[16] / length);
		frequences_letters_text.put('r', (double) occurrences_lettres[17] / length);
		frequences_letters_text.put('s', (double) occurrences_lettres[18] / length);
		frequences_letters_text.put('t', (double) occurrences_lettres[19] / length);
		frequences_letters_text.put('u', (double) occurrences_lettres[20] / length);
		frequences_letters_text.put('v', (double) occurrences_lettres[21] / length);
		frequences_letters_text.put('w', (double) occurrences_lettres[22] / length);
		frequences_letters_text.put('x', (double) occurrences_lettres[23] / length);
		frequences_letters_text.put('y', (double) occurrences_lettres[24] / length);
		frequences_letters_text.put('z', (double) occurrences_lettres[25] / length);
		
		ValueComparator vc = new ValueComparator(frequences_letters_text);
		TreeMap<Character, Double> sorted_frequences_letters_text = new TreeMap<Character, Double>(vc);
		sorted_frequences_letters_text.putAll(frequences_letters_text);
		return sorted_frequences_letters_text;

	}
}
