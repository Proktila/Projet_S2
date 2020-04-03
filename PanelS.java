import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class PanelS extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public PanelS(){
        super();
    }

    public void attribution(FenetreS fS){
        fS.add(this);
    }

    public void paintcomponent(Graphics g){

    }
    
}