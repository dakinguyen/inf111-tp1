import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class functions {
    static Scanner scanner = new Scanner(System.in);
    static JFrame frame = new JFrame();
    public static final int TAILLE_CHAR = 20;
    public static final int TAILLE_NOMBRE = 6;
    private static Random rand = new Random(System.currentTimeMillis());
    public static final int ASCII_ENTIER = 48;


    public static void genererNombres(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i] = new Nombre();
            tab_num[i].nombre = UtilitaireFonctionsMath.alea(1,10);
            tab_num[i].choisi = false;
            System.out.println(tab_num[i].nombre);
        }

    }

    public static void initExpression(char[] exp) {

        for (int i=0; i < exp.length; i++) {
            exp[i] = ' ';
        }
    }

    public static void initBoolen(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i].choisi = false;
        }
    }

    public static char operateurHasard(){
        char[] operators = {'+', '-', '*', '/'};
        char ops = operators[UtilitaireFonctionsMath.alea(0,3)];
        System.out.println(ops);
        return  ops;
    }

    public static int nombreChoisiHasard(Nombre[] tab_num) {

        int nombre = tab_num[UtilitaireFonctionsMath.alea(0,tab_num.length)].nombre;
        System.out.println(nombre);
        return nombre;
    }

    public static boolean nombreExiste(int nombre, Nombre[] tab_num) {

        for (int j = 0; j < tab_num.length; j++) {
            if (nombre == tab_num[j].nombre && !tab_num[j].choisi ) {
                return true;
            }
        }
        return false;
    }

    public static int resultatOperation(int nbChoisi1, char operation, int nbChoisi2) {

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
            if (nombre == tab_num[i].nombre && !tab_num[i].choisi) {
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

    public static int concatenerNombre(char[] charArray, int taille, String charString) {

        charArray[taille-1] = ' ';

        for (int i = 0; i < charString.length(); i++) {
            charArray[taille] = charString.charAt(i);
            taille += 1;
        }

        return taille;
    }

    public static int creerExpression(char[] expression, int taille, int operande1, char operateur, int operande2, int resultat) {

        expression[taille] = (char)(ASCII_ENTIER + operande1);
        expression[taille+1] = ' ';
        expression[taille+2] = operateur;
        expression[taille+3] = ' ';
        expression[taille+4] = (char)(ASCII_ENTIER + operande2);
        expression[taille+5] = ' ';
        expression[taille+6] = '=';
        expression[taille+7] = ' ';
        String res = Integer.toString(resultat);
        for (int i=0; i< res.length(); i++) {
            expression[taille+7+i]= res.charAt(i);
        }

        expression[taille + 8 + res.length()] = ' ';
        taille += taille + 8 + res.length();

        return taille;
    }

    public static String tabToString(Nombre[] tab_num) {

        String str = "";
        for(int i =0; i < tab_num.length; i++) {
            str += tab_num[i].nombre;
            str += " ";
        }

        return str;
    }
    public static int nombreValideClavier(Nombre[] tab_num) {

        int valide = 0;
        int nombreEntree = 0;

        while (valide == 0) {
            JOptionPane.showMessageDialog(null, "Entrer un nombre existant dans la liste et non deja utilise ou -1 pour annuler: " + tabToString(tab_num));
            nombreEntree = scanner.nextInt();

            if (nombreEntree == -1) {
                valide = 1;
            }

            if(nombreExiste(nombreEntree, tab_num)){
                valide = 1;
            }
        }

        return nombreEntree;
    }

    public static void afficherNombres(Nombre[] nombre, int nbOperations, int resultat){

        String tabNombre = "";

        for(int i = 0; i < nombre.length; i++) {

            tabNombre += Integer.toString(nombre[i].nombre) + " ";

        }

        JOptionPane.showMessageDialog(null,
                "------------\n" +
                        "\tListe des nombres\n" +
                        "------------\n" +
                        tabNombre +
                        "\nNombre d'opérations obligatoires: " +
                        nbOperations +
                        "\nLa cible est: " +
                        resultat);

    }

    /**
     *
     * @return operateur
     */
    public static char operateurValideClavier(){

        String operateur;

        do{

            operateur = JOptionPane.showInputDialog(null, "Entrez un opérateur (+,-,*,/ ou = pour annuler)");

        }while(!operateur.contentEquals("+") && !operateur.contentEquals("-") && !operateur.contentEquals("*") && !operateur.contentEquals("/") && !operateur.contentEquals("="));

        return operateur.charAt(0);
    }


    /**
     *
     */
    public static void afficherEntete(){

        JOptionPane.showMessageDialog(null,
                "Le jeu consiste à trouver une valeur cible en utilisant un nombre d'opérations fixé à l'avance et une liste de nombres fournie.\n\n\n" +
                        "-Un nombre peut se répéter dans la liste fournie + \n" +
                        "-Les seules opérations permises sont: + - * et /\n" +
                        "-Un nombre de la liste ne peut être utilisé qu'une fois dans la solution\n" +
                        "-En tout temps la valeur -1 annule l'opération en cours");

    }


    /**
     *
     * @param expression
     */
    public static void afficherExpression(char[] expression ){

        String reponse = "";
        int compteur_espace = 0;

        for(int i = 0; i < expression.length; i++){

            if(expression[i] == ' '){

                reponse += expression[i];
                compteur_espace += 1;

                if(compteur_espace%5 == 0){

                    reponse += "\n";

                }

            }else{

                reponse += expression[i];

            }

        }

        JOptionPane.showMessageDialog(null, "Une solution: " +
                reponse);

    }

    /**
     *
     * @return choix
     */
    public static char veutRejouer(){

        String choix;

        do{
            choix = JOptionPane.showInputDialog(null, "Voulez vous rejouer une partie?");

        }while(!choix.contentEquals("o") && !choix.contentEquals("n"));

        return choix.charAt(0);

    }

    public static int obtenirResultatValide(Nombre[] tab_num, int nb_operations) {
        initBoolen(tab_num);
        int iterator = 0;

        int resultat = nombreValideClavier(tab_num);

        if (resultat != -1) {
            ajusterNombreChoisi(resultat, tab_num);
        }

        while (resultat !=-1 && iterator < nb_operations) {
            char operator = operateurValideClavier();

            if (operator != '=') {
                int deuxieme = nombreValideClavier(tab_num);

                if (deuxieme != -1) {
                    ajusterNombreChoisi(deuxieme, tab_num);
                    resultat = resultatOperation(resultat, operator, deuxieme);
                } else {
                    resultat = -1;
                }
                iterator++;
            } else {
                resultat = -1;
            }


        }

        return resultat;

    }
    
    
    
    //Sous-programme de haut niveau
    public static void effectuerTour (Nombre[] tab, int nbOperation, int cible, char[] expression) {
    int resultat  = 0;
    
    	do {
    		afficherEntete();
    	
    		afficherNombres(tab, nbOperation, cible);
    	
    		resultat = obtenirResultatValide(tab, nbOperation);
    	
    	if (resultat == cible)
    		JOptionPane.showMessageDialog(null, "Bravo !");
    	else 
    		JOptionPane.showMessageDialog(null, "Ce n'est pas le bon resultat");
    	
    	} while (resultat != -1 || resultat != cible);
    	
    	afficherExpression(expression);
    }
}
