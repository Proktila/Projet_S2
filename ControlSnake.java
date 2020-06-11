import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ControlSnake implements KeyListener, ActionListener {

    FenetreSnake fenetreSnake;
    Gameplay gameplay;
    Model model;
    boolean deathSoundPlayed;

    Timer timer;

    public ControlSnake(FenetreSnake fenetreSnake, Model model){
        this.model = model;
        this.fenetreSnake = fenetreSnake;
        this.deathSoundPlayed = false;
        gameplay = fenetreSnake.getGameplay();
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

    @Override
    public void keyPressed(KeyEvent e) {
        // j1
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
        // j2
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE && (model.getJ1().isDead() || (model.getMode() == "duo" && model.getJ2().isDead()))) {

            model.getScore().setActualScore(model.getJ2().getScore());

            String[][] tScore = gameplay.getFenetreMenu().getData();
            try {
                addScoreInTableMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            gameplay.getFenetreMenu().setData(tScore);
            // remise a zero lorsqu'on appuie sur espace et que l'on est mort
            reset(model.getJ1());
            reset(model.getJ2());

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timer.start();

        avance(model.getJ1());
        avance(model.getJ2());


        if(e.getSource().equals(gameplay.getPauseBut())){
            gameplay.setPause(!gameplay.isPause());
        }
        if(e.getSource().equals(gameplay.getHomeBut())){
            this.timer.stop();
            gameplay.setBegin(0);
            model.getScore().setActualScore(0);
            gameplay.repaint();
            gameplay.getFen().dispose();
            gameplay.getFenetreMenu().setVisible(true);
            gameplay.getFenetreMenu().changerMenuPrincipal();
            Snake s = new Snake(model);
            s.skins(model.getJ1().getSkin());
            model.setJ1(s);
            model.getListeWall().clear();
            model.getListeFruit().clear();
        }

        // bruit mort
        if(model.getJ1().isDead()  && !deathSoundPlayed){
            try {
                Sound.playSound("sound/death.wav", model.getVolumeBruits());
                deathSoundPlayed = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
        if(model.getMode() == "duo"){
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

            if(model.getJ2().isDead()  && !deathSoundPlayed){
                try {
                    Sound.playSound("sound/death.wav", model.getVolumeBruits());
                    deathSoundPlayed = true;
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // bruit mort
    }

    public void addScoreInTableMenu() throws IOException {
        //récup pseudo
        try {
            model.getScore().setActualPseudo(String.valueOf(gameplay.getFenetreMenu().getTfPseudo().getText()));
        } catch (PseudoOutOfBoundsException | SansPseudoException e) {
            e.printStackTrace();
        }
        model.getScore().setActualScore(model.getScore().getActualScore());
        //récup score, seudo, mode, dfficulté
        model.getScore().initList();
        model.getScore().addScore();
        model.getScore().addScoreInFich();
    }

    public void reset(Snake snake){
        deathSoundPlayed = false;
        snake.setDelay(100);
        gameplay.initWall();
        gameplay.setBegin(0);
        snake.setScore(0);
        snake.setTaille(3);
        snake.setDead(false);
        snake.setRight(false);
        snake.setLeft(false);
        snake.setUp(false);
        snake.setDown(false);
        gameplay.repaint();
        gameplay.revalidate();
    }

    public void avance(Snake s){
        int[][] snake = s.getSnake();

        // si on va a droite
        if(s.isRight() && !s.isDead() && !gameplay.isPause()){
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
        if(s.isLeft() && !s.isDead() && !gameplay.isPause()){
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
        if(s.isDown() && !s.isDead() && !gameplay.isPause()){
            for (int i = s.getTaille()-1; i >=0;i--){
                if (snake[0][1] >= 660) {
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
        if(s.isUp() && !s.isDead() && !gameplay.isPause()){
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
            if(snake[0][0] == w.getX() && snake[0][1] == w.getY()){
                s.setDead(true);
                break;
            }
        }
    }

}
