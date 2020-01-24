package appli;


import codage.Code;

public class Encode {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Erreur parametres : <type de chiffrement> <Cle> <Fichier entree>");
			System.exit(-1);
		}
		
		ICode code = new Code(args[0], args[1], args[2]);
		
		System.out.println(code.chiffrer());
		
	}
}
