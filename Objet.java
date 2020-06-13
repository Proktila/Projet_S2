import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Objet {


    private String type;
    private BufferedImage bImg = null;
    private ImageIcon img;

    private int x;
    private int y;


    /**
     * @param type
     * @param x
     * @param y
     * créer un objet avec une coordonée précise
     * affecte l'image correspondante
     */
    public Objet(String type, int x, int y){
        this.type = type;
        this.setObjet();
        this.x = x;
        this.y = y;
    }


    /**
     * affecte l'image à l'objet correspondant
     */
    public void setObjet(){
        if (type == "coin") {
            try {
                bImg = ImageIO.read(new File("img/object/coin.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (type == "key") {
            try {
                bImg = ImageIO.read(new File("img/object/key.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img = new ImageIcon(bImg);
        img = new ImageIcon(img.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH));
    }


    /**
     * @param snake
     * fais l'effet correspondant à l'objet
     */
    public void effect(Snake snake){
        switch(type) {
            default:
                break;
            case "key":
                snake.setDead(true);
                snake.setWinLaby(true);
                break;
            case "coin":
                snake.setScore(snake.getScore() + 50);
                break;
        }
    }

    public ImageIcon getImg(){
        return this.img;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
