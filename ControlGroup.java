public class ControlGroup {

    private Model model;
    private FenetreMenu fen;
    public ControlBouton controlBut;

    public ControlGroup(Model model) {

        this.model = model;
        fen = new FenetreMenu(model);
        controlBut = new ControlBouton(fen, model);
    }
}