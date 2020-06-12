import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
    private Color lightBlue = new Color(85,220,238);


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
    private SnakeButton homeBut;

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
        // bouton pause
        SnakeButton pauseBut = new SnakeButton("");
        pauseBut.setIcon(new ImageIcon("img/btn/pause.png"));
        pauseBut.setRolloverIcon(new ImageIcon("img/btn/pause.png"));
        pauseBut.setPressedIcon(new ImageIcon("img/btn/pause.png"));
        pauseBut.setBounds(30,30,200,91);
        pauseBut.setFocusable(false);
        this.pauseBut=pauseBut;
        panelButton.add(pauseBut);
        // bouton vers menu
        SnakeButton homeBut = new SnakeButton("");
        homeBut.setIcon(new ImageIcon("img/btn/home.png"));
        homeBut.setRolloverIcon(new ImageIcon("img/btn/home.png"));
        homeBut.setPressedIcon(new ImageIcon("img/btn/home.png"));
        homeBut.setBounds(30,150,200,100);
        homeBut.setFocusable(false);
        this.homeBut=homeBut;
        panelButton.add(homeBut);
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
            createLaby(MapTools.readGrid(MapTools.getRandomMap(model.getDifficulty())));
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

    /**
     * remplie les listes listeWall et listeObjetsLaby du model a partir de grid
     * @param grid tableau 36*36 content les objets du labyrinthe
     */
    public void createLaby(String[][] grid) {
        for (int x = 0; x<grid.length; x++) {
            for (int y = 0; y<grid.length; y++) {
                String cell = grid[y][x];
                if (cell.equals("X")) model.getListeWall().add(new Wall(model, x*20, y*20)); // ajout mur
                if (cell.equals("K")) model.getListeObjetsLaby().add(new Objet("key", x*20, y*20));  // ajout cle
                if (cell.equals("C")) model.getListeObjetsLaby().add(new Objet("coin", x*20, y*20));  // ajout piece
            }
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
        this.homeBut.addActionListener(controlSnake);
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
        g.setColor(setThemeColor(model.getTheme()));
        g.fillRect(0,0,720,720);

        if(model.getMode() != "duo") {
            // Dessine effet et type
            showInfoFruit(g, 160);
        }

        showSnake(snake,g,model.getJ1());

        if(model.getMode() == "duo"){
            createDuo(g);
        }

        // collisions avec un fruit ou un objet du labyrinthe
        eatSnake(snake,g,model.getJ1());
        if(model.getMode() == "labyrinthe") collectCoin(snake,g,model.getJ1());

        // affichage des murs et objets du labyrinthe
        for( Wall w : model.getListeWall()){
            w.getWall().paintIcon(this,g,w.getX(),w.getY());
        }
        if(model.getMode() == "labyrinthe"){
            for(Objet obj : model.getListeObjetsLaby()){
                obj.getImg().paintIcon(this,g,obj.getX(),obj.getY());
            }
        }

        switch (model.getMode()){
            case "traditionnel":
                deadTraditionnel(g);
                break;
            case "labyrinthe":
                deadLaby(g);
                break;
            case "chrono":
                createChrono(g);
                break;
            case "duo":
                deadDuo(g);
                break;
        }

        if(pause){
            g.setColor(blue);
            g.fillRect(120,235,500,250);
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            // Dessine la taille du serpent
            g.drawString("PAUSE",300,300);
            g.setFont(new Font("Monospaced", Font.BOLD, 25));
            g.drawString("Rappuyer sur le bouton pause",165,400);
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
        int[][] snake = model.getJ2().getSnake();
        if(begin == 0){
            snake[2][0]=700;
            snake[1][0]=680;
            snake[0][0]=660;

            snake[2][1]=20;
            snake[1][1]=20;
            snake[0][1]=20;
        }
        g.setColor(lightGreen);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString("J1",740,40);
        showScoreIndiv(g);
        g.drawString("J2",740,120);
        g.drawString("Taille: "+model.getJ2().getTaille(),740,140);
        g.drawString("Score: "+model.getJ2().getScore(),740,160);

        showInfoFruit(g, 200);
        showSnake(snake,g,model.getJ2());
        eatSnake(snake,g,model.getJ2());
    }

    private Color setThemeColor(String map) {
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

    public void showSnake(int[][] snake,Graphics g,Snake s){

        if(s == model.getJ2()){
            leftHead = s.getLeftHead();
            leftHead.paintIcon(this,g,snake[0][0],snake[0][1]);
        }else{
            rightHead = s.getRightHead();
            rightHead.paintIcon(this,g,snake[0][0],snake[0][1]);
        }

        for(int i = 0; i < s.getTaille(); i++){
            // si la tete va vers la droite
            if(i==0 && s.isRight()){
                rightHead = s.getRightHead();
                rightHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers la gauche
            if(i==0 && s.isLeft()){
                leftHead = s.getLeftHead();
                leftHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers le bas
            if(i==0 && s.isDown()){
                downHead = s.getDownHead();
                downHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers le haut
            if(i==0 && s.isUp()){
                upHead = s.getUpHead();
                upHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si c'est un corp
            if(i != 0){
                body = s.getBody();
                body.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
        }
    }

    public void eatSnake(int[][] snake,Graphics g, Snake s){
        java.util.List<Fruit> toRemove=new ArrayList<Fruit>();
        // si le serpent mange le fruit
        for(Fruit f : model.getListeFruit()){
            if((f.getPosX() == snake[0][0]) && (f.getPosY() == snake[0][1])){
                s.setScore( s.getScore()+10);
                s.setTaille(s.getTaille()+ 1);
                // augmente la vitesse
                f.effect(s,model);
                toRemove.add(f);
                if(model.getListeFruit().size() == 1 ){
                    Fruit newFruit;
                    newFruit = model.choisirFruit();
                    newFruit.validFruit(s,model);
                    model.getToAdd().add(newFruit);
                }

                // Bruit manger
                try {
                    Sound.playSound("sound/eat.wav", model.getVolumeBruits());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                // fin bruit manger
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
        s.setSnake(snake);

    }

    public void collectCoin(int[][] snake,Graphics g, Snake s) {
        java.util.List<Objet> toRemove=new ArrayList<Objet>();
        // si le serpent mange le Objet
        for(Objet obj : model.getListeObjetsLaby()){
            if((obj.getX() == snake[0][0]) && (obj.getY() == snake[0][1])){
                obj.effect(s,model);
                toRemove.add(obj);

                // Bruit objet
                try {
                    Sound.playSound("sound/coin.wav", model.getVolumeBruits());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                // fin bruit objet
            }
        }
        model.getListeObjetsLaby().removeAll(toRemove);
        toRemove.clear();
        s.setSnake(snake);
    }

    public void deadTraditionnel(Graphics g){
        if(model.getJ1().isDead()){
            g.setColor(blue);
            g.fillRect(120,225,500,250);
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            g.drawString("GAME OVER ",230,300);
            g.drawString("Scores: "+model.getJ1().getScore(),230,350);
            // Dessine la taile du serpent
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
            g.setFont(new Font("Monospaced", Font.BOLD, 24));
            g.drawString("Appuyer sur espace pour rejouer",150,450);
        }
    }
    public void deadLaby(Graphics g){
        if(model.getJ1().isDead() && model.getJ1().isWinLaby()){
            g.setColor(blue);
            g.fillRect(120,225,500,250);
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            g.drawString("Win! ",230,300);
            g.drawString("Scores: "+model.getJ1().getScore(),230,350);
            // Dessine la taile du serpent
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
            g.setFont(new Font("Monospaced", Font.BOLD, 24));
            g.drawString("Appuyer sur espace pour rejouer",150,450);
            model.getJ1().setWinLaby(false);
        }
        else if(model.getJ1().isDead()) deadTraditionnel(g);
    }

    public void deadDuo(Graphics g){
        if(model.getJ1().isDead() || model.getJ2().isDead()){
            Snake best;
            String s;
            if(model.getJ1().getScore() > model.getJ2().getScore()){
                best = model.getJ1();
                s ="J1";
            }else{
                best = model.getJ2();
                s = "J2";
            }
            g.setColor(blue);
            g.fillRect(120,225,500,275);
            g.setColor(lightGreen);
            g.setFont(new Font("Monospaced", Font.BOLD, 50));
            g.drawString("GAME OVER ",230,300);
            g.setFont(new Font("Monospaced", Font.BOLD, 38));
            g.drawString(s + " a gagné",250,350);
            // Dessine la taile du serpent
            g.drawString("avec ",325,400);
            g.drawString(best.getScore() + " points",280,430);
            g.setFont(new Font("Monospaced", Font.BOLD, 24));
            g.drawString("Appuyer sur espace pour rejouer",150,475);
        }
    }

    public void showInfoFruitStyle(Graphics g, int k, int i){
        g.setColor(lightBlue);
        g.drawString("Type Fruit/Légume: ", 740, k);
        g.setColor(new Color(153, 241, 188));
        g.drawString("" + model.getListeFruit().get(i).getTypeFruit(), 740, k+20);
        g.setColor(lightBlue);
        g.drawString("Effet: ", 740, k+60);
        g.setColor(new Color(225, 245, 228));
        g.drawString("" + model.getListeFruit().get(i).getEffet(), 740, k+80);
    }

    public void showInfoFruit(Graphics g, int k){
        if (model.getListeFruit().size() == 1) {
            showScoreIndiv(g);
            showInfoFruitStyle(g, k, model.getListeFruit().size() - 1);
        } else {
            for (int i = 0; i < model.getListeFruit().size(); i++) {
                if (i == 0) {
                    showScoreIndiv(g);
                    showInfoFruitStyle(g, k, 0);
                }
                if (i == 1) {
                    showScoreIndiv(g);
                    showInfoFruitStyle(g, k+140, 1);
                }
                if (i == 2) {
                    showScoreIndiv(g);
                    showInfoFruitStyle(g, k+280, 2);
                }
            }
        }
    }

    public void showScoreIndiv(Graphics g){
        // Dessine le score
        g.setColor(lightGreen);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString("Score: " + model.getJ1().getScore(), 740, 60);
        // Dessine la taille du serpent
        g.drawString("Taille: " + model.getJ1().getTaille(), 740, 80);

    }
    public SnakeButton getHomeBut() { return homeBut; }
}

