/**DEBUT CLASS SNAKEBUTTON POUR LE STYLE DES BOUTTONS DANS APPLICATION**/
/**pour tous les menus**/

import javax.swing.*;
import java.awt.*;

/*DEBUT CLASS SNAKEBUTTON POUR LE STYLE GENERAL DES BOUTTONS**/
/**class à commenter**/
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
        setIcon(new ImageIcon("img/btn/btnBasic.png"));
        setRolloverIcon(new ImageIcon("img/btn/btnHover.png"));
        setPressedIcon(new ImageIcon("img/btn/btnClicked.png"));
        setForeground(new Color(99, 205, 42));
        setFont(new Font("Monospaced", Font.BOLD, 20));
    }
}
/*FIN CLASS SNAKEBUTTON**/
