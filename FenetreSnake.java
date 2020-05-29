import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class FenetreSnake extends JFrame {


    public FenetreSnake(Model model) {
        Gameplay gameplay = new Gameplay(this,model);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setBackground(Color.darkGray);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gameplay);

    }

}

class Gameplay extends JPanel implements KeyListener, ActionListener {

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
    private Model model;

    public Gameplay(JFrame fen,Model model){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        this.fen=fen;
        this.model = model;
    }

    public void paint(Graphics g){

        if(dead){
            g.setColor(blue);
            g.fillRect(390,235,500,250);
            repaint();
            revalidate();
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            g.drawString("GAME OVER ",500,300);
            g.drawString("Scores: "+score,500,350);
            // Dessine la taile du serpent
            g.drawString("Taille: "+taille,500,400);
        }
        else{
            if(begin == 0){
                // positionne le snake au commencement
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

            // Dessine la taile du serpent
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
            foodImg = new ImageIcon("img/snake/body.png");

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
                timer.setDelay(delay);
            }
            // affiche le fruit à l'endroit voulu
            foodImg.paintIcon(this,g,foodX,foodY);

            g.dispose();

        }

    }

    /*
    Renvoie un entier random entre les bornes de la largeur de la fenetre
     */
    public int randomXFood(){
        int random = (int)(Math.random()*((940-280)+1))+280;
        while(random%20 !=0){
            random = (int)(Math.random()*((940-280)+1))+280;
        }
        System.out.println(random);
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
        System.out.println(random);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        // si on va a droite
        if(right){
            for (int i = taille-1; i >=0;i--){
                // met le corp à la même hauteur que la tête
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    // avance la tete de la taille des images
                    snake[i][0] = snake[i][0] + 20;
                }else{
                    // avance le corp autant que la tête
                    snake[i][0] = snake[i-1][0];
                }
                if(snake[i][0] > 980){
                    dead = true;
                }
            }
            // rappelle la méthode paint()
            repaint();
        }
        if(left){
            for (int i = taille-1; i >=0;i--){
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    snake[i][0] = snake[i][0] - 20;
                }else{
                    snake[i][0] = snake[i-1][0];
                }
                if(snake[i][0] < 280){
                    dead = true;
                }
            }
            repaint();
        }
        if(down){
            for (int i = taille-1; i >=0;i--){
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] + 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
                if(snake[i][1] > 660){
                    dead = true;
                }
            }
            repaint();
        }
        if(up){
            for (int i = taille-1; i >=0;i--){
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] - 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
                if(snake[i][1] < 0){
                    dead = true;
                }
            }
            repaint();
        }
        for(int i = 0;i < taille;i++){
            // si le snake se mord son corp
            if((snake[0][0] == snake[i][0]) && (snake[0][1] == snake[i][1]) && (i != 0)){
                System.out.println("PROBLEME");
                dead=true;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            // des qu'on bouge begin augmentera et ne repositionnera plus le serpent
            begin++;
            right = true;
            // pour de pas avoir les diagonales
            if(!left){
                right = true;
            }
            else{
                right = false;
                left = true;
            }
            down = false;
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            begin++;
            left = true;
            if(!right){
                left = true;
            }
            else{
                left = false;
                right = true;
            }
            down = false;
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            begin++;
            down = true;
            if(!up){
                down = true;
            }
            else{
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            begin++;
            up = true;
            if(!down){
                up = true;
            }
            else{
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && dead){
            // remise a zero lorsqu'on appuie sur espace et que l'on est mort
            begin = 0;
            score = 0;
            taille = 3;
            dead = false;
            right = false;
            left = false;
            up = false;
            down = false;
            repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE && !dead){
            // ferme le jeu et ouvre le menu
            fen.dispose();
            FenetreMenu fenMenu = new FenetreMenu(model);
            ControlBouton controlBut = new ControlBouton(fenMenu, model);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

