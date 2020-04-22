import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class FenetreS extends JFrame {
    // Nécessaire à la crétion d'un JFrame :
    private static final long serialVersionUID = 1L;

    // Elements du menu PARAMETRE
    protected JSlider slVolumeMusique;
    protected JSlider slVolumeBruits;
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
        slVolumeMusique = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);
        slVolumeBruits = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);

        slVolumeMusique.setMajorTickSpacing(10);
        slVolumeMusique.setMinorTickSpacing(5);
        slVolumeMusique.setPaintTicks(true);
        slVolumeMusique.setPaintLabels(true);

        slVolumeBruits.setMajorTickSpacing(10);
        slVolumeBruits.setMinorTickSpacing(5);
        slVolumeBruits.setPaintTicks(true);
        slVolumeBruits.setPaintLabels(true);

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
        panRbLangue.setLayout(new BoxLayout(panRbLangue, BoxLayout.X_AXIS));
        panRbLangue.add(rbFrancais);
        panRbLangue.add(rbAnglais);
        JPanel panLangues = new JPanel();
        panLangues.add(lLangue);
        panLangues.add(panRbLangue);

        JPanel panGrid = new JPanel(new GridLayout(3,2));
        panGrid.add(lMusique);
        panGrid.add(slVolumeMusique);
        panGrid.add(lBruitages);
        panGrid.add(slVolumeBruits);
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