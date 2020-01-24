package utils;


import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Character> {
	Map<Character, Double> base;

	public ValueComparator(Map<Character, Double> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	@Override
	public int compare(Character a, Character b) {
		if (this.base.get(a) >= this.base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}