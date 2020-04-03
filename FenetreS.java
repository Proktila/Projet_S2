import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Scanner;

public class FenetreS extends JFrame {
    // Nécessaire à la crétion d'un JFrame :
    private static final long serialVersionUID = 1L;

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
}