import javax.swing.*;

public class ProgrammePrincipale {
    public static final int TAILLE_CHAR = 500;
    public static final int TAILLE_NOMBRE = 6;

    static public void main(String[] args){

        Nombre[] tab_num = new Nombre[TAILLE_NOMBRE];
        char[] expression = new char[TAILLE_CHAR];
        char rejouer = 'o';
        int[] tailleArray = new int[1];

        while (rejouer == 'o') {
            functions.initExpression(expression);
            functions.genererNombres(tab_num);

            int nbOperations = UtilitaireFonctionsMath.alea(1,5);

            int cible = functions.trouverCible(tab_num, nbOperations, tailleArray, expression);

            functions.effectuerTour(tab_num, nbOperations, cible, expression, tailleArray);

            rejouer = functions.veutRejouer();

        }

        JOptionPane.showMessageDialog(null, "Merci d'avoir jouÃ©!");
    }
}
