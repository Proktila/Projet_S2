import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
                Sound.setVolume(model.getMusicClip(), model.getVolumeMusique());
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
        if(a.getSource().equals(fenMenu.getbClear())){
            try {
                videData();
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
            fenSnake  = new FenetreSnake(fenMenu, model);
            ControlSnake controlSnake = new ControlSnake(fenSnake,model);
        }
        if(a.getSource().equals(fenMenu.getButChrono())){
            // lance le jeu labyrinthe
            model.setMode("chrono");
            model.getScore().setActualMode(fenMenu.getButChrono().getText());
            fenSnake  = new FenetreSnake(fenMenu, model);
            ControlSnake controlSnake = new ControlSnake(fenSnake,model);
        }
        if(a.getSource().equals(fenMenu.getButDuo())){
            // lance le jeu labyrinthe
            model.setMode("duo");
            fenSnake  = new FenetreSnake(fenMenu, model);
            ControlSnake controlSnake = new ControlSnake(fenSnake,model);
        }
        if(a.getSource().equals(fenMenu.getBackDifficulty())){
            fenMenu.getPanDifficulty().setVisible(false);
            fenMenu.setBackPlay();
        }
        // choix skin serpent
        if (a.getSource().equals(fenMenu.getCbSerpent())){
            model.getJ1().setSkin(( (JComboBox) a.getSource()).getSelectedItem().toString());
        }
        // choix map
        if (a.getSource().equals(fenMenu.getCbMap())){
            model.setTheme(( (JComboBox) a.getSource()).getSelectedItem().toString());
        }

    }
    //méthode qui affiche les scores dans le tableau
    public void showScore() throws IOException{
        //initialisation et création des fichiers pour lire les scores, modes, difficultés et pseudo
        model.getScore().initFich();
        model.getScore().initReaderFich();
        model.getScore().readFich();
        Score score = model.getScore();
        //initialisation des listes
        model.getScore().initList();
        //initialisation des 4 fichiers à lire
        String linePseudo;String lineScore;String lineMode;String lineDiff;
        //tant que la ligne dans les fichiers texte n'est pas null
        while (((lineScore = score.getReaderScore().readLine()) != null) && (linePseudo = score.getReaderPseudo().readLine()) != null
                && ((lineMode = score.getReaderMode().readLine()) != null) && (lineDiff = score.getReaderDiff().readLine()) != null ) {

            //on ajoute la ligne des scores dans la liste des Scores
            score.getListScore().add(lineScore);
            score.setActualScore(Integer.parseInt(lineScore));
            //on ajoute le score à la liste d'entier
            score.getListData().add(Integer.valueOf(lineScore));
            //on trie cette liste par ordre décroissant
            score.getListData().sort(Collections.reverseOrder());
            //on vide la liste de Score en String pour ajouter les scores triés provenant de la listeData
            score.triScore();
            //on ajoute les lignes des fichiers dans les listes associées
            score.getListPseudo().add(linePseudo);
            score.getListMode().add(lineMode);
            score.getListDifficulty().add(lineDiff);
            //on trie les listes : mode, dofficulté et pseudo à partir de la liste des scores
            triData(score, linePseudo, lineMode, lineDiff);
        }
        //fermeture des fichiers
        score.getReaderScore().close();
        score.getReaderPseudo().close();
        score.getReaderMode().close();
        score.getReaderDiff().close();
    }

    //méthode qui ajoute au bon endroit le mode, difficulté, pseudo en fonction de la position des scores
    public void triData(Score score, String linePseudo, String lineMode, String lineDiff){
        //on retire la derniere valeur de la liste puis on ajoute la derniere valeur à l'indice correspondant
        //à la position du score
        score.getListPseudo().remove(score.getListPseudo().size() - 1);
        score.getListPseudo().add(score.indiceScore(score), linePseudo);
        score.getListMode().remove(score.getListMode().size() - 1);
        score.getListMode().add(score.indiceScore(score), lineMode);
        score.getListDifficulty().remove(score.getListDifficulty().size() - 1);
        score.getListDifficulty().add(score.indiceScore(score), lineDiff);
        //on affiche les données dans le tableau des scores
        for (int i = 0; i < score.getListScore().size(); i++) {
            //si la taille de la liste des scores dépasse la taille du tableau
            if(score.getListScore().size() >= 51){
                //on retire la dernière valeur
                //si le nouveau score est inferieur à cette valeur elle n'apparaitra pas dans le tableau
                score.getListScore().remove(50);
            }
            //affichage des données
            fenMenu.getData()[i][0] = score.getListMode().get(i);
            fenMenu.getData()[i][1] = score.getListDifficulty().get(i);
            fenMenu.getData()[i][2] = score.getListPseudo().get(i);
            fenMenu.getData()[i][3] = score.getListScore().get(i);
        }
    }
    //methode qui va vider les scores (bouton vider)
    public void videData() throws IOException {
        //vide les listes et fichiers
        model.getScore().videScore();
        model.getScore().videFich();
        //affichage du tableau vide
        for (int i = 0; i < fenMenu.getData().length; i++) {
            //vide les données
            fenMenu.getData()[i][0] = "";
            fenMenu.getData()[i][1] = "";
            fenMenu.getData()[i][2] = "";
            fenMenu.getData()[i][3] = "";
        }
    }
}
