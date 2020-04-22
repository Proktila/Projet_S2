import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Scanner;

public class FenetreS extends JFrame {
    // Nécessaire à la crétion d'un JFrame :
    private static final long serialVersionUID = 1L;

    // Elements du menu PARAMETRE
    protected JSlider volumeMusique;
    protected JSlider volumeBruits;
    protected JRadioButton rbFrancais;
    protected JRadioButton rbAnglais;
    static final int VOL_MIN = 0;
    static final int VOL_MAX = 100;
    static final int VOL_INIT = 75;

    // Constructeur de la fenêtre :
    public FenetreS() {

        // Création du titre de la fenêtre 
        this.setTitle(" Premier test - SNACKe ");

        // Initialisation de la taille de la fenêtre 
        this.setSize(700, 500);

        // Centrage de la fenêtre par rapport au centre 
        this.setLocationRelativeTo(null);

        // Instruction permettant de correctement fermer la fenêtre à l'aide de la "croix rouge"
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Autorise l'utilisateur à redimensionner la fenêtre
        this.setResizable(true);

        // Permet de rendre prioritaire la fenêtre 
        this.setAlwaysOnTop(true);

        // Permet de rendre la fenêtre visible (toujours placer à la fin)
        this.setVisible(true);

    }


    public void initParametres() {
        volumeMusique = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);
        volumeBruits = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);

        volumeMusique.setMajorTickSpacing(10);
        volumeMusique.setMinorTickSpacing(5);
        volumeMusique.setPaintTicks(true);
        volumeMusique.setPaintLabels(true);

        volumeBruits.setMajorTickSpacing(10);
        volumeBruits.setMinorTickSpacing(5);
        volumeBruits.setPaintTicks(true);
        volumeBruits.setPaintLabels(true);

        rbFrancais = new JRadioButton("Français", true);
        rbAnglais = new JRadioButton("English", false);
        ButtonGroup rbLangue = new ButtonGroup();
        rbAnglais.add(rbFrancais);
        rbAnglais.add(rbAnglais);
    }

    public void creerParametresVue() {
        JLabel lSon = new JLabel("Son");
        JLabel lMusique = new JLabel("Musique");
        JLabel lBruitages = new JLabel("Bruitages");
        JLabel lLangue = new JLabel("Langue");

        JPanel panRbLangue = new JPanel();
        panRbAnglais.setLayout(new BoxLayout(panRbAnglais, BoxLayout.X_AXIS));
        panRbAnglais.add(rbFrancais);
        panRbAnglais.add(rbAnglais);
        JPanel panLangues = new JPanel();
        panAnglais.add(lLangue);
        panAnglais.add(panRbLangue);

        panGrid.add(lSon);
        JPanel panGrid = new JPanel(new GridLayout(3,2));
        panGrid.add(lMusique);
        panGrid.add(slSon);
        panGrid.add(lBruitages);
        panGrid.add(slBruits);
        panGrid.add(lLangue);
        panGrid.add(panLangues);

        JPanel panBorder = new JPanel();
        panBorder.setBorder(BorderFactory.createLineBorder(Color.black));
        panBorder.add(panGrid);

        JPanel panDecalage = new JPanel();
        panDecalage.setLayout(new BoxLayout(panDecalage, BoxLayout.Y_AXIS));
        panDecalage.add(lSon);
        panDecalage.add(panBorder);

        JPanel panParametre = new JPanel();
        panParametre.add(panDecalage);

        setContentPane(panParametre);
    }
}