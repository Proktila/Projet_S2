import java.util.Scanner;
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;

public class Snake extends JPanel {
    private static final long serialVersionUID = 1L;

    private double posCorp[][]; // taille map
    private int taille = 3;
    private double vitesse;
    private int nbFruitsManger = 0;
    private Score score;
    private int nbsVies = 3;
    private String skin = "normal";
    private double posTete[] = new double[2];
    private int direction = 0; // 0 = haut, 1 = droite, 2 = bas, 3 = gauche

    public Snake(){ super(); }

    public Snake(double[][] posCorp, double vitesse, String skin, double[] posTete) {
        this.posCorp = posCorp;
        this.vitesse = vitesse;
        this.skin = skin;
        this.posTete = posTete;
    }

    public void run(double speed){
        while( this.nbsVies > 0 ){

        }
    }

    public void turnLeft(){
        if( this.direction == 0 ){
            this.direction = 3;
        } else {
            this.direction -= 1;
        }
    }

    public void turnRight(){
        if( this.direction == 3 ){
            this.direction = 0;
        } else {
            this.direction += 1;
        }
    }

    public boolean isOnFruit(){
        // Envoie maquête ?
        // Recherche de tous les fruits
        //  comparer la position des fruits a la tête du serpent
        // si tete serpent = posFruit -> eat(Fruit fruit)
        return false;
    }

    public boolean isFaceWall(){
        // Envoie maquête ?
        // Recherche de tous les murs
        //  comparer la position des murs a la tête du serpent
        // si tete serpent devant mur et petit delai pour bouger et tjr devant mur alors
        // loseLife()
        return false;
    }


    public void eat(Fruit fruit){
        // eaten Fruit
        this.nbFruitsManger += 1;
        // recup stats fruit
        // faire grandir le serpent de x longueurs en fonction des stats du fruit
        this.addSize(1);
    }
    public void die(){
        // detruis le serpent
        // affiche game over
    }

    public void loseSize(int size){
        this.taille -= size;
    }

    public void addSize(int size){
        this.taille += size;
    }



}
