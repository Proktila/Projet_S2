import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

    private Fruit firstFruit = new Fruit();

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

        initFruit();
        if(model.getMode() != "labyrinthe"){
            initWall();
        }else{
            // créer les murs manuellement
        }
    }

    public void initWall() {
        model.getListeWall().clear();
        int max=0;
        switch(model.getDifficulty()){
            case "easy":
                max=4;
                break;
            case "normal":
                max=8;
                break;
            case "hard":
                max=15;
                break;
        }
        for(int i=0; i < max; i++){
            new Wall(model,model.getJ1(),this.firstFruit);
        }
    }
    public void initFruit(){
        this.firstFruit = model.choisirFruit();
        this.firstFruit.validFruit(model.getJ1(),model);
        model.getListeFruit().add(this.firstFruit);
    }

    public void setControlSnake(ControlSnake controlSnake){
        this.addKeyListener(controlSnake);
        this.pauseBut.addActionListener(controlSnake);
    }

    
    @Override
    protected void paintComponent(Graphics g){
        int[][] snake = model.getJ1().getSnake();
        super.paintComponent(g);
        if(begin == 0){
            // positionne le snake au commencement
            snake[2][0]=0;
            snake[1][0]=20;
            snake[0][0]=40;

            snake[2][1]=20;
            snake[1][1]=20;
            snake[0][1]=20;



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
        java.util.List<Fruit> toRemove=new ArrayList<Fruit>();
        // si le serpent mange le fruit
        for(Fruit f : model.getListeFruit()){
            if((f.getPosX() == snake[0][0]) && (f.getPosY() == snake[0][1])){
                model.getJ1().setScore( model.getJ1().getScore()+10);
                model.getJ1().setTaille(model.getJ1().getTaille()+ 1);
                // augmente la vitesse
                f.effect(model.getJ1(),model);
                toRemove.add(f);
                if(model.getListeFruit().size() == 1 ){
                    Fruit newFruit;
                    newFruit = model.choisirFruit();
                    newFruit.validFruit(model.getJ1(),model);
                    model.getToAdd().add(newFruit);
                }
            }
        }
        model.getListeFruit().addAll(model.getToAdd());
        model.getListeFruit().removeAll(toRemove);
        model.getToAdd().clear();
        toRemove.clear();
        for(Fruit f : model.getListeFruit()){
            // affiche le fruit à l'endroit voulu
            f.getImgFruit().paintIcon(this,g,f.getPosX(),f.getPosY());
        }
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
        model.getJ1().setSnake(snake);

        switch (model.getMode()){
            case "traditionnel":
                createTraditionnel(g);
                break;
            case "labyrinthe":
                createLaby(g);
                break;
            case "chrono":
                createChrono(g);
                break;
            case "duo":
                createDuo(g);
                break;
        }
        for( Wall w : model.getListeWall()){
            w.getWall().paintIcon(this,g,w.getX(),w.getY());
        }
        g.dispose();
    }

    private void createTraditionnel(Graphics g){
        // jeu de base rien à coder en plus
    }
    private void createLaby(Graphics g){
        // a coder
    }
    private void createChrono(Graphics g){
        // a coder
    }
    private void createDuo(Graphics g){
        // a coder
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

    public int getBegin() { return begin; }

    public void setBegin(int begin) { this.begin = begin; }


    public FenetreMenu getFenetreMenu() { return fenetreMenu; }

    public SnakeButton getPauseBut() { return pauseBut; }

    public void setPause(boolean pause) { this.pause = pause; }

    public boolean isPause() { return pause; }

}

