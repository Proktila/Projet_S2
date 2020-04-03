import java.util.Scanner;
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;

public class Snake extends JPanel {
    private static final long serialVersionUID = 1L;

    private int sizeS = 3;
    private int posX;
    private int posY;
    private int nbFruits = 0;
    private String skin;
    private int direction; // 0 = haut, 1 = droite, 2 = bas, 3 = gauche
    private boolean isAlive = true;

    public Snake(){ super(); }

    public Snake(String skinS, int dirS, int posXS, int posYS){
        super();
        this.skin = skinS;
        this.direction = dirS;
        this.posX = posXS;
        this.posY = posYS;
    }

    public void run(){
        while( this.isAlive ){

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

    public void setPosXSnake(int posXs){
        this.posX = posXs;
    }

    public void setPosYSnake(int posYs){
        this.posY = posYs;
    }

    public int getPosXSnake(){
        return this.posX;
    }

    public int getPosYSnake(){
        return this.posY;
    }

    public int getNbFruits(){
        return this.nbFruits;
    }

    public String getSkin(){
        return this.skin;
    }

    public int getSizeS(){
        return this.sizeS;
    }

    public int getDirection(){
        return this.direction;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public String toString(){
        return "";
    }

}
