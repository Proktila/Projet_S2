import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class ControlBouton implements ActionListener {

    Fenetre fen;
    Model model;
    String backFromSkinChild;


    public ControlBouton(Fenetre fen, Model model) {
        this.fen = fen;
        this.model = model;
        this.fen.setControlButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent a) {

        // Change le menu actuel vers le menu principal
        if(a.getSource().equals(fen.getBackFromSkin())|| a.getSource().equals(fen.getbRetour())){
            fen.changerMenuPrincipal();
        }
        // menu principal vers menu skin
        if (a.getSource().equals(fen.getBoutonSkins())) {
            fen.changerMenuSkin();
        }
        // menu principal vers menu skin
        if (a.getSource().equals(fen.getBoutonScores())) {
            fen.changerMenuScore();
        }
        // enfant de menu skin vers menu skin
        if(a.getSource() == fen.getBackFromSkinChild()){
            switch(backFromSkinChild){
                case "skinSerpent":
                    fen.getSkinSerpentButtonPanel().setVisible(false);
                    fen.setBackSkin();
                    backFromSkinChild=null;
                    break;
                case "skinMap":
                    fen.getSkinMapButtonPanel().setVisible(false);
                    fen.setBackSkin();
                    backFromSkinChild=null;
                    break;
                case "skinPseudo":
                    fen.getSkinPseudoButtonPanel().setVisible(false);
                    fen.setBackSkin();
                    backFromSkinChild=null;
                    break;
            }
        }
        // menu skin vers skin Serpent
        if(a.getSource() == fen.getSkinSerpent()){
            fen.getSkinButtonPanel().setVisible(false);
            fen.addSkinSerpent();
            backFromSkinChild = "skinSerpent";
        }
        // menu skin vers skin map
        if(a.getSource() == fen.getSkinMap()){
            fen.getSkinButtonPanel().setVisible(false);
            fen.addSkinMap();
            backFromSkinChild = "skinMap";
        }
        // menu skin vers skin pseudo
        if(a.getSource() == fen.getSkinPseudo()){
            fen.getSkinButtonPanel().setVisible(false);
            fen.addSkinPseudo();
            backFromSkinChild = "skinPseudo";
        }
    }
}
