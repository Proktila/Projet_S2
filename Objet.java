import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Objet {

    private static final long serialVersionUID = 1L;

    private String type;
    private BufferedImage bImg = null;
    private ImageIcon img;

    private String effet;
    private int x;
    private int y;
    private boolean bonus;

    public Objet(){}

    public Objet(String type){
        this.type = type;
        this.setFruit();
    }

    public Objet(String type, int x, int y){
        this.type = type;
        this.setFruit();
        this.x = x;
        this.y = y;
    }

    public void setFruit(){
        if (type == "coin") {
            try {
                bImg = ImageIO.read(new File("img/object/coin.png"));
                effet = "score+50";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (type == "key") {
            try {
                bImg = ImageIO.read(new File("img/object/key.png"));
                effet = "win";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        img = new ImageIcon(bImg);
        img = new ImageIcon(img.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH));
    }

    public void effect(Snake snake,Model model){
        switch(type) {
            default:
                break;
            case "key":
                snake.setDead(true);
                break;
            case "coin":
                snake.setScore(snake.getScore() + 50);
                break;
        }
    }

    public String toString(){
        return (type+", effet est : "+ effet);
    }

    public ImageIcon getImg(){
        return this.img;
    }

    // renvoie true si l'objet se trouve sur une partie du serpent
    public boolean objectIsOnSnake(int x, int y,Snake snake,Model model){
        for(int i = 0; i < snake.getTaille();i++){
            if((x == snake.getSnake()[i][0]) && (y == snake.getSnake()[i][1])){
                return true;
            }
        }
        return false;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
