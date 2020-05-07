package com.company;

import javax.swing.*;
import java.awt.*;

class SnakeButton extends JButton {


    /*
       Créer un bouton avec des images personnalisé en fournissant un texte à afficher sur le bouton
     */
    public SnakeButton(String txt) {
        super(txt);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setFocusPainted(false);
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setIcon(new ImageIcon("img/btnBasic.png"));
        setRolloverIcon(new ImageIcon("img/btnHover.png"));
        setPressedIcon(new ImageIcon("img/btnClicked.png"));
        setForeground(new Color(99, 205, 42));
        setFont(new Font("Monospaced", Font.BOLD, 20));
    }
}
