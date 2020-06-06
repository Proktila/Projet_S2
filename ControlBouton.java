import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class ControlBouton implements ActionListener, ChangeListener {

    FenetreMenu fenMenu;
    Model model;
    FenetreSnake fenSnake;
    String backFromSkinChild;


    public ControlBouton(FenetreMenu fenMenu,  Model model) {
        this.fenMenu = fenMenu;
        this.model = model;
        this.fenMenu.setControlButton(this);
    }

    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
            if(e.getSource().equals(fenMenu.getSlVolumeMusique())){
                model.setVolumeMusique(source.getValue());
            }else if(e.getSource().equals(fenMenu.getSlVolumeBruits())){
                model.setVolumeBruits(source.getValue());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {

        // Change le menu actuel vers le menu principal
        if(a.getSource().equals(fenMenu.getBackFromSkin()) || a.getSource().equals(fenMenu.getbRetour()) || a.getSource().equals(fenMenu.getBackCredits()) || a.getSource().equals(fenMenu.getBackParam()) || a.getSource().equals(fenMenu.getBackPlay())){
            fenMenu.changerMenuPrincipal();
        }
        // menu principal vers menu skin
        if (a.getSource().equals(fenMenu.getBoutonSkins())) {
            fenMenu.changerMenuSkin();
        }
        // menu principal vers menu score
        if (a.getSource().equals(fenMenu.getBoutonScores())) {
            fenMenu.changerMenuScore();
        }
        // menu principal vers menu jouer
        if (a.getSource().equals(fenMenu.getBoutonJouer())) {
            fenMenu.changerMenuJouer();
        }
        // menu principal vers menu credits
        if (a.getSource().equals(fenMenu.getBoutonCredits())) {
            fenMenu.changerMenuCredit();
        }
        // menu principal vers menu parametres
        if (a.getSource().equals(fenMenu.getBoutonParametres())) {
            fenMenu.changerMenuParam();
        }
        // enfant de menu skin vers menu skin
        if(a.getSource() == fenMenu.getBackFromSkinChild()){
            switch(backFromSkinChild){
                case "skinSerpent":
                    fenMenu.getSkinSerpentButtonPanel().setVisible(false);
                    fenMenu.setBackSkin();
                    backFromSkinChild=null;
                    break;
                case "skinMap":
                    fenMenu.getSkinMapButtonPanel().setVisible(false);
                    fenMenu.setBackSkin();
                    backFromSkinChild=null;
                    break;
                case "skinPseudo":
                    fenMenu.getSkinPseudoButtonPanel().setVisible(false);
                    fenMenu.setBackSkin();
                    backFromSkinChild=null;
                    break;
            }
        }
        // menu skin vers skin Serpent
        if(a.getSource() == fenMenu.getSkinSerpent()){
            fenMenu.getSkinButtonPanel().setVisible(false);
            fenMenu.addSkinSerpent();
            backFromSkinChild = "skinSerpent";
        }
        // menu skin vers skin map
        if(a.getSource() == fenMenu.getSkinMap()){
            fenMenu.getSkinButtonPanel().setVisible(false);
            fenMenu.addSkinMap();
            backFromSkinChild = "skinMap";
        }
        // menu skin vers skin pseudo
        if(a.getSource() == fenMenu.getSkinPseudo()){
            fenMenu.getSkinButtonPanel().setVisible(false);
            fenMenu.addSkinPseudo();
            backFromSkinChild = "skinPseudo";
        }

        if(a.getSource().equals(fenMenu.getButEasy())){
            fenMenu.getPanPlay().setVisible(false);
            fenMenu.addDifficulty();
            model.setDifficulty("easy");
        }
        if(a.getSource().equals(fenMenu.getButNormal())){
            fenMenu.getPanPlay().setVisible(false);
            fenMenu.addDifficulty();
            model.setDifficulty("normal");
        }
        if(a.getSource().equals(fenMenu.getButHard())){
            fenMenu.getPanPlay().setVisible(false);
            fenMenu.addDifficulty();
            model.setDifficulty("hard");
        }
        if(a.getSource().equals(fenMenu.getButTrad())){
            // lance le jeu traditionelle
            model.setMode("Traditionnel");
            fenMenu.setVisible(false);
            fenSnake  = new FenetreSnake(fenMenu);
            ControlSnake controlSnake = new ControlSnake(fenSnake,model);

        }
        if(a.getSource().equals(fenMenu.getButLaby())){
            // lance le jeu labyrinthe
            model.setMode("Labyrinthe");
        }
        if(a.getSource().equals(fenMenu.getButChrono())){
            // lance le jeu labyrinthe
            model.setMode("Chrono");
        }
        if(a.getSource().equals(fenMenu.getButDuo())){
            // lance le jeu labyrinthe
            model.setMode("Duo");
        }
        if(a.getSource().equals(fenMenu.getBackDifficulty())){
            System.out.println("test");
            fenMenu.getPanDifficulty().setVisible(false);
            fenMenu.setBackPlay();
        }

    }

    public FenetreSnake getFenSnake() {
        return fenSnake;
    }

    public void setFenSnake(FenetreSnake fenSnake) {
        this.fenSnake = fenSnake;
    }
}
