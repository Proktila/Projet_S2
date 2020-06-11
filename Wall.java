
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall{
    private int x;
    private int y;
    private ImageIcon wall;

    public Wall(Model model,Snake snake,Fruit fruit){
        this.wall = new ImageIcon("img/snake/blackWall.png");
        int x,y;
        do {
            x = randomX();
            y = randomY();
        }while(wallIsNotValid(x,y,snake,fruit) || (model.getMode() == "duo" && wallIsNotValid(x,y,model.getJ2(),fruit)));
        this.x = x;
        this.y = y;
        model.getListeWall().add(this);
    }

    public Wall(Model model,int x, int y){
        this.wall = new ImageIcon("img/snake/blackWall.png");
        this.x = x;
        this.y = y;
        model.getListeWall().add(this);
    }

    public int randomX(){
        int random = (int)(Math.random()*((700)+1));
        while(random%20 != 0){
            random = (int)(Math.random()*((700)+1));
        }
        return random;
    }
    public int randomY(){
        int random = (int) (Math.random() * ((660) + 1));
        while(random % 20 != 0){
            random = (int) (Math.random() * ((660) + 1));
        }
        return random;
    }

    public boolean wallIsNotValid( int x, int y,Snake snake,Fruit fruit){
        for(int i = 0; i < snake.getTaille();i++){
            if(((x == snake.getSnake()[i][0]) && (y == snake.getSnake()[i][1])) ||
                    ((x == snake.getSnake()[i][0]+20) && (y == snake.getSnake()[i][1])) ||
                    ((x == snake.getSnake()[i][0]) && (y == snake.getSnake()[i][1]+20))||
                    ((x == snake.getSnake()[i][0]-20) && (y == snake.getSnake()[i][1]))||
                    ((x == snake.getSnake()[i][0]) && (y == snake.getSnake()[i][1]-20))||
                    ((x == snake.getSnake()[i][0]) && (y == snake.getSnake()[i][1]))){
                if(x == fruit.getPosX() && y == fruit.getPosY()){
                    return true;
                }
            }
        }
        return false;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public ImageIcon getWall() { return wall; }
}
