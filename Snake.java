import javax.swing.*;

public class Snake {

    public int[][] snake = new int[720][2];

    private ImageIcon rightHead;
    private ImageIcon leftHead;
    private ImageIcon downHead;
    private ImageIcon upHead;
    private ImageIcon body;

    private String skin = "basic";

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private boolean dead = false;
    private boolean winLaby = false;

    private int delay = 100;
    private int taille = 3;
    private int score = 0;

    private Model model;


    public Snake(Model model){
        this.model = model;
        this.setBasicSkin();
    }
    // Gérer les skins avec un fonction par skin :
    // Skin "traditionnel" du snake
    public void setBasicSkin(){
        this.rightHead = new ImageIcon(model.getChemin()+"green/HeadRight.png");
        this.leftHead = new ImageIcon(model.getChemin()+"green/HeadLeft.png");
        this.downHead = new ImageIcon(model.getChemin()+"green/HeadDown.png");
        this.upHead = new ImageIcon(model.getChemin()+"green/HeadUp.png");
        this.body = new ImageIcon(model.getChemin()+"green/Body.png");
    }

    // Exemples de skin, à compléter une fois les skins acquis
    public void setNyanCatSkin(){
        this.rightHead = new ImageIcon(model.getChemin()+"nyan/HeadRight.png");
        this.leftHead = new ImageIcon(model.getChemin()+"nyan/HeadLeft.png");
        this.downHead = new ImageIcon(model.getChemin()+"nyan/HeadDown.png");
        this.upHead = new ImageIcon(model.getChemin()+"nyan/HeadUp.png");
        this.body = new ImageIcon(model.getChemin()+"nyan/Body.png");

    }

    public void setGoldSkin(){

    }

    public void setCyborgSkin(){

    }

    // Gérer les skins avec un switch case :

    public void skins(String skin){

        switch (skin) {

            case "basic" :
                this.setBasicSkin();
                break;

            case "nyan" :
                setNyanCatSkin();
                break;

            case "gold" :
                break;

            case "cyborg" :
                break;

            default:
                break;
        }
    }

    public boolean isRight() { return right; }

    public void setRight(boolean right) { this.right = right; }

    public boolean isLeft() { return left; }

    public void setLeft(boolean left) { this.left = left; }

    public boolean isUp() { return up; }

    public void setUp(boolean up) { this.up = up; }

    public boolean isDown() { return down; }

    public void setDown(boolean down) { this.down = down; }

    public boolean isDead() { return dead; }

    public void setDead(boolean dead) { this.dead = dead; }

    public boolean isWinLaby() { return winLaby; }

    public void setWinLaby(boolean winLaby) { this.winLaby = winLaby; }

    public int getDelay() { return delay; }

    public void setDelay(int delay) { this.delay = delay; }

    public int getTaille() { return taille; }

    public void setTaille(int taille) { this.taille = taille; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public int[][] getSnake() { return snake; }

    public void setSnake(int[][] snake) { this.snake = snake; }

    public ImageIcon getRightHead() { return rightHead; }

    public ImageIcon getLeftHead() { return leftHead; }

    public ImageIcon getDownHead() { return downHead; }

    public ImageIcon getUpHead() { return upHead; }

    public ImageIcon getBody() { return body; }

    public String getSkin() { return skin; }

    public void setSkin(String skin) {
        this.skin = skin;
        this.skins(this.skin);
    }
}