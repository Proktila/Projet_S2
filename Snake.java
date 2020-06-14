import javax.swing.*;

public class Snake {
    public int[][] snake = new int[720][2];

    private ImageIcon rightHead;
    private ImageIcon leftHead;
    private ImageIcon downHead;
    private ImageIcon upHead;
    private ImageIcon body;

    private String skin;
    private String imgDir = "img/snake/";

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

    public Snake(){
        this.skin="green";
        this.setSkinImg();
    }

    /**
     * affecte un skin
     */
    public void setSkinImg(){
        this.rightHead = new ImageIcon(imgDir+skin+"/HeadRight.png");
        this.leftHead = new ImageIcon(imgDir+skin+"/HeadLeft.png");
        this.downHead = new ImageIcon(imgDir+skin+"/HeadDown.png");
        this.upHead = new ImageIcon(imgDir+skin+"/HeadUp.png");
        this.body = new ImageIcon(imgDir+skin+"/Body.png");
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

            case "hack" :
                this.skin="hack";
                setSkinImg();
                break;

            case "gold" :
                this.skin="gold";
                setSkinImg();
                break;
            case "fish" :
                this.skin="fish";
                setSkinImg();
                break;
        }
    }

    public int[][] getSnake() { return snake; }
    public String getSkin() { return skin; }

    public boolean isLeft() { return left; }
    public boolean isRight() { return right; }
    public boolean isUp() { return up; }
    public boolean isDown() { return down; }
    public boolean isDead() { return dead; }
    public boolean isParalysed() { return paralysed; }

    public boolean isWinLaby() { return winLaby; }
    public void setWinLaby(boolean winLaby) { this.winLaby = winLaby; }

    public int getTaille() { return taille; }
    public int getScore() { return score; }
    public int getDelay() { return delay; }

    public ImageIcon getRightHead() { return rightHead; }
    public ImageIcon getLeftHead() { return leftHead; }
    public ImageIcon getDownHead() { return downHead; }
    public ImageIcon getUpHead() { return upHead; }
    public ImageIcon getBody() { return body; }

    public void setSnake(int[][] snake) { this.snake = snake; }
    public void setSkin(String skin) { this.skins(skin); }

    public void setLeft(boolean left) { this.left = left; }
    public void setRight(boolean right) { this.right = right; }
    public void setUp(boolean up) { this.up = up; }
    public void setDown(boolean down) { this.down = down; }
    public void setDead(boolean dead) { this.dead = dead; }
    public void setParalysed(boolean paralysed) { this.paralysed = paralysed; }

    public void setDelay(int delay) { this.delay = delay; }
    public void setTaille(int taille) { this.taille = taille; }
    public void setScore(int score) { this.score = score; }
}