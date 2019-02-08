import javax.swing.*;


/**
 * Regroupe les sous-programmes de bas-niveau et de haut-niveau (sauf le programme principal). 
 * 
 * @author Dat Quang Nguyen, Sara Nguyen, Emerick Paul, Gabriel Somma
 * @version Hiver 2019
 *
 */
public class Functions {
    public static final int QUITTER = -1;
    public static final int MATH_ERROR = -2;
    public static final int ASCII_ENTIER = 48;
    public static final int VALIDE = 1;
    public static final int NON_VALIDE = 0;
    public static final String OUI = "o";
    public static final String NON = "n";

    /**
     * Initialise un tableau de nombre pris en parametre avec des chiffres au hasard entre 1 et 9.
     *
     * @param tab_num, le tableau de Nombres
     * @return aucun
     */
    public static void genererNombres(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i] = new Nombre();
            tab_num[i].nombre = UtilitaireFonctionsMath.alea(1,9);
            tab_num[i].choisi = false;
        }
    }

    /**
     * Initialise un tableau de char avec des espaces vides pour tout le tableau.
     *
     * @param exp, le tableau de char
     * @return aucun
     */
    public static void initExpression(char[] exp) {

        for (int i=0; i < exp.length; i++) {
            exp[i] = ' ';
        }
    }

    /**
     * Initialise tout les booleans du tableau de nombre a false.
     *
     * @param tab_num, le tableau de Nombres
     * @return aucun
     */
    public static void initBoolen(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i].choisi = false;
        }
    }

    /**
     * Retourne un operateur au hasard.
     *
     * @param aucun
     * @return le char de l'operateur
     */
    public static char operateurHasard(){
        char[] operators = {'+', '-', '*', '/'};
        char ops = operators[UtilitaireFonctionsMath.alea(0,3)];
        return  ops;
    }

    /**
     * Retourne un nombre int au hasard du tableau de Nombres.
     *
     * @param tab_num, le tableau de Nombres
     * @return la valeur int du nombre
     */
    public static int nombreChoisiHasard(Nombre[] tab_num) {

        int nombre = tab_num[UtilitaireFonctionsMath.alea(0,tab_num.length - 1)].nombre;
        return nombre;
    }

    /**
     * Il faut verifier si le nombre appartient au tableau et n'a pas encore ete choisi.
     *
     * @param nombre, nombre compare au tableau
     * @param tab_num, le tableau de Nombres
     * @return vrai si le nombre n'a pas encore ete choisi et est dans le tableau, false dans au moins un des cas echeant
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
     * Cette fonction fait le calcul et retourne le resultat de l'operation.
     *
     * @param nbChoisi1, premier nombre du calcul
     * @param operation, operation choisie
     * @param nbChoisi2, deuxieme nombre du calcul
     * @return retourne le resultat, ou -2 si jamais le resultat de la division ne donne pas un entier ou celui de la soustraction est negatif
     */
    public static int resultatOperation(int nbChoisi1, char operation, int nbChoisi2) {

        int resultat;

        switch (operation) {
            case '+': resultat = nbChoisi1 + nbChoisi2;
                break;
            case '-': resultat = nbChoisi1 - nbChoisi2;
                if (resultat < 0) {
                    return MATH_ERROR;
                }
                break;
            case '*': resultat = nbChoisi1 * nbChoisi2;
                break;
            case '/': resultat = nbChoisi1 / nbChoisi2;
                if (nbChoisi1 % nbChoisi2 != 0)
                    return MATH_ERROR;
                break;
            default: resultat = MATH_ERROR;
        }

        return resultat;
    }

    /**
     * La fonction permet de trouver le nombre choisi dans le tableau et change son booleen a vrai.
     *
     * @param nombre, compare le nombre au tableau
     * @param tab_num, le tableau de Nombres
     * @return aucun
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
     * La fonction retourne un nombre du tableau qui n'a pas encore ete choisi.
     *
     * @param tab_num, le tableau de Nombres
     * @return retourne le nombre mentionne precedemment
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

    /**
     * La fonction permet de rajouter une chaine de caractere a la fin d'un tableau de caractere
     * et retourne la taille du tableau de caractere.
     *
     * @param charArray, un tableau de caractere
     * @param taille, le nombre de caractere dans le tableau de caractere
     * @param charString, une chaine de caractere a ajouter dans le tableau de caractere
     * @return la taille du tableau de caractere
     */
    public static int concatenerNombre(char[] charArray, int taille, String charString) {

    	// mettre les caracteres du String dans le tableau de char en ordre
        for (int i = 0; i < charString.length(); i++) {
            charArray[taille] = charString.charAt(i);
            taille += 1;
        }

        return taille;
    }

    /**
     * La fonction permet de creer l'expression de l'operation faite et de le mettre
     * dans un tableau de caractere. Elle retourne la taille de celle-ci.
     *
     * @param expression, un tableau qui contient les equations
     * @param taille, le nombre de caracteres entre dans le tableau d'expression
     * @param operande1, le premiere chiffre du calcul
     * @param operateur, le caractere de l'operateur
     * @param operande2, le deuxieme chiffre du calcul
     * @param resultat, le resultat du calcul
     * @return la taille finale du tableau d'expression
     */
    public static int creerExpression(char[] expression, int taille, int operande1, char operateur, int operande2, int resultat) {

    	// Creation du String d'expression
        String op1 = "";       
        // Transformation de la valeur de l'operande1 en String
        op1 = String.valueOf(operande1);
        String op2 = "";
        // Transformation de la valeur de l'operande2 en String
        op2 = String.valueOf(operande2);
        String operateurS = "";
        // Transformation du char de l'operande2 en String
        operateurS = String.valueOf(operateur);
        String resultatStr = "";
        // Transformation de la valeur du resultat en String
        resultatStr = String.valueOf(resultat);
        String expReturn = op1 + " " + operateurS + " " + op2 + " = " + resultatStr + " ";

        // Mettre le String a la fin du tableau de caractere d'expression
        taille = concatenerNombre(expression, taille, expReturn);
        return taille;
    }

    /**
     * Transforme le tableau de Nombres en une chaine de caractere et retourne celle-ci.
     *
     * @param tab_num, le tableau de Nombres
     * @return string qui contient les nombres dans le tableau de Nombre
     */
    public static String tabToString(Nombre[] tab_num) {

        String str = "";
        // Mettre chaque valeur du tableau de Nombre dans le String suivant d'un espace
        for(int i =0; i < tab_num.length; i++) {
            str += tab_num[i].nombre;
            str += " ";
        }

        return str;
    }
    
    /**
     * Demande a l'utilisateur d'entrer un nombre et permet de valider si
     * le nombre entre n'est pas encore utilise. Retourne cette valeur si 
     * elle est valide.
     *
     * @param tab_num, le tableau de Nombres
     * @return le nombre entre par l'utilisateur
     */
    public static int nombreValideClavier(Nombre[] tab_num) {

        int valide = NON_VALIDE;
        int nombreEntree = NON_VALIDE;

        // Tant que le nombreEntree n'est pas valide, on redemande a l'utilisateur d'entrer
        // des valeurs
        while (valide == NON_VALIDE) {
            try {
                nombreEntree = Integer.valueOf(JOptionPane.showInputDialog(null,
                        "Entrer un nombre existant dans la liste et non deja utilise ou -1 pour annuler: " + tabToString(tab_num))).intValue();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "SVP entrez un chiffre!");
            }
            if (nombreEntree == QUITTER) {
                valide = VALIDE;
            }

            if(nombreExiste(nombreEntree, tab_num)){
                valide = VALIDE;
            }
        }

        return nombreEntree;
    }


    /**
     * Afficher la presentation du probleme a resoudre.
     *
     * @param nombre, le tableau de Nombres
     * @param nbOperations, le nombre d'operations obligatoires
     * @param resultat, la valeur cible a obtenir
     * @return aucun
     */
    public static void afficherNombres(Nombre[] nombre, int nbOperations, int resultat){
        
        String tabNombre;
        tabNombre = tabToString(nombre); 
        
        JOptionPane.showMessageDialog(null,
                "------------\n" +
                        "\tListe des nombres\n" +
                        "------------\n" +
                        tabNombre +
                        "\nNombre d'operations obligatoires: " +
                        nbOperations +
                        "\nLa cible est: " +
                        resultat);

    }


    /**
     * Gere la fenetre demandant a l'utilisateur d'entrer un operateur et valide l'operateur entre.
     *
     * @param aucun
     * @return l'operateur entre par l'utilisateur
     */
    public static char operateurValideClavier(){

        String operateur;
        
        //Demander d'entrer un operateur tant que celui entre n'est pas valide
        do{

            operateur = JOptionPane.showInputDialog(null, "Entrez un operateur (+,-,*,/ ou = pour annuler)");

        }while(!operateur.contentEquals("+") && !operateur.contentEquals("-") && !operateur.contentEquals("*") && !operateur.contentEquals("/") && !operateur.contentEquals("="));

        return operateur.charAt(0);
    }


    /**
     * Affiche la fenetre d'accueil.
     *
     * @param aucun
     * @return aucun
     */
    public static void afficherEntete(){

        JOptionPane.showMessageDialog(null,
                "Le jeu consiste a trouver une valeur cible en utilisant un nombre d'operations fixe a l'avance et une liste de nombres fournie.\n\n\n" +
                        "-Un nombre peut se repeter dans la liste fournie + \n" +
                        "-Les seules operations permises sont: + - * et /\n" +
                        "-Un nombre de la liste ne peut etre utilise qu'une fois dans la solution\n" +
                        "-En tout temps la valeur -1 annule l'operation en cours");

    }


    /**
     * Affiche l'expression de la solution dans une fenetre.
     *
     * @param expression, le tableau de caractere contenant l'expression de la solution
     * @return aucun
     */
    public static void afficherExpression(char[] expression, int[] tailleArray){

        String reponse = "";
        int compteur_espace = 0;
        
        
        for(int i = 0; i < tailleArray[0]; i++){

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
     * Gere la fenetre demandant a l'utilisateur s'il veut rejouer une partie.
     *
     * @return le choix de l'utilisateur (s'il veut rejouer ou non)
     */
    public static char veutRejouer(){

        String choix;

        //Montrer le message tant que l'utilisateur n'entre pas un choix valide
        do{
            choix = JOptionPane.showInputDialog(null, "Voulez vous rejouer une partie?");
            choix.toLowerCase();

        }while(!choix.contentEquals(OUI) && !choix.contentEquals(NON));

        return choix.charAt(0);

    }

    /**
     * Interagit avec l'utilisateur lorsqu'il effectue ses operations et quitte lorsqu'il le demande.
     *
     * @param tab_num, le tableau de Nombres
     * @param nb_operations, le nombre d'operation max a effectuer
     * @return le resultat des operations
     */
    public static int obtenirResultatValide(Nombre[] tab_num, int nb_operations) {
        initBoolen(tab_num);
        int iterator = 0;

        int resultat = nombreValideClavier(tab_num);

        if (resultat != QUITTER) {
            ajusterNombreChoisi(resultat, tab_num);
        }

        while (resultat !=QUITTER && iterator < nb_operations) {
            char operator = operateurValideClavier();

            if (operator != '=') {
                do {
                    int deuxieme = nombreValideClavier(tab_num);

                    if (deuxieme != QUITTER) {
                        ajusterNombreChoisi(deuxieme, tab_num);
                        resultat = resultatOperation(resultat, operator, deuxieme);
                    } else {
                        resultat = QUITTER;
                    }
                    //On verifie ici si l'operation ne donne
                    //pas un chiffre negatif ou un chiffre a virgule comme resultat
                } while (resultat == MATH_ERROR);
                iterator++;
            } else {
                resultat = QUITTER;
            }
        }
        return resultat;
    }



    /**
     * Cette fonction permet de jouer un tour, verifie si le nombre est valide et ce, tant que l'utilisateur ne trouve pas le bon resultat. Tant que l'utilisateur ne trouve pas le bon resultat, le jeu affiche un message d'echec, il affiche un message de reussite une fois le resultat trouve.
     *
     * @param tab, le tableau de Nombres
     * @param nbOperation, le nombre d'operations
     * @param cible, la cible (le resultat a obtenir)
     * @param expression, l'expression du resultat une fois la cible attente
     * @param tailleArray, le tableau qui contient la taille du tableau de caractere de l'expression
     * @return aucun
     */
    public static void effectuerTour (Nombre[] tab, int nbOperation, int cible, char[] expression, int[] tailleArray) {
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
        } while (resultat != QUITTER && resultat != cible);

        afficherExpression(expression, tailleArray);
    }


    /**
     * Genere un resultat(la cible) a partir de la serie de nombres genere juste avant et avec le nombre d’operation imposes.
     *
     * @param tab_num, le tableau de Nombres
     * @param nbOperation, le nombre d'operations
     * @param tailleArray, le tableau qui contient le nombre de caractere dans le tableau d'expression
     * @param expression, le tableau qui contient les expressions
     * @return la valeur de la cible
     */
    public static int trouverCible(Nombre[] tab_num, int nbOperation, int[] tailleArray, char[] expression) {
    	// Definition des variables
    	int cible;
        int nombre_2;
        int compteurOperation = 0;
        char operateur;
        int resultat;
        int taille = 0;

        // Prendre un nombre aleatoire dans le tableau de Nombre et mettre le boleen a true (choisi)
        cible = nombreChoisiHasard(tab_num);
        ajusterNombreChoisi(cible, tab_num);


        // Choisir un 2e nombre qui n'a pas encore ete choisi
        nombre_2 = nombrePasDejaChoisi(tab_num);
        ajusterNombreChoisi(nombre_2, tab_num);

        // Tant que le nombre d'operations n'a pas ete atteint, on continue la boucle
        while (compteurOperation < nbOperation) {
        	// Choisir au hasard un operateur
            operateur = operateurHasard();
            // Trouver le resultat de l'operation
            resultat = resultatOperation(cible, operateur, nombre_2);
           
            // Si l'operation est bonne
            if (resultat!= MATH_ERROR) {
                taille = creerExpression(expression, taille, cible, operateur, nombre_2, resultat);
                // On met la cible egal au resultat
                cible = resultat;
                
                // Si le nombre d'operation n'a pas ete atteint, on trouve un 2e nombre
                if (compteurOperation < nbOperation) {
                    nombre_2 = nombrePasDejaChoisi(tab_num);
                    ajusterNombreChoisi(nombre_2, tab_num);
                }
                // Incrementation du compteur d'operation
                compteurOperation ++;
            }
            // Mettre la valeur de la taille dans le tableau
            tailleArray[0] = taille;
        }
        return cible;
    }
}
