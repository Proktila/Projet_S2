import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FenetreSnake extends JFrame {

    Gameplay gameplay;

    /**
     * @param fenetreMenu
     * @param model
     * crée une fenêtre
     */
    public FenetreSnake(FenetreMenu fenetreMenu,Model model) {
        gameplay = new Gameplay(this,fenetreMenu,model);
        setSize(1280, 759);
        setLocation(100,0);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sn'hack");
        Image icone =Toolkit.getDefaultToolkit().getImage("img/icone.png");
        this.setIconImage(icone);
    }

    public Gameplay getGameplay() { return gameplay; }
}

class Gameplay extends JPanel{
    private final Model model;

    // variable qui sert à positionner le serpent au début
    private int begin = 0;

    private final JFrame fen;
    private final FenetreMenu fenetreMenu;

    private boolean pause=false;
    private SnakeButton pauseBut;
    private SnakeButton homeBut;

    private Fruit firstFruit = new Fruit(); // le premier fruit a apparaitre
    private String[][] grid; // contient le labyrinthe

    private ImageIcon bg; // fond d'écran


    /**
     * @param fen
     * @param fenetreMenu
     * @param model
     * créer le panel
     */
    public Gameplay(JFrame fen,FenetreMenu fenetreMenu,Model model) {
        this.model= model;
        setLayout(null);
        this.fen=fen;
        this.fenetreMenu = fenetreMenu;
        initGameplay();
    }

    public void initGameplay(){
        // le panel qui contien les boutons
        JPanel panelButton = new JPanel();
        panelButton.setLayout(null);
        panelButton.setBackground(model.getBLUE());
        panelButton.setBounds(0,0,280,720);

        //créeation du bouton pause
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

        // le panel qui contient le gameplay et le panel des boutons
        JPanel panelButtonGameplay = new JPanel();
        panelButtonGameplay.setLayout(null);
        panelButtonGameplay.setBounds(0,0,1280,720);
        panelButtonGameplay.add(panelButton);
        this.setBounds(280,0,1000,720);
        panelButtonGameplay.add(this);
        panelButtonGameplay.setFocusable(true);
        panelButtonGameplay.setFocusTraversalKeysEnabled(false);

        // on affiche le panel
        fen.setContentPane(panelButtonGameplay);

        // tous les modes sauf labyrinthe créer aleatoirement les murs
        if(!model.getMode().equals("labyrinthe")){
            initWall();
        }else{
            // va lire les fichiers contenant les maps et va en créer une en fonction de la difficulté
            grid = MapTools.readGrid(MapTools.getRandomMap(model.getDifficulty()));
            createLaby(grid);
        }
        // positionne le snake à sa postion de base
        initSnake();
        // ajoute le premier fruit à la liste defruit
        initFruit();
        if (model.getMode().equals("chrono")){
            // initalise le chrono à la bonne valeur
            model.setChronoDifficulty();
        }
    }

    /**
     * remet la liste de mur à zero
     * créer des murs en fonction de la difficulté
     */
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
            // créer un mur a un emplacement valide
            new Wall(model,model.getJ1(),this.firstFruit);
        }
    }

    /**
     * remplie les listes listeWall et listeObjetsLaby du model a partir de grid
     * @param grid tableau 36*36 content les objets du labyrinthe
     */
    public void createLaby(String[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                String cell = grid[y][x];
                if (cell.equals("X")) model.getListeWall().add(new Wall(model, x*20, y*20)); // ajout mur
                if (cell.equals("K")) model.getListeObjetsLaby().add(new Objet("key", x*20, y*20));  // ajout cle
                if (cell.equals("C")) model.getListeObjetsLaby().add(new Objet("coin", x*20, y*20));  // ajout piece
            }
        }
    }


    /**
     * positionne le snake à la position de base
     */
    public void initSnake() {
        if(begin == 0){
            int[][] snake = model.getJ1().getSnake();
            snake[2][0]=0;
            snake[1][0]=20;
            snake[0][0]=40;

            snake[2][1]=20;
            snake[1][1]=20;
            snake[0][1]=20;
        }
    }


    /**
     * créer le premier fruit
     */
    public void initFruit(){
        // renvoi un fruit random
        this.firstFruit = model.choisirFruit();
        // selectionne une position valide
        this.firstFruit.validFruit(model.getJ1(),model);
        // ajoute le fruit à la liste de fruit
        model.getListeFruit().add(this.firstFruit);
    }

    /**
     * @param controlSnake
     * lient les objets au listener
     */
    public void setControlSnake(ControlSnake controlSnake){
        this.addKeyListener(controlSnake);
        this.pauseBut.addActionListener(controlSnake);
        this.homeBut.addActionListener(controlSnake);
    }


    /**
     * @param g
     * paint le jeu sur le Graphics j
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // récupere le corp du serpent
        int[][] snake = model.getJ1().getSnake();

        // Dessine la bande bleu sur le côtés
        g.setColor(model.getBLUE());
        g.fillRect(720,0,280,720);

        // Dessine le milieu du jeu
        setTheme();
        bg.paintIcon(this,g,0,0);

        if(!model.getMode().equals("duo")) {
            // Dessine effet et type
            showInfoFruit(g, 160);
        }

        // affiche le corp du serpent et sa tete
        showSnake(snake,g,model.getJ1());

        // dessine le 2eme serpent si on est en mode duo
        if(model.getMode().equals("duo")){
            createDuo(g);
        }

        // collisions avec un fruit ou un objet du labyrinthe
        eatSnake(snake,g,model.getJ1());
        if(model.getMode().equals("labyrinthe")) collectCoin(snake,g,model.getJ1());

        // affichage des murs et objets du labyrinthe
        for( Wall w : model.getListeWall()){
            w.getWall().paintIcon(this,g,w.getX(),w.getY());
        }
        if(model.getMode().equals("labyrinthe")){
            for(Objet obj : model.getListeObjetsLaby()){
                obj.getImg().paintIcon(this,g,obj.getX(),obj.getY());
            }
        }

        // affiche des morts différentes en fonction du mode de jeu
        switch (model.getMode()){
            case "traditionnel":
                deadTraditionnel(g);
                break;
            case "labyrinthe":
                deadLaby(g);
                break;
            case "chrono":
                deadChrono(g);
                break;
            case "duo":
                deadDuo(g);
                break;
        }
        // affiche la pause
        if(pause){
            g.setColor(model.getBLUE());
            g.fillRect(120,235,500,250);
            g.setColor(model.getLIGHT_GREEN());
            g.setFont(model.getFONT_50());
            // Dessine la taille du serpent
            g.drawString("PAUSE",300,300);
            g.setFont(new Font("Monospaced", Font.BOLD, 25));
            g.drawString("Rappuyer sur le bouton pause",165,400);
        }
        // supprime le graphique actuelle
        g.dispose();
    }

    private void createDuo(Graphics g){
        // récupere le corp du 2eme snake
        int[][] snake = model.getJ2().getSnake();
        if(begin == 0){
            snake[2][0]=700;
            snake[1][0]=680;
            snake[0][0]=660;

            snake[2][1]=20;
            snake[1][1]=20;
            snake[0][1]=20;
        }
        // affichage personallisé pour les 2 joueurs
        g.setColor(model.getLIGHT_GREEN());
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

    /**
     * Definit le theme du jeu
     */
    private void setTheme() {
        String map = model.getTheme();
        bg = new ImageIcon("maps/theme/map"+map+".png");
    }


    /**
     * @param snake
     * @param g
     * @param s
     * affiche les parties du serpent à leur position
     */
    public void showSnake(int[][] snake,Graphics g,Snake s){

        // Les différentes partie du serpent
        ImageIcon rightHead;
        ImageIcon leftHead;
        if(s == model.getJ2()){
            leftHead = s.getLeftHead();
            leftHead.paintIcon(this,g,snake[0][0],snake[0][1]);
        }else{
            rightHead = s.getRightHead();
            rightHead.paintIcon(this,g,snake[0][0],snake[0][1]);
        }

        // i=0 correspond à la tête

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
                ImageIcon downHead = s.getDownHead();
                downHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si la tete va vers le haut
            if(i==0 && s.isUp()){
                ImageIcon upHead = s.getUpHead();
                upHead.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
            // si c'est un corp
            if(i != 0){
                ImageIcon body = s.getBody();
                body.paintIcon(this,g,snake[i][0],snake[i][1]);
            }
        }
    }

    /**
     * @param snake
     * @param g
     * @param s
     * vérifie si le serpent mange le fruit,
     * applique les effets du fruit
     * supprime le fruit
     * recrée un fruit
     */
    public void eatSnake(int[][] snake,Graphics g, Snake s){
        // liste qui contiendra les fruits a enlever
        java.util.List<Fruit> toRemove= new ArrayList<>();
        // si le serpent mange le fruit
        for(Fruit f : model.getListeFruit()){
            // si le fruit est au même endroit que la tête du serpent
            if((f.getPosX() == snake[0][0]) && (f.getPosY() == snake[0][1])){
                // augmente le score du serpent avec la difficulté
                s.setScore( s.getScore() + (10 * model.getMultiplicateur()));
                s.setTaille(s.getTaille() + 1);
                // augmente la vitesse du serpent avec la difficulté
                s.setDelay(s.getDelay() - model.getMultiplicateur());
                // applique l'effet du fruit
                f.effect(s,model);
                // ajoute le mauvais fruit à la liste
                toRemove.add(f);
                // si il n'y a qu'un fruit on en recrée un autre
                if(model.getListeFruit().size() == 1 ){
                    Fruit newFruit;
                    newFruit = model.choisirFruit();
                    newFruit.validFruit(s,model);
                    // ajoute le fruit a jouté dans une liste de fruit tampon
                    model.getToAdd().add(newFruit);
                }

                // Bruit manger
                try {
                    Sound.playSound("sound/eat.wav", model.getVolumeBruits());
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                    fenetreMenu.creerDialogErr(e.getMessage());
                }
            }
        }
        // ajoute la liste tampon a la liste de fruit
        model.getListeFruit().addAll(model.getToAdd());
        // enlevela liste tampon a la liste de fruit
        model.getListeFruit().removeAll(toRemove);
        // clear les liste tampon
        model.getToAdd().clear();
        toRemove.clear();
        for(Fruit f : model.getListeFruit()){
            // affiche le fruit à l'endroit voulu
            f.getImgFruit().paintIcon(this,g,f.getPosX(),f.getPosY());
        }

    }

    /**
     * @param snake
     * @param g
     * @param s
     * la même fonction que eatSnake mais avec des coins
     */
    public void collectCoin(int[][] snake,Graphics g, Snake s) {
        java.util.List<Objet> toRemove=new ArrayList<Objet>();
        // si le serpent mange le Objet
        for(Objet obj : model.getListeObjetsLaby()){
            if((obj.getX() == snake[0][0]) && (obj.getY() == snake[0][1])){
                obj.effect(s);
                toRemove.add(obj);

                // Bruit objet
                try {
                    Sound.playSound("sound/coin.wav", model.getVolumeBruits());
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                    fenetreMenu.creerDialogErr(e.getMessage());
                }
            }
        }
        model.getListeObjetsLaby().removeAll(toRemove);
        toRemove.clear();
        s.setSnake(snake);
    }

    /**
     * @param g
     * affiche la mort normal si le serpent est mort
     */
    public void deadTraditionnel(Graphics g){
        if(model.getJ1().isDead()){
            g.setColor(model.getBLUE());
            g.fillRect(120,225,500,250);
            g.setColor(model.getLIGHT_GREEN());
            g.setFont(model.getFONT_50());
            g.drawString("GAME OVER ",230,300);
            g.drawString("Scores: "+model.getJ1().getScore(),230,350);
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
            g.setFont(model.getFONT_24());
            g.drawString("Appuyer sur espace pour rejouer",150,450);
        }
    }

    /**
     * @param g
     * affiche la mort du serpent si il est mort en mode chrono
     */
    public void deadChrono(Graphics g){
        if(model.getJ1().isDead()){
            g.setColor(model.getBLUE());
            g.fillRect(120,225,500,250);
            g.setColor(model.getLIGHT_GREEN());
            g.setFont(model.getFONT_50());
            g.drawString("TIME OVER",230,300);
            g.drawString("Scores: "+model.getJ1().getScore(),230,350);
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
            g.setFont(model.getFONT_24());
            g.drawString("Appuyer sur espace pour rejouer",150,450);
        }
    }

    /**
     * @param g
     * affiche la mort du serpent si il est mort en mode labyrinthe
     * ou si il a gagné
     */
    public void deadLaby(Graphics g){
        if( model.getJ1().isWinLaby()){
            g.setColor(model.getBLUE());
            g.fillRect(120,225,500,250);
            g.setColor(model.getLIGHT_GREEN());
            g.setFont(model.getFONT_50());
            g.drawString("Win! ",230,300);
            g.drawString("Scores: "+model.getJ1().getScore(),230,350);
            // Dessine la taile du serpent
            g.drawString("Taille: "+model.getJ1().getTaille(),230,400);
            g.setFont(model.getFONT_24());
            g.drawString("Appuyer sur espace pour rejouer",150,450);
            model.getJ1().setWinLaby(false);
        }
        else if(model.getJ1().isDead()) deadTraditionnel(g);
    }

    /**
     * @param g
     * affiche le gagnant des deux serpents
     */
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
            g.setColor(model.getBLUE());
            g.fillRect(120,225,500,275);
            g.setColor(model.getLIGHT_GREEN());
            g.setFont(model.getFONT_50());
            g.drawString("GAME OVER ",230,300);
            g.setFont(model.getFONT_38());
            g.drawString(s + " a gagné",250,350);
            // Dessine la taile du serpent
            g.drawString("avec ",325,400);
            g.drawString(best.getScore() + " points",280,430);
            g.setFont(model.getFONT_24());
            g.drawString("Appuyer sur espace pour rejouer",150,475);
        }
    }

    /**
     * @param g
     * @param k
     * @param i
     * affiche le fruit actuellement à l'écran
     */
    public void showInfoFruitStyle(Graphics g, int k, int i){
        g.setColor(model.getLIGHT_BLUE());
        g.drawString("Type Fruit/Légume: ", 740, k);
        g.setColor(new Color(153, 241, 188));
        g.drawString("" + model.getListeFruit().get(i).getTypeFruit(), 740, k+20);
        g.setColor(model.getLIGHT_BLUE());
        g.drawString("Effet: ", 740, k+60);
        g.setColor(new Color(225, 245, 228));
        g.drawString("" + model.getListeFruit().get(i).getEffet(), 740, k+80);
    }

    /**
     * @param g
     * @param k
     * affiche le ou les fruits à l'écran
     */
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

    /**
     * @param g
     * affiche le score et la taille du serpent
     * sur la droite de l'écran
     */
    public void showScoreIndiv(Graphics g){
        g.setColor(model.getLIGHT_GREEN());
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        if(model.getMode().equals("chrono")){
            g.drawString("Temps " + model.getChrono().getTime(), 740, 40);
        }
        g.drawString("Score: " + model.getJ1().getScore(), 740, 60);
        g.drawString("Taille: " + model.getJ1().getTaille(), 740, 80);

    }
    public SnakeButton getHomeBut() { return homeBut; }
    public String[][] getGrid() { return grid; }
    public JFrame getFen() { return fen; }
    public int getBegin() { return begin; }
    public FenetreMenu getFenetreMenu() { return fenetreMenu; }
    public SnakeButton getPauseBut() { return pauseBut; }
    public boolean isPause() { return pause; }

    public void setBegin(int begin) { this.begin = begin; }
    public void setPause(boolean pause) { this.pause = pause; }


}

