package appli;


import codage.Code;

public class Decrypt {
	
	public static void main(String[] args) {
		if (args.length != 4) {
			System.err.println("Erreur parametres : <type de chiffrement> <Cle> <Fichier entree> <methode");
			System.exit(-1);
		}

		ICode code = new Code(args[0], args[1], args[2], args[3]);

		System.out.println(code.decrypter());

	}

}