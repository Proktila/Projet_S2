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


    public void init(){

    }

    public ControlSnake(FenetreSnake fenetreSnake, Model model){
        this.model = model;
        this.fenetreSnake = fenetreSnake;
        gameplay = fenetreSnake.getGameplay();
        gameplay.setFocusable(true);
        gameplay.setFocusTraversalKeysEnabled(false);
        gameplay.requestFocusInWindow();
        gameplay.setControlSnake(this);
        this.timer = new Timer(model.getDelay(), this);
        timer.start();

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        init();
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            // des qu'on bouge begin augmentera et ne repositionnera plus le serpent
            gameplay.setBegin(gameplay.getBegin()+1);
            gameplay.setRight(true);
            // pour ne pas avoir les diagonales
            if(!gameplay.isLeft()){
                gameplay.setRight(true);
            }
            else{
                gameplay.setRight(false);
                gameplay.setLeft(true);
            }
            gameplay.setDown(false);
            gameplay.setUp(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            gameplay.setBegin(gameplay.getBegin()+1);
            gameplay.setLeft(true);
            if(!gameplay.isRight()){
                gameplay.setLeft(true);
            }
            else{
                gameplay.setLeft(false);
                gameplay.setRight(true);
            }
            gameplay.setDown(false);
            gameplay.setUp(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            gameplay.setBegin(gameplay.getBegin()+1);
            gameplay.setDown(true);
            if(!gameplay.isUp()){
                gameplay.setDown(true);
            }
            else{
                gameplay.setDown(false);
                gameplay.setUp(true);
            }
            gameplay.setRight(false);
            gameplay.setLeft(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            gameplay.setBegin(gameplay.getBegin()+1);
            gameplay.setUp(true);
            if(!gameplay.isDown()){
                gameplay.setUp(true);
            }
            else{
                gameplay.setUp(false);
                gameplay.setDown(true);
            }
            gameplay.setRight(false);
            gameplay.setLeft(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && gameplay.isDead()) {
            String pseudoSnake = "pseudo";
            String theme = "theme";
            String[][] tScore = gameplay.getFenetreMenu().getData();
            try {
                addScore(tScore);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            model.getScore().setActualScore(model.getScoreS());
            gameplay.getFenetreMenu().setData(tScore);
            // remise a zero lorsqu'on appuie sur espace et que l'on est mort
            model.setDelay(100);
            gameplay.setBegin(0);
            model.setScoreS(0);
            model.setTaille(3);
            gameplay.setDead(false);
            gameplay.setRight(false);
            gameplay.setLeft(false);
            gameplay.setUp(false);
            gameplay.setDown(false);
            gameplay.repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE && !gameplay.isDead()){
            // ferme le jeu et ouvre le menu
            model.setDelay(100);
            gameplay.setBegin(0);
            model.setScoreS(0);
            model.setTaille(3);
            gameplay.setDead(false);
            gameplay.setRight(false);
            gameplay.setLeft(false);
            gameplay.setUp(false);
            gameplay.setDown(false);
            gameplay.repaint();
            gameplay.getFen().dispose();
            gameplay.getFenetreMenu().setVisible(true);
            gameplay.getFenetreMenu().changerMenuPrincipal();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timer.start();
        int[][] snake = gameplay.getSnake();
        // si on va a droite
        if(gameplay.isRight() && !gameplay.isDead() && !gameplay.isPause()){
            for (int i = model.getTaille()-1; i >=0;i--){
                // met le corp à la même hauteur que la tête
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    // avance la tete de la taille des images
                    snake[i][0] = snake[i][0] + 20;
                }else{
                    // avance le corp autant que la tête
                    snake[i][0] = snake[i-1][0];
                }
                if (snake[i][0] > 700) {
                    gameplay.setDead(true);
                    break;
                }
            }
            gameplay.setSnake(snake);
            // rappelle la méthode paint()
            gameplay.repaint();
            this.timer.setDelay(model.getDelay());
        }
        if(gameplay.isLeft() && !gameplay.isDead() && !gameplay.isPause()){
            for (int i = model.getTaille()-1; i >=0;i--){
                snake[i+1][1] = snake[i][1];
                if(i == 0){
                    snake[i][0] = snake[i][0] - 20;
                }else{
                    snake[i][0] = snake[i-1][0];
                }
                if (snake[i][0] < 0) {
                    gameplay.setDead(true);
                    break;
                }
            }
            gameplay.setSnake(snake);
            gameplay.repaint();
            this.timer.setDelay(model.getDelay());
            //gameplay.requestFocus();
            //gameplay.revalidate();
        }
        if(gameplay.isDown() && !gameplay.isDead() && !gameplay.isPause()){
            for (int i = model.getTaille()-1; i >=0;i--){
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] + 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
                if (snake[i][1] > 660) {
                    gameplay.setDead(true);
                    break;
                }
            }
            gameplay.setSnake(snake);
            gameplay.repaint();
            this.timer.setDelay(model.getDelay());
        }
        if(gameplay.isUp() && !gameplay.isDead() && !gameplay.isPause()){
            for (int i = model.getTaille()-1; i >=0;i--){
                snake[i+1][0] = snake[i][0];
                if(i == 0){
                    snake[i][1] = snake[i][1] - 20;
                }else{
                    snake[i][1] = snake[i-1][1];
                }
                if (snake[i][1] < 0) {
                    gameplay.setDead(true);
                    break;
                }
            }
            gameplay.setSnake(snake);
            gameplay.repaint();
            this.timer.setDelay(model.getDelay());
        }
        for(int i = 0;i < model.getTaille();i++){
            // si le snake se mord son corp
            if((snake[0][0] == snake[i][0]) && (snake[0][1] == snake[i][1]) && (i != 0)){
                gameplay.setDead(true);
                break;
            }
        }
        if(e.getSource().equals(gameplay.getPauseBut())){
            gameplay.setPause(!gameplay.isPause());

        }

    }


    public void addScore(String[][] tabScore) throws IOException {
        List<String> lAddScore = new java.util.ArrayList<>();
        List<Integer> test = new java.util.ArrayList<>();
        lAddScore.add(String.valueOf(model.getScoreS()));
        //ajout du score dans le fichier score
        Path fichier = Paths.get("src/score.txt");
        Files.write(fichier, lAddScore, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        //lecture du fichier score
        BufferedReader reader = new BufferedReader(new FileReader("src/score.txt"));
        String sNewScoreAdd;
        while (true) {
            if (!((sNewScoreAdd = reader.readLine()) != null)) break;
            //Afficher les scores
            System.out.println("score : " + sNewScoreAdd);
            //pas de doublons dans la liste des scores
            if(!lAddScore.contains(sNewScoreAdd)){
                //ajout du score à la liste
                lAddScore.add(sNewScoreAdd);
            }
            //tri décroissant des scores
            for (String myString : lAddScore) {
                test.add(Integer.parseInt(myString));
            }
            test.sort(Collections.reverseOrder());
            System.out.println(lAddScore);
            lAddScore.clear();
            for (Integer myInt : test) {
                if(!lAddScore.contains(String.valueOf(myInt))){
                    //ajout du score à la liste
                    lAddScore.add(String.valueOf(myInt));
                }
            }
            //affiche contenu liste
            System.out.println(lAddScore);
            //ajout des scores dans le tableau des scores (menu)
            for(int i = 0; i<lAddScore.size(); i++) {
                //si un score nouveau et plus grand que le dernier du tableau est réalisé
                if(lAddScore.size() == 8){
                    //le dernier score du tableau est supprimé
                    lAddScore.remove(7);
                }else{
                    //ajout des scores dans l'ordre décroissant dans le tableau
                    tabScore[i][2] = lAddScore.get(i);
                }
            }
        }
        reader.close();
    }
}
