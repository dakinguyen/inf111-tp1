import javax.swing.JOptionPane;

public class ProgrammePrincipal {

	//Documentation: https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
	
	public static void main(String[] args) {
		
		String reponse;
		
		do {
			
			do {
			reponse = JOptionPane.showInputDialog(null, 
	                "Pet pis Repete s'en vont en bateau, pet tombe à l'eau, qui qui reste?");
			
			if(!reponse.contentEquals("Repete") && !reponse.contentEquals("Pet")) {
				JOptionPane.showMessageDialog(null,"Répondre Pet ou Repete");
			}
			
			}while(!reponse.contentEquals("Repete") && !reponse.contentEquals("Pet"));
			
		}while(reponse.contentEquals("Repete"));
		
		
		JOptionPane.showMessageDialog(null,"Haha, je t'ai bien eu!!!!");
	}

}
