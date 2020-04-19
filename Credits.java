import javax.swing.*; // Pour les composants graphiques que l'on ajoutera dans la méthode creerWidget
import java.awt.*;    // Pour la JFrame++++++++++++++++++
 
public class Credits extends JFrame {

  public Credits() {
 
    creerWidget();
 
    setSize(500,500);                                // Fixe la taille par défaut
    setVisible(true);                                // Affiche la fenetre
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
  }
 
  public void creerWidget() {
	

		// un texte contenant un retour à la ligne
  	String text0 = "Credits";
	String text1 = "Le jeu a ete concu par Julien, Lisa, Marion, Theo, Nathan, Arthur";
	String text2 = "La musique utilisee est celle de Harry potter, Nyan Cat etc";
	String text3 = "Pour la realisation de ce jeu on tient a remercier notre tuteur monsieur Couchot etc. ";
	 
	JTextArea taText0 = new JTextArea(text0); 
	JTextArea taText1 = new JTextArea(text1);
	JTextArea taText2 = new JTextArea(text2);
	JTextArea taText3 = new JTextArea(text3);

	JPanel pano = new JPanel(new GridLayout(4,1));
	pano.add(taText0, BorderLayout.SOUTH);
	pano.add(taText1);   // Création d'un JPanel qui va contenir
	pano.add(taText2);
	pano.add(taText3);
	setContentPane(pano);         // Ajoute pano à la fenêtre principale
	}
}
