import javax.swing.*;

/**
 * Le programme principal du projet.
 * 
 * @author Dat Quang Nguyen, Sara Nguyen, Emerick Paul, Gabriel Somma
 * @version Hiver 2019
 *
 */
public class ProgrammePrincipale {
    public static final int TAILLE_CHAR = 500;
    public static final int TAILLE_NOMBRE = 6;

    /**
     * Boucle qui s'execute tant que le joueur veut continuer a jouer, assumant qu'au depart, le joueur veut jouer au moins une partie..
     * 
     * @param args
     * @return aucun
     */
    static public void main(String[] args){

    	// Definir les variables qui servira de contenir les nombres et les expressions du jeu
        Nombre[] tab_num = new Nombre[TAILLE_NOMBRE];
        char[] expression = new char[TAILLE_CHAR];
        char rejouer = 'o';
        int[] tailleArray = new int[1];

        // Tant que l'utilisateur veut rejouer on fait la boucle
        while (rejouer == 'o') {
        	
        	// Initialisation de du tableau de Nombres et du tableau d'expression
            Functions.initExpression(expression);
            Functions.genererNombres(tab_num);
            Functions.initBoolen(tab_num);

            // Trouver le nombre d'operations qui se fera
            int nbOperations = UtilitaireFonctionsMath.alea(1,5);

            // Trouver la cible
            int cible = Functions.trouverCible(tab_num, nbOperations, tailleArray, expression);

            // L'utilisateur effectue son tour
            Functions.effectuerTour(tab_num, nbOperations, cible, expression, tailleArray);

            // Demande si l'utilisateur veut rejouer
            rejouer = Functions.veutRejouer();

        }
        
        JOptionPane.showMessageDialog(null, "Merci d'avoir joue!");
    }
}
