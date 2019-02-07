import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class functions {
    static Scanner scanner = new Scanner(System.in);
    static JFrame frame = new JFrame();
    public static final int TAILLE_CHAR = 20;
    public static final int TAILLE_NOMBRE = 6;
    public static final int QUITTER = -1;
    public static final int MATH_ERROR = -2;
    private static Random rand = new Random(System.currentTimeMillis());
    public static final int ASCII_ENTIER = 48;

    /**
     * Initatilise un tableau de monbre pris en parametre avec des chiffres au hasard entre 1 et 9
     * @param tab_num le tableau de Nombres
     */
    public static void genererNombres(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i] = new Nombre();
            tab_num[i].nombre = UtilitaireFonctionsMath.alea(1,9);
            tab_num[i].choisi = false;
        }
    }

    /**
     * Initialise un tableau de char avec des espaces vides pour tout le tableau
     * @param exp le tabeau de char
     */
    public static void initExpression(char[] exp) {

        for (int i=0; i < exp.length; i++) {
            exp[i] = ' ';
        }
    }

    /**
     * Initialise tout les booleans du tableau de nombre a false
     * @param tab_num le tableau de Nombres
     */
    public static void initBoolen(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i].choisi = false;
        }
    }

    /**
     * Retourne un operateur au hasard
     * @return le char de l'operateur
     */
    public static char operateurHasard(){
        char[] operators = {'+', '-', '*', '/'};
        char ops = operators[UtilitaireFonctionsMath.alea(0,3)];
        return  ops;
    }

    /**
     * Retourne un nombre int au hasard du tableau de Nombres
     * @param tab_num le tableau de Nombres
     * @return la valeur int du nombre
     */
    public static int nombreChoisiHasard(Nombre[] tab_num) {

        int nombre = tab_num[UtilitaireFonctionsMath.alea(0,tab_num.length)].nombre;
        return nombre;
    }
    
    /**
     * Il faut verifier si le nombre appartient au tableau et n'a pas encore ete choisi
     * @param nombre : nombre compare au tableau
     * @param tab_num : Tableau de nombres
     * @return : vrai si le nombre n'a pas encore ete choisi et est dans le tableau, false dans au moins un des cas echeant
     */
    public static boolean nombreExiste(int nombre, Nombre[] tab_num) {

        for (int j = 0; j < tab_num.length; j++) {
            if (nombre == tab_num[j].nombre && !tab_num[j].choisi ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cette fonction fait le calcul et retourne le resultat de l'operation
     * @param nbChoisi1 : Premier nombre du calcul
     * @param operation : Operation choisie, et retourne -1 si jamais le resultat de la division ne donne pas un entier
     * @param nbChoisi2 : Deuxieme nombre du calcul
     * @return : Retourne le resultat
     */
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

    /**
     * La fonction permet de trouver le nombre choisi dans le tableau et tourne son booleen a vrai
     * @param nombre : Compare le nombre au tableau
     * @param tab_num : Tableau de nombres
     */
    public static void ajusterNombreChoisi (int nombre, Nombre[] tab_num) {

        for (int i = 0; i < tab_num.length; i ++) {
            if (nombre == tab_num[i].nombre && !tab_num[i].choisi) {
                tab_num[i].choisi = true;
                i = tab_num.length;
            }
        }

    }

    /**
     * La fonction retourne un nombre du tableau qui n'a pas encore ete choisi
     * @param tab_num : Tableau de nombre
     * @return : Retourne le nombre mentionne precedemment
     */
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

    
    /**
     * Afficher la présentation du problème à résoudre
     * 
     * @param nombre, tableau de nombres
     * @param nbOperations, le nombre d'opérations obligatoires
     * @param resultat, la valeur cible à obtenir
     * @return aucun
     */
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
     * Gère la fenêtre demandant à l'utilisateur d'entrer un opérateur et valide l'opérateur entré
     *
     * @return L'opérateur entré par l'utilisateur
     */
    public static char operateurValideClavier(){

        String operateur;

        do{

            operateur = JOptionPane.showInputDialog(null, "Entrez un opérateur (+,-,*,/ ou = pour annuler)");

        }while(!operateur.contentEquals("+") && !operateur.contentEquals("-") && !operateur.contentEquals("*") && !operateur.contentEquals("/") && !operateur.contentEquals("="));

        return operateur.charAt(0);
    }

    
    /**
     * Affiche la fenêtre d'accueil
     *
     * @return aucun
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
     * Affiche l'expression de la solution dans une fenêtre
     *
     * @param expression, le tableau de caractères contenant l'expression de la solution
     * @return aucun
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
     *Gère la fenêtre demandant à l'utilisateur s'il veut rejouer une partie
     *
     * @return le choix de l'utilisateur (s'il veut rejouer ou non)
     */
    public static char veutRejouer(){

        String choix;

        do{
            choix = JOptionPane.showInputDialog(null, "Voulez vous rejouer une partie?");
            choix.toLowerCase();

        }while(!choix.contentEquals("o") && !choix.contentEquals("n"));

        return choix.charAt(0);

    }

    /**
     * Interagit avec l'utilisateur lorsqu'il effectue ses operations et quitte lorsqu'il le demande
     * @param tab_num tableau de Nombres
     * @param nb_operations le nombre d'operation max a effectuer
     * @return le resultat des operations
     */
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
                do {
                    int deuxieme = nombreValideClavier(tab_num);

                    if (deuxieme != -1) {
                        ajusterNombreChoisi(deuxieme, tab_num);
                        resultat = resultatOperation(resultat, operator, deuxieme);
                    } else {
                        resultat = -1;
                    }
                } while (resultat == -2);
                iterator++;
            } else {
                resultat = -1;
            }
        }
        return resultat;
    }
    


    /**
     * Cette fonction permet de jouer un tour, verifie si le nombre est valide et ce, tant que l'utilisateur ne trouve pas le bon resultat. Tant que l'utilisateur ne trouve pas le bon resultat, le jeu affiche un message d'echec, il affiche un message de reussite une fois le resultat trouve
     * @param tab : Affiche le tableau de nombres
     * @param nbOperation : Affiche le nombre d'operations
     * @param cible : Affiche la cible (le resultat a obtenir)
     * @param expression : Affiche l'expression du resultat une fois la cible attente
     */
    public static void effectuerTour (Nombre[] tab, int nbOperation, int cible, char[] expression) {
    int resultat  = 0;
    	do {
    		afficherEntete();
    		afficherNombres(tab, nbOperation, cible);
    		resultat = obtenirResultatValide(tab, nbOperation);
    	
            if (resultat == cible) {
    		JOptionPane.showMessageDialog(null, "Bravo !");
            }else {
    		    JOptionPane.showMessageDialog(null, "Ce n'est pas le bon resultat");
            }
    	} while (resultat != -1 || resultat != cible);
    	
        afficherExpression(expression);
    }
}
