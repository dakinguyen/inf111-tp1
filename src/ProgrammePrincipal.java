import java.util.*;
import javax.swing.*;
public class ProgrammePrincipal {

	static JFrame frame = new JFrame();
	public static Scanner nombreChoisi = new Scanner(System.in);
	public static void main(String[] args) {
	
		Nombre[] tab_num = new Nombre[6];
		genererNombres(tab_num);
		
	}
	
	public static void genererNombres (Nombre[] tab_num) {
		
		for (int i = 0; i < tab_num.length; i++) {
			tab_num[i] = new Nombre();
			tab_num[i].nombre = UtilitaireFonctionsMath.alea(1, 10);
			tab_num[i].choisi = false;
			String message = ("" + tab_num[i].nombre + " " + tab_num[i].choisi);
			JOptionPane.showMessageDialog(frame, message);
		}
	}
	
	public static boolean nombreExiste(int nombre, Nombre[] tab_num) {
		
		boolean nombreExiste = false;
		
		for (int j = 0; j < tab_num.length; j++) {
			if (nombre == tab_num[j].nombre && tab_num[j].choisi == false) {
				System.out.println("Le nombre existe et n'a pas encore ete choisi");
				nombreExiste = true;
			}
		}	
		return nombreExiste;
	}
	
	public static int resultatOperation(int nbChoisi1, char operation, int nbChoisi2) {
		
		Nombre[] tab_num = new Nombre[6];
		
		genererNombres(tab_num);
		
		nombreExiste(nbChoisi1, tab_num);
		nombreExiste(nbChoisi2, tab_num);
		
		int resultat;
		
		switch (operation) {
			case '+': resultat = nbChoisi1 + nbChoisi2;
				break;
			case '-': resultat = nbChoisi1 - nbChoisi2;
				break;
			case '*': resultat = nbChoisi1 * nbChoisi2;
				break;
			case '/': resultat = nbChoisi1 / nbChoisi2;
				if (nbChoisi1 % nbChoisi2 != 0)
					return -1;
				break;
			default: resultat = -1;
		}
	 
		return resultat;
	}
	
	public static void ajusterNombreChoisi (int nombre, Nombre[] tab_num) {
		
		for (int i = 0; i < tab_num.length; i ++) {
			if (nombre == tab_num[i].nombre && tab_num[i].choisi == false) {
				tab_num[i].choisi = true;
				i = tab_num.length;
			}
		}
		
	}
	
	public static int nombrePasDejaChoisi (Nombre[] tab_num) {
		boolean choisi;	
		int nombre = 0;
		
		do {
			nombre = nombreChoisiHasard(tab_num);
			choisi = nombreExiste(nombre, tab_num);
		} while (!choisi);
		
		return nombre;
	}
}
