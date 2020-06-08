import javax.swing.*; // Pour les composants graphiques que l'on ajoutera dans la méthode creerWidget
import java.awt.*;    // Pour la JFrame++++++++++++++++++
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Credits extends JFrame {

    private Color green = new Color(50,99,23);
    private JButton retour;

  public Credits() {

 	setTitle("Page credit du jeu"); 

 	initAttributs();

    setSize(1280,720);                                // Fixe la taille par défaut        
    setVisible(true);    

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
  }


  public void initAttributs() {

	 
	JLabel taText0 = new JLabel("CREDITS"); 
	taText0.setFont(new Font("Monospaced", Font.BOLD,50));
	JLabel taText1 = new JLabel("Le jeu à été concu par Julien, Lisa, Marion, Theo, Nathan, Arthur");
	taText1.setFont(new Font("Monospaced", Font.BOLD,20));
	JLabel taText2 = new JLabel("La musique utilisee est celle de Harry potter, Nyan Cat etc");
	taText2.setFont(new Font("Monospaced", Font.BOLD,20));
	JLabel taText3 = new JLabel("Pour la realisation de ce jeu on tient a remercier notre tuteur monsieur Couchot etc. ");
	taText3.setFont(new Font("Monospaced", Font.BOLD,20));

	JButton but1 = new JButton("Retour Menu");


	JPanel pano = new JPanel(new FlowLayout(FlowLayout.CENTER, 4000 ,100));


	pano.setBackground(green);
	pano.add(taText0);
	pano.add(taText1);   // Création d'un JPanel qui va contenir
	pano.add(taText2);
	pano.add(taText3);
	pano.add(but1);


	setContentPane(pano);  // Ajoute pano à la fenêtre principale
	}
}
