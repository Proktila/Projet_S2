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
    }

    public boolean isFaceWall(){
        // Envoie maquête ?
        // Recherche de tous les murs
        //  comparer la position des murs a la tête du serpent
        // si tete serpent devant mur et petit delai pour bouger et tjr devant mur alors
        // loseLife()
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

    public void loseSize(){
        this.loseSize(1);
    }
    public void loseSize(int size){
        this.taille -= size;
    }

    public void addSize(){
        this.addSize(1);
    }
    public void addSize(int size){
        this.taille += size;
    }

    public void loseLife(){
        this.taille -= 1;
    }

    public void addLife(){
        this.taille += 1;
    }

    public double[][] getPosCorps() {
        return posCorps;
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
        return this.nbFruitsManger;
    }

    public void setNbFruitsManger(int nbFruitsManger) {
        this.nbFruitsManger = nbFruitsManger;
    }

    public Score getScore() {
        return this.score;
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
        return this.skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public double[] getPosTete() {
        return this.posTete;
    }

    public void setPosTete(double[] posTete) {
        this.posTete = posTete;
    }

    public int getDirection() {
        return this.direction;
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
