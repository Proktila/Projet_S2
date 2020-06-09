import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreSnake extends JFrame {

    Gameplay gameplay;
    FenetreMenu fenetreMenu;

    public FenetreSnake(FenetreMenu fenetreMenu,Model model) {
        gameplay = new Gameplay(this,fenetreMenu,model);
        setSize(1280, 720);
        setLocation(100,0);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }
}

class Gameplay extends JPanel{

    private Model model;
    private Color blue = new Color(47, 81, 103);
    private Color green = new Color(50, 99, 23);
    private Color lightGreen = new Color(99, 205, 42);

    /*
    Tableau à double entré ou la 1ere entrée est la position d'une partie du snake par ex snake[0] = tete
    et la deuxième entré est soit la valeur x soit y ex snake[0][0] = posx de la tete
     */
    private int[][] snake = new int[720][2];

    // Les différentes partie du serpent
    private ImageIcon rightHead;
    private ImageIcon leftHead;
    private ImageIcon downHead;
    private ImageIcon upHead;
    private ImageIcon body;

    // Un timer pour la vitesse
    private Timer timer;


    // variable qui sert à positionner le serpent au début
    private int begin = 0;

    private ImageIcon foodImg;

    private JFrame fen;

    private FenetreMenu fenetreMenu;

    private boolean pause=false;
    private SnakeButton pauseBut;

    private Fruit currentFruit = new Fruit();

    private String chemin = "img/snake/";

    public Gameplay(JFrame fen,FenetreMenu fenetreMenu,Model model) {
        this.model= model;
        setLayout(null);
        this.fen=fen;
        this.fenetreMenu = fenetreMenu;
        initGameplay();
    }

    public void initGameplay(){
        JPanel panelButton = new JPanel();
        panelButton.setLayout(null);
        panelButton.setBackground(blue);
        panelButton.setBounds(0,0,280,720);
        SnakeButton pauseBut = new SnakeButton("");
        pauseBut.setIcon(new ImageIcon("img/btn/pause.png"));
        pauseBut.setRolloverIcon(new ImageIcon("img/btn/pause.png"));
        pauseBut.setPressedIcon(new ImageIcon("img/btn/pause.png"));
        pauseBut.setBounds(30,30,200,91);
        pauseBut.setFocusable(false);
        this.pauseBut=pauseBut;
        panelButton.add(pauseBut);
        JPanel panelButtonGameplay = new JPanel();
        panelButtonGameplay.setLayout(null);
        panelButtonGameplay.setBounds(0,0,1280,720);
        panelButtonGameplay.add(panelButton);
        this.setBounds(280,0,1000,720);

        panelButtonGameplay.add(this);
        panelButtonGameplay.setFocusable(true);
        panelButtonGameplay.setFocusTraversalKeysEnabled(false);
        fen.setContentPane(panelButtonGameplay);
    }

    public void setControlSnake(ControlSnake controlSnake){
        this.addKeyListener(controlSnake);
        this.pauseBut.addActionListener(controlSnake);
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        if(begin == 0){
            // positionne le snake au commencement
            snake[2][0]=0;
            snake[1][0]=20;
            snake[0][0]=40;

            snake[2][1]=20;
            snake[1][1]=20;
            snake[0][1]=20;

            this.currentFruit = new Fruit();
            this.currentFruit = model.choisirFruit();
            this.currentFruit.validFruit(model.getJ1().getSnake(),model.getJ1().getTaille());
            System.out.println(currentFruit);

        }
        // Dessine les deux bandes bleu sur les côtés
        g.setColor(blue);
        g.fillRect(720,0,280,720);

        // Dessine le milieu du jeu
        g.setColor(setMapColor(model.getMap()));
        g.fillRect(0,0,720,720);

        // Dessine le score
        g.setColor(lightGreen);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString("Scores: "+model.getJ1().getScore(),740,20);

        // Dessine la taile du serpent
        g.drawString("Taille: "+model.getJ1().getTaille(),740,40);


        // le snake regarde à droite de base
        rightHead = model.getJ1().getRightHead();
        rightHead.paintIcon(this,g,snake[0][0],snake[0][1]);

        for(int i = 0; i < model.getJ1().getTaille(); i++){
            // si la tete va vers la droite
            if(i==0 && model.getJ1().isRight()){
                rightHead = model.getJ1().getRightHead();
                rightHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers la gauche
            if(i==0 && model.getJ1().isLeft()){
                leftHead = model.getJ1().getLeftHead();
                leftHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers le bas
            if(i==0 && model.getJ1().isDown()){
                downHead = model.getJ1().getDownHead();
                downHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers le haut
            if(i==0 && model.getJ1().isUp()){
                upHead = model.getJ1().getUpHead();
                upHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si c'est un corp
            if(i != 0){
                body = model.getJ1().getBody();
                body.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
        }
        // on affecte l'image à food

        // si le fruit se trouve aux même endroit que la tete du snake
        if((currentFruit.getPosX() == snake[0][0]) && (currentFruit.getPosY() == snake[0][1])){
            model.getJ1().setScore( model.getJ1().getScore()+10);
            // augmente la vitesse
            currentFruit.effect(model.getJ1());
            this.currentFruit = model.choisirFruit();
            this.currentFruit.validFruit(model.getJ1().getSnake(),model.getJ1().getTaille());
        }
        this.currentFruit.getImgFruit().paintIcon(this,g,currentFruit.getPosX(),currentFruit.getPosY());
        // affiche le fruit à l'endroit voulu
        if(model.getJ1().isDead()){
            g.setColor(blue);
            g.fillRect(120,235,500,250);
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            g.drawString("GAME OVER ",230,300);
            g.drawString("Scores: "+model.getJ1().getScore(),230,350);
            // Dessine la taile du serpent
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
        }
        if(pause){
            g.setColor(blue);
            g.fillRect(120,235,500,250);
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            // Dessine la taile du serpent
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
        }


        g.dispose();

    }

    private Color setMapColor(String map) {
        switch (map) {
            case "Rouge":
                return Color.RED;
            case "Bleu":
                return Color.BLUE;
            default:
                return this.green;
        }
    }

    //les getters
    public JFrame getFen() { return fen; }

    public Timer getTimer() { return timer; }

    public int[][] getSnake() { return snake; }

    public int getBegin() { return begin; }


    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void setSnake(int[][] snake) {
        this.snake = snake;
    }

    public FenetreMenu getFenetreMenu() {
        return fenetreMenu;
    }

    public SnakeButton getPauseBut() { return pauseBut; }

    public void setPause(boolean pause) { this.pause = pause; }

    public boolean isPause() { return pause; }

}

