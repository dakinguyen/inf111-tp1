import javax.swing.*;

public class ProgrammePrincipale {
    public static final int TAILLE_CHAR = 500;
    public static final int TAILLE_NOMBRE = 6;

    static public void main(String[] args){

    	// Definir les variables qui servira de contenir les nombres et les expressions du jeu
        Nombre[] tab_num = new Nombre[TAILLE_NOMBRE];
        char[] expression = new char[TAILLE_CHAR];
        char rejouer = 'o';
        int[] tailleArray = new int[1];

        // Tant que l'utilisateur veut rejouer on fait la boucle
        while (rejouer == 'o') {
        	
        	// Initialisation de du tableau de Nombres et du tableau d'expression
            functions.initExpression(expression);
            functions.genererNombres(tab_num);
            functions.initBoolen(tab_num);

            // Trouver le nombre d'operations qui se fera
            int nbOperations = UtilitaireFonctionsMath.alea(1,5);

            // Trouver la cible
            int cible = functions.trouverCible(tab_num, nbOperations, tailleArray, expression);

            // L'utilisateur effectue son tour
            functions.effectuerTour(tab_num, nbOperations, cible, expression, tailleArray);

            // Demande si l'utilisateur veut rejouer
            rejouer = functions.veutRejouer();

        }
        
        JOptionPane.showMessageDialog(null, "Merci d'avoir joue!");
    }
}
