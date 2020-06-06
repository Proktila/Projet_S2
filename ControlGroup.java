public class ControlGroup {

    private Model model;
    private FenetreMenu fenMenu;
    private FenetreSnake fenSnake;
    private Gameplay gameplay;
    public ControlBouton controlBut;
    public  ControlSnake controlSnake;

    public ControlGroup(Model model) {

        this.model = model;
        fenMenu = new FenetreMenu(model);
        controlBut = new ControlBouton(fenMenu, model);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public FenetreMenu getFenMenu() {
        return fenMenu;
    }

    public void setFenMenu(FenetreMenu fenMenu) {
        this.fenMenu = fenMenu;
    }

    public FenetreSnake getFenSnake() {
        return fenSnake;
    }

    public void setFenSnake(FenetreSnake fenSnake) {
        this.fenSnake = fenSnake;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public ControlBouton getControlBut() {
        return controlBut;
    }

    public void setControlBut(ControlBouton controlBut) {
        this.controlBut = controlBut;
    }

    public ControlSnake getControlSnake() {
        return controlSnake;
    }

    public void setControlSnake(ControlSnake controlSnake) {
        this.controlSnake = controlSnake;
    }
}