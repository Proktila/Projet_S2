import java.util.Scanner;
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;

public class Wall {
    private int posX;
    private int posY;
    private String skin;

    public Wall(int posXW, int posYW, String skinW){
        this.posX = posXW;
        this.posY = posYW;
        this.skin = skinW;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public String getSkin(){
        return this.skin;
    }
}