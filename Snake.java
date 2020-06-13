import javax.swing.*;

public class Snake {

    public int[][] snake = new int[720][2];

    private ImageIcon rightHead;
    private ImageIcon leftHead;
    private ImageIcon downHead;
    private ImageIcon upHead;
    private ImageIcon body;

    private String skin = "basique";

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private boolean dead = false;
    private boolean winLaby = false;
    private boolean paralysed = false;

    private int delay = 100;
    private int taille = 3;
    private int score = 0;

    private Model model;


    /**
     * @param model
     * créer un serpent et lui affecte son skin basique
     */
    public Snake(Model model){
        this.model = model;
        this.skin="green";
        this.setSkinImg();
    }

    /**
     * affecte un skin
     */
    public void setSkinImg(){
        this.rightHead = new ImageIcon(model.getChemin()+skin+"/HeadRight.png");
        this.leftHead = new ImageIcon(model.getChemin()+skin+"/HeadLeft.png");
        this.downHead = new ImageIcon(model.getChemin()+skin+"/HeadDown.png");
        this.upHead = new ImageIcon(model.getChemin()+skin+"/HeadUp.png");
        this.body = new ImageIcon(model.getChemin()+skin+"/Body.png");
    }

    /**
     * @param skin
     * affecte le skin en fonction du skin envoyé
     */
    public void skins(String skin){

        switch (skin) {

            case "basique" :
                this.skin="green";
                setSkinImg();
                break;

            case "nyan" :
                this.skin="nyan";
                setSkinImg();
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

    public void setSkin(String skin) { this.skins(this.skin);}
    public boolean isParalysed() { return paralysed; }

    public void setParalysed(boolean paralysed) { this.paralysed = paralysed; }
}