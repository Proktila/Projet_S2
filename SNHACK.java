import java.util.Scanner;

public class SNHACK {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        FenetreS fenetre = new FenetreS();
        PanelS panel = new PanelS();

        System.out.println("Test");

        Wall wall1 = new Wall(0, 0);
        /*Wall wall2 = new Wall(50, 250);*/

        wall1.attribution(panel);
        /*wall2.attribution(panel);*/

        panel.attribution(fenetre);
    }

}