import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall extends JComponent {
    private static final long serialVersionUID = 1L;

    private int posX;
    private int posY;
    private int s = 50; // Taille du côté d'une case (une case est un carré)
    private String skin;

    public Wall(int posXW, int posYW, String skinW){
        this.posX = posXW;
        this.posY = posYW;
        this.skin = skinW;
    }

    public Wall(int posXW, int posYW){
        this.posX = posXW;
        this.posY = posYW;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Rectangle rect = new Rectangle(this.posX, this.posY, this.s, this.s);

        g2.setColor(Color.BLACK);

        g2.fill(rect);

        System.out.println("paintComponent successfully ran");
    }

    public void attribution(PanelS pS){
        pS.add(this);
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public String getSkin(){
        return this.skin;
    }
}