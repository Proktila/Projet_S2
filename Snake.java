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

    public void run(){
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

    public void eat(){

    }

    public double[][] getPosCorp() {
        return posCorp;
    }

    public void setPosCorp(double[][] posCorp) {
        this.posCorp = posCorp;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getNbFruitsManger() {
        return nbFruitsManger;
    }

    public void setNbFruitsManger(int nbFruitsManger) {
        this.nbFruitsManger = nbFruitsManger;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getNbsVies() {
        return nbsVies;
    }

    public void setNbsVies(int nbsVies) {
        this.nbsVies = nbsVies;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public double[] getPosTete() {
        return posTete;
    }

    public void setPosTete(double[] posTete) {
        this.posTete = posTete;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Snake{" +
                "sizeS=" + sizeS +
                ", posX=" + posX +
                ", posY=" + posY +
                ", nbFruits=" + nbFruits +
                ", skin='" + skin + '\'' +
                ", direction=" + direction +
                ", isAlive=" + isAlive +
                '}';
    }
}
