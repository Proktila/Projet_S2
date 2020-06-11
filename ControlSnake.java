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

    Timer timer;

    public ControlSnake(FenetreSnake fenetreSnake, Model model){
        this.model = model;
        this.fenetreSnake = fenetreSnake;
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
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
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
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
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
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
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
        if(e.getKeyCode() == KeyEvent.VK_UP){
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE && model.getJ1().isDead()) {


            model.getScore().setActualScore(model.getJ1().getScore());

            String[][] tScore = gameplay.getFenetreMenu().getData();
            try {
                addScoreInTableMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            gameplay.getFenetreMenu().setData(tScore);
            // remise a zero lorsqu'on appuie sur espace et que l'on est mort
            model.getJ1().setDelay(100);
            gameplay.initWall();
            gameplay.setBegin(0);
            model.getJ1().setScore(0);
            model.getJ1().setTaille(3);
            model.getJ1().setDead(false);
            model.getJ1().setRight(false);
            model.getJ1().setLeft(false);
            model.getJ1().setUp(false);
            model.getJ1().setDown(false);
            gameplay.repaint();
            gameplay.revalidate();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE && !model.getJ1().isDead()){
            // ferme le jeu et ouvre le menu
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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timer.start();
        int[][] snake = model.getJ1().getSnake();

        // si on va a droite
        if(model.getJ1().isRight() && !model.getJ1().isDead() && !gameplay.isPause()){
            for (int i = model.getJ1().getTaille()-1; i >=0;i--){
                if (snake[0][0] >= 700) {
                    model.getJ1().setDead(true);
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
            model.getJ1().setSnake(snake);
            this.timer.setDelay(model.getJ1().getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        if(model.getJ1().isLeft() && !model.getJ1().isDead() && !gameplay.isPause()){
            for (int i = model.getJ1().getTaille()-1; i >=0;i--){
                if (snake[0][0] <= 0) {
                    model.getJ1().setDead(true);
                    break;
                }
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    snake[i][0] = snake[i][0] - 20;
                }else{
                    snake[i][0] = snake[i-1][0];
                }
            }
            model.getJ1().setSnake(snake);
            this.timer.setDelay(model.getJ1().getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        if(model.getJ1().isDown() && !model.getJ1().isDead() && !gameplay.isPause()){
            for (int i = model.getJ1().getTaille()-1; i >=0;i--){
                if (snake[0][1] >= 660) {
                    model.getJ1().setDead(true);
                    break;
                }
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] + 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
            }
            model.getJ1().setSnake(snake);
            this.timer.setDelay(model.getJ1().getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        if(model.getJ1().isUp() && !model.getJ1().isDead() && !gameplay.isPause()){
            for (int i = model.getJ1().getTaille()-1; i >=0;i--){
                if (snake[0][1] <= 0) {
                    model.getJ1().setDead(true);
                    break;
                }
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] - 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
            }
            model.getJ1().setSnake(snake);
            this.timer.setDelay(model.getJ1().getDelay());
            // rappelle la méthode paint()
            gameplay.repaint();
        }
        for(int i = 0;i < model.getJ1().getTaille();i++){
            // si le snake se mord son corp
            if((snake[0][0] == snake[i][0]) && (snake[0][1] == snake[i][1]) && (i != 0)){
                model.getJ1().setDead(true);
                break;
            }
        }
        for(Wall w : model.getListeWall()){
            if(snake[0][0] == w.getX() && snake[0][1] == w.getY()){
                model.getJ1().setDead(true);
                break;
            }
        }
        if(e.getSource().equals(gameplay.getPauseBut())){
            gameplay.setPause(!gameplay.isPause());
        }
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

}
