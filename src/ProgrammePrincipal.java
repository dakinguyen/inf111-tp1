import java.util.Scanner;
/**
 * 
 * @author daki
 *
 */
public class ProgrammePrincipal {
	
	public static Scanner clavier = new Scanner(System.in);
	
	public static final int ASCII_ENTIER = 48;
	public static final int TAILLE_NOMBRE = 6;
	
	public static void main(String[] args) {
		
	}
	
	/*
	 * GONNA HAVE TO DO THIS BEFORE CALLING CONCATENERNOMBRE POUR LA TAILLE!!
	 * 	char n = expression[0];
		int compteur = 0;
		while (n != '\0') {
			n = expression[compteur];
			compteur++;
		}
	 */
	public static int concatenerNombre(char[] charArray, int taille, String charString) {
		
		charArray[taille-1] = ' ';
		
		for (int i = 0; i < charString.length(); i++) {
			charArray[taille] = charString.charAt(i);
			taille += 1;
		}
		
		return taille;
	}
	
	public static int creerExpression(char[] expression, int taille, int operande1, char operateur, int operande2, int resultat) {
		
		expression[0] = (char)(ASCII_ENTIER + operande1);
		expression[1] = ' ';
		expression[2] = operateur;
		expression[3] = ' ';
		expression[4] = (char)(ASCII_ENTIER + operande2);
		expression[5] = ' ';
		expression[6] = '=';
		expression[7] = ' ';
		if (resultat < 10) {
			expression[8] = (char)(ASCII_ENTIER + resultat);
			taille = 9;
			
		} else if (resultat >= 10 && resultat < 100) {
			expression[8] = (char) (ASCII_ENTIER + Math.floor(resultat/10));
			expression[9] = (char)(ASCII_ENTIER + (resultat % 10));
			taille = 10;
		} else if (resultat == 100) {
			expression[8] = '1';
			expression[9] = '0';
			expression[10] = '0';
			taille = 11;
		}
		
		return taille;
	}
	
	public static String tabToString(Nombre[] tab_num) {
		
		char[] copyNombre = new char[40];
		int curseur = 0;
		copyNombre[curseur] = '{';
		curseur += 1;
		for (int i = 0; i < tab_num.length; i++) {
			if (tab_num[i].nombre == 10) {
				copyNombre[curseur] = (char)(ASCII_ENTIER + 1);
				copyNombre[curseur + 1] = (char)(ASCII_ENTIER + 0);
				copyNombre[curseur + 2] = ' ';
				curseur += 3;
			} else if (tab_num[i].nombre < 10) {
				copyNombre[curseur] = (char) (ASCII_ENTIER + tab_num[i].nombre);
				copyNombre[curseur + 1] = ' ';
				curseur += 2;
			}
		}
		copyNombre[curseur - 1] = '}';
		return new String(copyNombre, 0, curseur);
	}
	public static int nombreValideClavier(Nombre[] tab_num) {
		
		int valide = 0;
		int nombreEntree = 0;
		
		while (valide == 0) {
			System.out.print("Entrer un nombre existant dans la liste et non deja utilise ou -1 pour annuler: " + tabToString(tab_num));
			nombreEntree = clavier.nextInt();
			
			if (nombreEntree == -1) {
				valide = 1;
			}
			
			for (int i = 0; i < TAILLE_NOMBRE; i++) {
				if (nombreEntree == tab_num[i].nombre) {
					if (tab_num[i].choisi == false) {
						valide = 1;
					}
				}
			}
		}
		
		return nombreEntree;
	}
}
