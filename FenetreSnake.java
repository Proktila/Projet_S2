import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreSnake extends JFrame {

    Gameplay gameplay;
    FenetreMenu fenetreMenu;

    public FenetreSnake(FenetreMenu fenetreMenu) {
        gameplay = new Gameplay(this,fenetreMenu);
        setSize(1280, 720);
        setLocation(100,0);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gameplay);
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }
}

class Gameplay extends JPanel{

    private Color blue = new Color(47, 81, 103);
    private Color green = new Color(50, 99, 23);

    /*
    Tableau à double entré ou la 1ere entrée est la position d'une partie du snake par ex snake[0] = tete
    et la deuxième entré est soit la valeur x soit y ex snake[0][0] = posx de la tete
     */
    private int[][] snake = new int[720][2];

    private Color lightGreen = new Color(99, 205, 42);

    // Les différentes partie du serpent
    private ImageIcon rightHead;
    private ImageIcon leftHead;
    private ImageIcon downHead;
    private ImageIcon upHead;
    private ImageIcon body;

    // Un timer pour la vitesse
    private Timer timer;
    private int delay = 100;

    // taille du serpent au commencement
    private int taille=3;

    // on ne donne aucune direction au serpent au commencement
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    // variable qui sert à positionner le serpent au début
    private int begin = 0;

    private ImageIcon foodImg;

    // position random du fruit dans le jeu
    private int foodX = randomXFood();
    private int foodY = randomYFood();

    // booléen si le serpent est mort
    private boolean dead = false;

    // score du serpent
    private int score = 0;
    private JFrame fen;

    FenetreMenu fenetreMenu;


    public Gameplay(JFrame fen,FenetreMenu fenetreMenu) {
        this.fen=fen;
        this.fenetreMenu = fenetreMenu;
    }

    public void setControlSnake(ControlSnake controlSnake){
        this.addKeyListener(controlSnake);
    }

    public void paintComponent(Graphics g){
        //requestFocus();
        if(dead){
            g.setColor(blue);
            g.fillRect(390,235,500,250);
            repaint();
            revalidate();
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            g.drawString("GAME OVER ",500,300);
            g.drawString("Scores: "+score,500,350);
            // Dessine la taille du serpent
            g.drawString("Taille: "+taille,500,400);
            g.setFont(new Font("Monospaced", Font.BOLD, 20));
        }
        else{
            // positionne le snake au commencement
            if(begin == 0){
                snake[2][0]=300;
                snake[1][0]=320;
                snake[0][0]=340;

                snake[2][1]=20;
                snake[1][1]=20;
                snake[0][1]=20;
            }
            // Dessine les deux bandes bleu sur les côtés
            g.setColor(blue);
            g.fillRect(0,0,280,720);
            g.fillRect(1000,0,280,720);


            // Dessine le milieu du jeu
            g.setColor(green);
            g.fillRect(280,0,720,720);

            // Dessine le score
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 18));
            g.drawString("Scores: "+score,160,20);

            // Dessine la taille du serpent
            g.drawString("Taille: "+taille,1020,20);

            // le snake regarde à droite de base
            rightHead = new ImageIcon("img/snake/basicGreenHeadRight.png");
            rightHead.paintIcon(this,g,snake[0][0],snake[0][1]);

            for(int i = 0; i < taille; i++){
                // si la tete va vers la droite
                if(i==0 && right){
                    rightHead = new ImageIcon("img/snake/basicGreenHeadRight.png");
                    rightHead.paintIcon(this,g,snake[i][0],snake[i][1]);
                }
                // si la tete va vers la gauche
                if(i==0 && left){
                    leftHead = new ImageIcon("img/snake/basicGreenHeadLeft.png");
                    leftHead.paintIcon(this,g,snake[i][0],snake[i][1]);
                }
                // si la tete va vers le bas
                if(i==0 && down){
                    downHead = new ImageIcon("img/snake/basicGreenHeadDown.png");
                    downHead.paintIcon(this,g,snake[i][0],snake[i][1]);
                }
                // si la tete va vers le haut
                if(i==0 && up){
                    upHead = new ImageIcon("img/snake/basicGreenHeadUp.png");
                    upHead.paintIcon(this,g,snake[i][0],snake[i][1]);
                }
                // si c'est un corp
                if(i != 0){
                    body = new ImageIcon("img/snake/basicGreenBody.png");
                    body.paintIcon(this,g,snake[i][0],snake[i][1]);
                }
            }
            // on affecte l'image à food
            foodImg = new ImageIcon("img/snake/pomme.png");
            // si le fruit se trouve aux même endroit que la tete du snake
            if((foodX == snake[0][0]) && (foodY == snake[0][1])){
                while (foodIsOnSnake(foodX,foodY)){
                    // tant que le fruit apparait sur le serpent, genere une nouvelle position
                    foodX = randomXFood();
                    foodY = randomYFood();
                }
                score = score + 10;
                taille++;
                // augmente la vitesse
                delay--;
            }

            // affiche le fruit à l'endroit voulu
            foodImg.paintIcon(this,g,foodX,foodY);
            g.dispose();
        }
    }

    public boolean isDead() {
        return dead;
    }

    /*
    Renvoie un entier random entre les bornes de la largeur de la fenetre
     */
    public int randomXFood(){
        int random = (int)(Math.random()*((940-280)+1))+280;
        while(random%20 !=0){
            random = (int)(Math.random()*((940-280)+1))+280;
        }
        return random;
    }

    /*
    Renvoie un entier random entre les bornes de la hauteur de la fenetre
     */
    public int randomYFood(){
        int random = (int) (Math.random() * ((660) + 1));
        while(random % 20 != 0){
            random = (int) (Math.random() * ((660) + 1));
        }
        return random;
    }
    // renvoie true si le fruit se trouve sur une partie du serpent
    public boolean foodIsOnSnake(int x, int y ){
        for(int i = 0; i < taille;i++){
            if((x == snake[i][0]) && (y == snake[i][1])){
                return true;
            }
        }
        return false;
    }


    //les getters
    public JFrame getFen() { return fen; }

    public Timer getTimer() { return timer; }

    public boolean isRight() { return right; }

    public boolean isLeft() { return left; }

    public boolean isUp() { return up; }

    public boolean isDown() { return down; }

    public int[][] getSnake() { return snake; }

    public int getScore() { return score; }

    public int getTaille() { return taille; }

    public int getBegin() { return begin; }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setSnake(int[][] snake) {
        this.snake = snake;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDelay() {
        return delay;
    }

    public FenetreMenu getFenetreMenu() {
        return fenetreMenu;
    }
}

