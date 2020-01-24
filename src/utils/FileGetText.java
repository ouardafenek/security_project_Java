package utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileGetText {
	
	/**
	 * Recupere le contenu d'un fichier sous forme d'une chaine de caracteres
	 *
	 * @param fichier
	 *            Le fciher dans lequel chercher
	 *
	 * @return La chaine de caracteres du fichier
	 */
	public static String getText(String fichier) {
		StringBuilder retour = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
			
			String line = reader.readLine();
			
			while (line != null) {
				retour.append(line).append('\n');
				
				line = reader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Fichier inexistant");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Erreur lecture");
			e.printStackTrace();
		}
		
		return retour.toString();
	}
	
	public static List<String> getMots() {
		return Arrays.asList(FileGetText.getText("words.txt").split("\n"));
	}

	/**
	 * @return les frequences des lettres les plus utilisé dans la langue
	 *         anglaise
	 */
	public static Map<Character, Double> createMap() {
		Map<Character, Double> frequences_letters = new HashMap<Character, Double>();
		frequences_letters.put('a', 0.0808);
		frequences_letters.put('b', 0.0167);
		frequences_letters.put('c', 0.0318);
		frequences_letters.put('d', 0.0399);
		frequences_letters.put('e', 0.1256);
		frequences_letters.put('f', 0.0217);
		frequences_letters.put('g', 0.0180);
		frequences_letters.put('h', 0.0527);
		frequences_letters.put('i', 0.0724);
		frequences_letters.put('j', 0.0014);
		frequences_letters.put('k', 0.0063);
		frequences_letters.put('l', 0.0404);
		frequences_letters.put('m', 0.0260);
		frequences_letters.put('n', 0.0738);
		frequences_letters.put('o', 0.0747);
		frequences_letters.put('p', 0.0191);
		frequences_letters.put('q', 0.0009);
		frequences_letters.put('r', 0.0642);
		frequences_letters.put('s', 0.0659);
		frequences_letters.put('t', 0.0915);
		frequences_letters.put('u', 0.0279);
		frequences_letters.put('v', 0.0100);
		frequences_letters.put('w', 0.0189);
		frequences_letters.put('x', 0.0021);
		frequences_letters.put('y', 0.0165);
		frequences_letters.put('z', 0.0007);
		ValueComparator vc = new ValueComparator(frequences_letters);
		TreeMap<Character, Double> sorted_frequences_letters = new TreeMap<Character, Double>(vc);
		sorted_frequences_letters.putAll(frequences_letters);
		return sorted_frequences_letters;
	}
}
