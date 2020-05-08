public class ControlGroup {

    private Model model;
    private Fenetre fen;
    public ControlBouton controlBut;

    public ControlGroup(Model model) {

        this.model = model;
        fen = new Fenetre();
        controlBut = new ControlBouton(fen, model);
    }
}