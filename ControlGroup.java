public class ControlGroup {
    public ControlBouton controlBut;

    public ControlGroup(Model model) {
        FenetreMenu fenMenu = new FenetreMenu(model);
        controlBut = new ControlBouton(fenMenu, model);
    }

}