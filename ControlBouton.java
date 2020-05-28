import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class ControlBouton implements ActionListener, ChangeListener {

    FenetreMenu fen;
    Model model;
    String backFromSkinChild;


    public ControlBouton(FenetreMenu fen, Model model) {
        this.fen = fen;
        this.model = model;
        this.fen.setControlButton(this);
    }

    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
            if(e.getSource().equals(fen.getSlVolumeMusique())){
                model.setVolumeMusique(source.getValue());
            }else if(e.getSource().equals(fen.getSlVolumeBruits())){
                model.setVolumeBruits(source.getValue());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {

        // Change le menu actuel vers le menu principal
        if(a.getSource().equals(fen.getBackFromSkin()) || a.getSource().equals(fen.getbRetour()) || a.getSource().equals(fen.getBackCredits()) || a.getSource().equals(fen.getBackParam()) || a.getSource().equals(fen.getBackPlay())){
            fen.changerMenuPrincipal();
        }
        // menu principal vers menu skin
        if (a.getSource().equals(fen.getBoutonSkins())) {
            fen.changerMenuSkin();
        }
        // menu principal vers menu score
        if (a.getSource().equals(fen.getBoutonScores())) {
            fen.changerMenuScore();
        }
        // menu principal vers menu jouer
        if (a.getSource().equals(fen.getBoutonJouer())) {
            fen.changerMenuJouer();
        }
        // menu principal vers menu credits
        if (a.getSource().equals(fen.getBoutonCredits())) {
            fen.changerMenuCredit();
        }
        // menu principal vers menu parametres
        if (a.getSource().equals(fen.getBoutonParametres())) {
            fen.changerMenuParam();
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

        if(a.getSource().equals(fen.getButEasy())){
            fen.getPanPlay().setVisible(false);
            fen.addDifficulty();
            model.setDifficulty("easy");
        }
        if(a.getSource().equals(fen.getButNormal())){
            fen.getPanPlay().setVisible(false);
            fen.addDifficulty();
            model.setDifficulty("normal");
        }
        if(a.getSource().equals(fen.getButHard())){
            fen.getPanPlay().setVisible(false);
            fen.addDifficulty();
            model.setDifficulty("hard");
        }
        if(a.getSource().equals(fen.getButTrad())){
            // lance le leu traditionelle
            model.setMode("Traditionnel");
            fen.dispose();
            FenetreSnake fenetreSnake = new FenetreSnake(model);
        }
        if(a.getSource().equals(fen.getButLaby())){
            // lance le jeu labyrinthe
            model.setMode("Labyrinthe");
        }
        if(a.getSource().equals(fen.getButChrono())){
            // lance le jeu labyrinthe
            model.setMode("Chrono");
        }
        if(a.getSource().equals(fen.getButDuo())){
            // lance le jeu labyrinthe
            model.setMode("Duo");
        }
        if(a.getSource().equals(fen.getBackDifficulty())){
            System.out.println("test");
            fen.getPanDifficulty().setVisible(false);
            fen.setBackPlay();
        }

    }
}
