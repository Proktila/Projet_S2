import java.util.ArrayList;

public class Maquette {
    private final int SIZE = 720/20;

    private Object[][] tab = new Object[SIZE][SIZE];

    private String theme;

    public Maquette(String theme) {
        this.theme = theme;
    }

    /**
     * Ajoute un mur dans la matrice de la maquette
     * @param wall mur a ajouter
     */
    public void createWall(Wall wall) {
        int wX = wall.getX();
        int wY = wall.getY();

        this.tab[wX][wY] = wall;
    }

    /**
     * Ajoute une liste de murs dans la matrice de la maquette
     * @param wList liste de murs a ajouter
     */
    public void createWalls(ArrayList<Wall> wList) {
        for (Wall wall : wList) {
            int wX = wall.getX();
            int wY = wall.getY();

            this.tab[wX][wY] = wall;
        }
    }

    /**
     * Ajoute un fruit dans la matrice de la maquette
     * @param fruit fruit a ajouter
     */
    public void placeFruit(Fruit fruit) {
        int fruitX = fruit.getPosX();
        int fruitY = fruit.getPosY();
        
        this.tab[fruitX][fruitY] = fruit;
    }

    public Object[][] getTab() {
        return tab;
    }
    public void setTab(Object[][] tab) {
        this.tab = tab;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
