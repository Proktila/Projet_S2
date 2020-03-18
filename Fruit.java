import java.util.Scanner;
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;

public class Fruit {
    final private String[] IMAGES = {};

    private String type;
    private int posX;
    private int posY;

    public Fruit(String typeF, int posXF, int posYF){
        this.type = typeF;
        this.posX = posXF;
        this.posY = posYF;
    }

    public int getPosXFruit(){
        return this.posX;
    }

    public int getPosYFruit(){
        return this.posY;
    }

    public String getTypeFruit(){
        return this.type;
    }

    
}