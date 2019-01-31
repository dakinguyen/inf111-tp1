import javax.swing.JOptionPane;

public class ProgrammePrincipal {
	
	public static void main(String[] args){
		
		Nombre[] tabNombre = new Nombre[10]; //creer tableau de ref
		for(int i = 0;i<tabNombre.length; i++){
			tabNombre[i] = new Nombre();
			tabNombre[i].nombre = 6;
			tabNombre[i].choisi = false; //ressemble a generer nombre
			
		}
		
		char tabChar[]= {'5',' ','+',' ','2',' ','=',' ','7',' ','7','+'};
		
		afficherNombres(tabNombre, 4, 3);
		veutRejouer();
		afficherExpression(tabChar);
		afficherEntete();
		operateurValideClavier();
		
	}
	
	/**
	 * 
	 * @param nombre
	 * @param nbOperations
	 * @param resultat
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
	
	

}
