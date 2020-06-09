import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Collections;

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
            try {
                showScore();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    try {
                        model.getScore().setActualPseudo(String.valueOf(fenMenu.getTfPseudo().getText()));
                        String pseudo = String.valueOf(model.getScore().getActualPseudo());
                        fenMenu.getTfPseudo().setText(pseudo);
                        fenMenu.getSkinPseudoButtonPanel().setVisible(false);
                        fenMenu.setBackSkin();
                        backFromSkinChild=null;
                        break;
                    } catch (PseudoOutOfBoundsException | SansPseudoException p) {
                        fenMenu.creerDialogErr(p.getMessage());
                    }
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
            model.getScore().setActualDifficulty(fenMenu.getButEasy().getText());
        }
        if(a.getSource().equals(fenMenu.getButNormal())){
            fenMenu.getPanPlay().setVisible(false);
            fenMenu.addDifficulty();
            model.setDifficulty("normal");
            model.getScore().setActualDifficulty(fenMenu.getButNormal().getText());
        }
        if(a.getSource().equals(fenMenu.getButHard())){
            fenMenu.getPanPlay().setVisible(false);
            fenMenu.addDifficulty();
            model.setDifficulty("hard");
            model.getScore().setActualMode(fenMenu.getButTrad().getText());
            model.getScore().setActualDifficulty(fenMenu.getButHard().getText());
        }
        if(a.getSource().equals(fenMenu.getButTrad())){
            // lance le jeu traditionelle
            model.setMode("traditionnel");
            fenMenu.setVisible(false);
            model.getScore().setActualMode(fenMenu.getButTrad().getText());
            fenSnake  = new FenetreSnake(fenMenu, model);
            ControlSnake controlSnake = new ControlSnake(fenSnake,model);

        }
        if(a.getSource().equals(fenMenu.getButLaby())){
            // lance le jeu labyrinthe
            model.setMode("labyrinthe");
            model.getScore().setActualMode(fenMenu.getButLaby().getText());
        }
        if(a.getSource().equals(fenMenu.getButChrono())){
            // lance le jeu labyrinthe
            model.setMode("chrono");
            model.getScore().setActualMode(fenMenu.getButChrono().getText());
        }
        if(a.getSource().equals(fenMenu.getButDuo())){
            // lance le jeu labyrinthe
            model.setMode("duo");
        }
        if(a.getSource().equals(fenMenu.getBackDifficulty())){
            fenMenu.getPanDifficulty().setVisible(false);
            fenMenu.setBackPlay();
        }
        // choix map
        if (a.getSource().equals(fenMenu.getCbMap())){
            model.setMap(( (JComboBox) a.getSource()).getSelectedItem().toString());
        }
    }
    public void showScore() throws IOException {
        model.getScore().initListScore();
        BufferedReader readerScore;
        readerScore = new BufferedReader(new FileReader("src/score.txt"));
        String line;
        int nbline = 0;

        while ((line = readerScore.readLine()) != null) {
            nbline++;
            // Récupération données dans le fichier texte
            if (nbline == 1) {
                model.getScore().getListMode().add(line);
                System.out.println(line);
               }
            if (nbline == 2) {
                model.getScore().getListDifficulty().add(line);
                System.out.println(line);
                }
            if (nbline == 3) {
                model.getScore().getListPseudo().add(line);
                System.out.println(line);
            }
            if (nbline == 4) {
                model.getScore().getListData().add(Integer.valueOf(line));
                model.getScore().getListScore().add(line);
                nbline = 0;
            }


            for (int i = 0; i < model.getScore().getListData().size(); i++) {
                fenMenu.getData()[i][0] = model.getScore().getListMode().get(i);
                fenMenu.getData()[i][1] = model.getScore().getListDifficulty().get(i);
                fenMenu.getData()[i][2] = model.getScore().getListPseudo().get(i);
                fenMenu.getData()[i][3] = model.getScore().getListScore().get(i);
            }
        }
        readerScore.close();
    }

    public FenetreSnake getFenSnake() {
        return fenSnake;
    }

    public void setFenSnake(FenetreSnake fenSnake) {
        this.fenSnake = fenSnake;
    }
}
