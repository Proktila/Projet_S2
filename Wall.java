
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall{
    private int x;
    private int y;
    private ImageIcon wall;

    /**
     * @param model
     * @param snake
     * @param fruit
     * création d'un mur avec une postion aléatoire qui n'encombre rien et l'ajoute à une liste de mur
     * on a besoin de la postion du snake et de la postion du fruit actuelle
     * ainsi que du model pour la liste
     */
    public Wall(Model model,Snake snake,Fruit fruit){
        this.wall = getWallColor(model);
        int x,y;
        do {
            x = randomX();
            y = randomY();
        }while(wallIsNotValid(x,y,snake,fruit) || (model.getMode() == "duo" && wallIsNotValid(x,y,model.getJ2(),fruit)));
        this.x = x;
        this.y = y;
        model.getListeWall().add(this);
    }


    /**
     * @param model
     * @param x
     * @param y
     * création d'un mur avec des coordonées précises et ajout dans une liste de murs
     */
    public Wall(Model model,int x, int y){
        this.wall = getWallColor(model);
        this.x = x;
        this.y = y;
        model.getListeWall().add(this);
    }

    /**
     * Change le mur en fonction du theme du plateau
     * @return ImageIcon du bon mur
     */
    private ImageIcon getWallColor(Model model) {
        if (model.getTheme().equals("Classic") || model.getTheme().equals("Cloud") || model.getTheme().equals("Wall")) return new ImageIcon("img/snake/blackWall.png");
        return new ImageIcon("img/snake/whiteWall.png");
    }

    /**
     * @return un int dans une postiion aléatoire mais dans les limites de l'écran
     */
    public int randomX(){
        int random = (int)(Math.random()*((700)+1));
        while(random%20 != 0){
            random = (int)(Math.random()*((700)+1));
        }
        return random;
    }

    /**
     * @return un int dans une postion aléatoire mais dans les limites de l'écran
     * classe différentes car cela differe en fonction du systeme d'exploitation
     */
    public int randomY(){
        int random = (int) (Math.random() * ((700) + 1));
        while(random % 20 != 0){
            random = (int) (Math.random() * ((700) + 1));
        }
        return random;
    }

    /**
     * @param x
     * @param y
     * @param snake
     * @param fruit
     * @return true si le mur a un emplacement non valide
     * false si il est valide
     */
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
