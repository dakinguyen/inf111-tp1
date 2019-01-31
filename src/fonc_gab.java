import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;



public class fonc_gab {

    static Scanner scanner = new Scanner(System.in);
    static JFrame frame = new JFrame();
    public static final int TAILLE_CHAR = 20;
    public static final int TAILLE_NOMBRE = 6;
    private static Random rand = new Random(System.currentTimeMillis());

    public static void main(String[] args){
        Nombre[] tab_num = new Nombre[TAILLE_NOMBRE];
        char[] expression = new char[TAILLE_CHAR];
        char[] operator = {'+', '-', '*', '/'};

        genererNombres(tab_num);
        initBoolen(tab_num);
        initExpression(expression);
        operateurHasard();
        /*nombreChoisiHasard(tab_num);
        nombreChoisiHasard(tab_num);
        nombreChoisiHasard(tab_num);
        nombreChoisiHasard(tab_num);
        nombreChoisiHasard(tab_num);*/

        //afficherNombre(tab_num, 4, 16);
        /*int n = nombreValideClavier(tab_num);
        if (n == JOptionPane.OK_OPTION) {
            System.out.println("OK");
        }
        else if (n == JOptionPane.CANCEL_OPTION) {
            System.out.println("Cancel");
        }
        else {
            System.out.println("error");
        }*/
        //afficherEntete();
        creerExpression(expression, 5, '+', 7, 12);
        String exp = "12-5=7";
        concatenerNombre(expression, exp.length(), exp);

        afficherExpression(expression);
        

    }

    public static void genererNombres(Nombre[] tab_num) {

        for (int i=0; i < tab_num.length; i++) {
            tab_num[i] = new Nombre();
            tab_num[i].nombre = rand.nextInt(10);
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
        char ops = operators[rand.nextInt(operators.length)];
        System.out.println(ops);
        return  ops;
    }

    public static int nombreChoisiHasard(Nombre[] tab_num) {
        
        int nombre = tab_num[rand.nextInt(tab_num.length)].nombre;
        System.out.println(nombre);
        return nombre;
    }
    
    public static boolean nombreExiste(int nombre, Nombre[] tab_num) {
        for (int i=0; i < tab_num.length; i++) {
            if (nombre == tab_num[i].nombre && !tab_num[i].choisi){
                return true;
            }
        }
        return false;
    }

    public static int resultatOperation(int operande1, char operateur, int operande2) {
        int reponse;
        switch (operateur) {
            case '+':
                reponse= operande1 + operande2;
                break;

            case '-':
                reponse= operande1 - operande2;
                break;

            case '*':
                reponse = operande1 * operande2;
                break;

            case '/':
                reponse = operande1 / operande2;
                break;

            default:
                reponse = 0;
        }
        return reponse;
    }

    public static void ajusterNombreChoisi(int nombre, Nombre[] tab_num) {
        for (int i=0; i < tab_num.length; i++) {
            if (nombre == tab_num[i].nombre) {
                tab_num[i].choisi = true;
                i = tab_num.length;
            }
        }
    }

    public static int nombrePasDejaChoisi(Nombre[] tab_num) {
        boolean correct;
        int nombre;

        do {
            nombre = nombreChoisiHasard(tab_num);
            correct = nombreExiste(nombre, tab_num);
        } while (!correct);

        return nombre;
    }

    public static int concatenerNombre(char[]expression, int taille, String chaineChar) {
        for (int i=0; i < chaineChar.length(); i++) {
            expression[taille + i] = chaineChar.charAt(i);
        }

        return taille + chaineChar.length();
    }

    public static int creerExpression(char[] expression, int operande1, char operateur, int operande2, int resultat) {
        expression[0] = (char)(operande1 + '0');
        expression[1] = operateur;
        expression[2] = (char)(operande2 + '0');
        expression[3] = '=';
        String res = Integer.toString(resultat);
        for (int i=0; i< res.length(); i++) {
            expression[4+i]= res.charAt(i);
        }
        expression[4+res.length()] = '.';
        return expression.length;
    }

    public static String tabToString(Nombre[] tab_num) {
        String str = "";
        for(int i =0; i < tab_num.length; i++) {
            str += tab_num[i];
        }

        return str;
    }

    public static void afficherNombre(Nombre[] tab_num, int nb_operation, int resultat) {
        String message = "-------------------\n";
        message+= "\t Liste des nombres\n";
        message+= "-------------------\n";

        for (int i=0; i < tab_num.length; i++) {
            message+= tab_num[i].nombre;
        }
        message+= "\n";
        message+= "Nombre d'operation obligatoire : " + nb_operation + "\n";
        message+= "La cible est : " + resultat;

        JOptionPane.showMessageDialog(frame, message);

    }

    public static int nombreValideClavier(Nombre[] tab_num) {
        Object[] options = {"ok", "cancel"};
        int n = JOptionPane.showOptionDialog(frame, "hello", "title", JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return n;
    }

    public static void afficherEntete() {
        String message = "Le jeu consiste a trouver une valeur cible en utilisant un nombre d'opération fixe a ";
        message+= "l'avance et une liste de nombre fournies";
        message+= "\n\n\n";
        message+= "-Un seul nombre peut se repeter dans la liste fournie \n";
        message+= "-Les seules operations permises sont : + - * et / \n";
        message+= "-Un nombre de la liste de nombre ne peut etre utilise qu'une fois dans la solution \n";
        message+= "-En tout temps la valeur -1 annule l'operation en cours";

        JOptionPane.showMessageDialog(frame, message);

    }

    public static void afficherExpression(char[] expression) {
        String message = "Une solution : ";

        for (int i=0; i < expression.length; i++ ) {
            message+= expression[i];

            if (expression[i] == '.') {
                message+= expression[i];
                message+= "\n";
            }
        }

        JOptionPane.showMessageDialog(frame, message);
    }

}
















