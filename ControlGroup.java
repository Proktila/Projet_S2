public class ControlGroup {
    private FenetreMenu fenMenu;
    public ControlBouton controlBut;

    public ControlGroup(Model model) {
        fenMenu = new FenetreMenu(model);
        controlBut = new ControlBouton(fenMenu, model);
    }

}