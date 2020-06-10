import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Score {
    //attributs
    //score, pseudo, difficulté et mode
    private int actualScore;
    private String actualPseudo;
    private String actualDifficulty;
    private String actualMode;

    //liste des attributs
    private  List<String> listScore;
    private  List<String> listPseudo;
    private  List<String> listMode;
    private  List<String> listDifficulty;
    //liste d'entier contenant les scores pour les trier
    private List<Integer> listData;

    //fichiers contenant les données (garde historique des scores)
    private Path fichierScore = Paths.get("src/score.txt");
    private Path fichierPseudo = Paths.get("src/pseudo.txt");
    private Path fichierMode = Paths.get("src/mode.txt");
    private Path fichierDiff = Paths.get("src/difficulte.txt");

    //constructeur vide
    public Score(){}

    //initialisation des listes
    public void initList(){
        listScore = new java.util.LinkedList<>();
        listPseudo = new java.util.LinkedList<>();
        listMode = new java.util.LinkedList<>();
        listDifficulty = new java.util.LinkedList<>();
        listData = new java.util.LinkedList<>();
    }

    //ajout du score, mode, difficulté, pseudo dans la liste correspondante
    public void addScore() {
        getListMode().add(getActualMode());
        getListDifficulty().add(getActualDifficulty());
        getListPseudo().add(getActualPseudo());
        getListScore().add(String.valueOf(getActualScore()));
    }

    //ajout des valeurs des scores,modes, difficultés et pseudo dans les fichiers textes correspondants
    public void addScoreInFich() throws IOException {
        Files.write(getFichierMode(), getListMode(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierDiff(), getListDifficulty(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierPseudo(), getListPseudo(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierScore(), getListScore(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    //repère de l'indice du score dans la liste trié pour pouvoir trier le pseudo, la difficulté et le mode
    //en fonction de cet indice
    public int indiceScore(Score score){
        int indScore;
        for (indScore = 0; indScore < score.getListScore().size(); indScore++) {
            //si la valeur à l'indice dans la liste des scores trié est la même que la valeur du score actuel (le score
            //qui vient d'être réalisé)
            if (score.getListScore().get(indScore).equals(String.valueOf(score.getActualScore()))) {
                //alors je retourne cet indice
                return  indScore;
            }
        }
        return 0;
    }

    //tri des scores dans la liste d'entier
    public void triScore(){
        getListScore().clear();
        for (Integer myInt :getListData()) {
            //injection de ces entiers dans la liste des scores en format String
            getListScore().add(String.valueOf(myInt));
        }
    }

    /**************************************************getter et setter************************************************/

    //ajout de 2 exception sur le pseudo
    public void setActualPseudo(String actualPseudo) throws PseudoOutOfBoundsException, SansPseudoException{
        //le pseudo ne doit pas dépasser 15 caractères
        if(actualPseudo.length()>15) {
            throw new PseudoOutOfBoundsException(actualPseudo);
        }
        //le pseudo ne doit pas être vide
        else if(actualPseudo.length()==0) {
            throw new SansPseudoException();
        }else {
            this.actualPseudo = actualPseudo;
        }
    }

    public String getActualPseudo(){ return actualPseudo; }
    public int getActualScore() { return actualScore; }
    public void setActualScore(int actualScore) { this.actualScore = actualScore; }
    public String getActualDifficulty() { return actualDifficulty; }
    public void setActualDifficulty(String actualDifficulty) { this.actualDifficulty = actualDifficulty;}
    public String getActualMode() { return actualMode; }
    public void setActualMode(String actualMode) { this.actualMode = actualMode; }
    public Path getFichierPseudo() { return fichierPseudo; }
    public Path getFichierMode() { return fichierMode; }
    public Path getFichierDiff() { return fichierDiff; }
    public List<Integer> getListData() { return listData; }
    public Path getFichierScore() { return fichierScore; }
    public List<String> getListScore() { return listScore; }
    public List<String> getListPseudo() { return listPseudo; }
    public List<String> getListMode() { return listMode; }
    public List<String> getListDifficulty() { return listDifficulty; }

}

/**Excpetion si le pseudo dépasse 15 caractères**/
class PseudoOutOfBoundsException extends Exception{
    PseudoOutOfBoundsException(String pseudo) {
        super(
                "<html><center>Votre pseudo du nom de " + pseudo +
                        " est trop <strong style='color:red'>long.</strong><br> " +
                        "Il doit comporter au maximum " +
                        "<strong style='color:red'>15 caractères.</strong></center></html>"
        );
    }
}

/**Exception si le pseudo est vide**/
class SansPseudoException extends Exception{
    SansPseudoException (){
        super(
                "<html><center>Veuillez saisir un " +
                        "<strong style='color:red'>pseudo.</strong>" +
                        "</center></html>"
        );
    }
}