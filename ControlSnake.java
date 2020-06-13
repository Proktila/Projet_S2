import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;


public class ControlSnake implements KeyListener, ActionListener {
    private final Gameplay gameplay;
    private final Model model;
    private boolean deathSoundPlayed;
    private int first = 0;

    Timer timer;


    /**
     * @param fenetreSnake le jeu
     * @param model le model
     * initalise le listener
     */
    public ControlSnake(FenetreSnake fenetreSnake, Model model){
        this.model = model;
        this.deathSoundPlayed = false;
        this.gameplay = fenetreSnake.getGameplay();
        gameplay.setFocusable(true);
        gameplay.setFocusTraversalKeysEnabled(false);
        gameplay.requestFocusInWindow();
        gameplay.setControlSnake(this);
        this.timer = new Timer(model.getJ1().getDelay(), this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * @param e
     * on appuie sur une touche
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // la première fois que l'on appuie sur une touche cela lance le chrono pour le mode chrono
        if(first == 0){
            model.setChronoDifficulty();
            first = 1;
        }
        // si il n'y a plus de temps, le serpent meurt
        if(model.getChrono().getTime() <= 0){
            model.getJ1().setDead(true);
            gameplay.repaint();
        }
        // touches pour le j1
        // tourne à droite
        if(e.getKeyCode() == KeyEvent.VK_D){
            // des qu'on bouge begin augmentera et ne repositionnera plus le serpent
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ1().setRight(true);
            // pour ne pas avoir les diagonales
            if(!model.getJ1().isLeft()){
                model.getJ1().setRight(true);
            }
            else{
                model.getJ1().setRight(false);
                model.getJ1().setLeft(true);
            }
            model.getJ1().setDown(false);
            model.getJ1().setUp(false);
        }
        // tourne à gauche
        if(e.getKeyCode() == KeyEvent.VK_Q){
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ1().setLeft(true);
            if(!model.getJ1().isRight()){
                model.getJ1().setLeft(true);
            }
            else{
                model.getJ1().setLeft(false);
                model.getJ1().setRight(true);
            }
            model.getJ1().setDown(false);
            model.getJ1().setUp(false);
        }
        // tourne en bas
        if(e.getKeyCode() == KeyEvent.VK_S){
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ1().setDown(true);
            if(!model.getJ1().isUp()){
                model.getJ1().setDown(true);
            }
            else{
                model.getJ1().setDown(false);
                model.getJ1().setUp(true);
            }
            model.getJ1().setRight(false);
            model.getJ1().setLeft(false);
        }
        // tourne en haut
        if(e.getKeyCode() == KeyEvent.VK_Z){
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ1().setUp(true);
            if(!model.getJ1().isDown()){
                model.getJ1().setUp(true);
            }
            else{
                model.getJ1().setUp(false);
                model.getJ1().setDown(true);
            }
            model.getJ1().setRight(false);
            model.getJ1().setLeft(false);
        }
        // touches pour le j2
        // tourne à droite
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            // des qu'on bouge begin augmentera et ne repositionnera plus le serpent
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ2().setRight(true);
            // pour ne pas avoir les diagonales
            if(!model.getJ2().isLeft()){
                model.getJ2().setRight(true);
            }
            else{
                model.getJ2().setRight(false);
                model.getJ2().setLeft(true);
            }
            model.getJ2().setDown(false);
            model.getJ2().setUp(false);
        }
        // tourne à gauche
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ2().setLeft(true);
            if(!model.getJ2().isRight()){
                model.getJ2().setLeft(true);
            }
            else{
                model.getJ2().setLeft(false);
                model.getJ2().setRight(true);
            }
            model.getJ2().setDown(false);
            model.getJ2().setUp(false);
        }
        // tourne en bas
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ2().setDown(true);
            if(!model.getJ2().isUp()){
                model.getJ2().setDown(true);
            }
            else{
                model.getJ2().setDown(false);
                model.getJ2().setUp(true);
            }
            model.getJ2().setRight(false);
            model.getJ2().setLeft(false);
        }
        // tourne en haut
        if(e.getKeyCode() == KeyEvent.VK_UP){
            gameplay.setBegin(gameplay.getBegin()+1);
            model.getJ2().setUp(true);
            if(!model.getJ2().isDown()){
                model.getJ2().setUp(true);
            }
            else{
                model.getJ2().setUp(false);
                model.getJ2().setDown(true);
            }
            model.getJ2().setRight(false);
            model.getJ2().setLeft(false);
        }
        // si lorsque qu'on appue sur espace et que le serpent est mort ou si on est en mode duo l'autre serpent est mort
        // relance le jeu
        if(e.getKeyCode() == KeyEvent.VK_SPACE && (model.getJ1().isDead() || (model.getMode().equals("duo") && model.getJ2().isDead()))) {

            // réinitialise la valeur first pour le chrono
            first = 0;

            // enregistre le score du joueur 1 si on n'est pas en mode duo
            model.getScore().setActualScore(model.getJ1().getScore());
            String[][] tScore = gameplay.getFenetreMenu().getData();
            if(!model.getMode().equals("duo")) {
                try {
                    addScoreInTableMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                gameplay.getFenetreMenu().setData(tScore);
            }
            // reinitalise les serpents
            reset(model.getJ1());
            reset(model.getJ2());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @param e
     * lorsqu'il se passe quelque chose (touches ou bouton)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // on lance le timer
        this.timer.start();
        // fais avancer le serpent de 20px et les font mourir si ils dépassent les bords
        avance(model.getJ1());
        avance(model.getJ2());

        // met le jeu en pause
        if(e.getSource().equals(gameplay.getPauseBut())){
            gameplay.setPause(!gameplay.isPause());
        }
        // retourne sur le menu principal
        if(e.getSource().equals(gameplay.getHomeBut())){
            // arrête le timer
            this.timer.stop();
            // réinitialise les valeurs utilisé
            gameplay.setBegin(0);
            model.getScore().setActualScore(0);
            gameplay.repaint();
            // ferme le jeu
            gameplay.getFen().dispose();
            // afichhe le menu
            gameplay.getFenetreMenu().setVisible(true);
            gameplay.getFenetreMenu().changerMenuPrincipal();
            // recréer un snake avec le même skin
            Snake s = new Snake(model);
            s.skins(model.getJ1().getSkin());
            model.setJ1(s);
            // clear lest différentes liste
            model.getListeWall().clear();
            model.getListeFruit().clear();
            model.getListeObjetsLaby().clear();
        }

        // bruit mort
        if((model.getJ1().isDead() || (model.getJ2().isDead() && model.getMode().equals("duo")))  && !deathSoundPlayed){
            try {
                Sound.playSound("sound/death.wav", model.getVolumeBruits());
                deathSoundPlayed = true;
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
        // si on est en mode duo tue le serpent qui mange l'autre
        if(model.getMode().equals("duo")){
            for (int i=0;i< model.getJ1().getTaille();i++){
                if(model.getJ2().getSnake()[0][0] == model.getJ1().getSnake()[i][0] && model.getJ2().getSnake()[0][1] == model.getJ1().getSnake()[i][1]){
                    model.getJ2().setDead(true);
                }
            }
            for (int i=0;i< model.getJ2().getTaille();i++){
                if(model.getJ1().getSnake()[0][0] == model.getJ2().getSnake()[i][0] && model.getJ1().getSnake()[0][1] == model.getJ2().getSnake()[i][1]){
                    model.getJ1().setDead(true);
                }
            }
        }
    }

    /**
     * @throws IOException
     * ajoute le score actuelle dans le tableau de score
     */
    public void addScoreInTableMenu() throws IOException {
        try {
            model.getScore().setActualPseudo(String.valueOf(gameplay.getFenetreMenu().getTfPseudo().getText()));
        } catch (PseudoOutOfBoundsException | SansPseudoException e) {
            e.printStackTrace();
        }
        // récup score, seudo, mode, dfficulté
        model.getScore().initList();
        model.getScore().addScore();
        model.getScore().addScoreInFich();
    }

    /**
     * @param snake
     * remet zero le serpent et le jeu pour rejouer danns un environnement différent
     */
    public void reset(Snake snake){
        model.getListeFruit().clear();
        deathSoundPlayed = false;
        snake.setDelay(100);
        if(model.getMode().equals("labyrinthe")){
            model.getListeObjetsLaby().clear();
            gameplay.createLaby(gameplay.getGrid());
        }
        else gameplay.initWall();
        gameplay.initFruit();
        gameplay.setBegin(0);
        Snake s = new Snake(model);
        s.skins(model.getJ1().getSkin());
        model.setJ1(s);
        gameplay.repaint();
        gameplay.revalidate();
        gameplay.initSnake();
    }

    /**
     * @param s
     * fais avancer le serpent en fonction de sa direction
     * ne le fais pas bouger lorsqu'il est mort/pause/paralysé
     * et le fais mourir quand il le faut
     */
    public void avance(Snake s){
        int[][] snake = s.getSnake();

        // si on va a droite
        // si il n'est pas mort,parralysé et que le jeu n'est pas en pause
        if(s.isRight() && !s.isDead() && !gameplay.isPause() && !s.isParalysed() ){
            for (int i = s.getTaille()-1; i >=0;i--){
                if (snake[0][0] >= 700) {
                    s.setDead(true);
                    break;
                }
                // met le corp à la même hauteur que la tête
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    // avance la tete de la taille des images
                    snake[i][0] = snake[i][0] + 20;
                }else{
                    // avance le corp autant que la tête
                    snake[i][0] = snake[i-1][0];
                }
            }
            s.setSnake(snake);
            this.timer.setDelay(s.getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        // même chause que pour la droite
        if(s.isLeft() && !s.isDead() && !gameplay.isPause() && !s.isParalysed()){
            for (int i = s.getTaille()-1; i >=0;i--){
                if (snake[0][0] <= 0) {
                    s.setDead(true);
                    break;
                }
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    snake[i][0] = snake[i][0] - 20;
                }else{
                    snake[i][0] = snake[i-1][0];
                }
            }
            s.setSnake(snake);
            this.timer.setDelay(s.getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        // même chause que pour la droite
        if(s.isDown() && !s.isDead() && !gameplay.isPause() && !s.isParalysed()){
            for (int i = s.getTaille()-1; i >=0;i--){
                if (snake[0][1] >= 700) {
                    s.setDead(true);
                    break;
                }
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] + 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
            }
            s.setSnake(snake);
            this.timer.setDelay(s.getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        // même chause que pour la droite
        if(s.isUp() && !s.isDead() && !gameplay.isPause() && !s.isParalysed()){
            for (int i = s.getTaille()-1; i >=0;i--){
                if (snake[0][1] <= 0) {
                    s.setDead(true);
                    break;
                }
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] - 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
            }
            s.setSnake(snake);
            this.timer.setDelay(s.getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        for(int i = 0;i < s.getTaille();i++){
            // si le snake se mord son corp
            if((snake[0][0] == snake[i][0]) && (snake[0][1] == snake[i][1]) && (i != 0)){
                s.setDead(true);
                break;
            }
        }
        for(Wall w : model.getListeWall()){
            // si le serpent mange un mur
            if(snake[0][0] == w.getX() && snake[0][1] == w.getY()){
                s.setDead(true);
                break;
            }
        }
    }

}
