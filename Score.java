import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class Score {
    private String bestScore;
    private String actualScore;
    private String actualPseudo;
    private String bestDifficulty;
    private String actualDifficulty;
    private String actualMode;
    private Path fichierScore = Paths.get("src/score.txt");
    private  List<String> listScore;
    private  List<String> listPseudo;
    private  List<String> listMode;
    private  List<String> listDifficulty;
    private List<Integer> listData;

    public Score(){
    }
    public String getActualPseudo(){
        return actualPseudo;
    }

    public void setActualPseudo(String actualPseudo) throws PseudoOutOfBoundsException, SansPseudoException{
        if(actualPseudo.length()>15) {
            throw new PseudoOutOfBoundsException(actualPseudo);
        }
        else if(actualPseudo.length()==0) {
            throw new SansPseudoException();
        }else {
            this.actualPseudo = actualPseudo;
        }
    }

    public String getBestDifficulty() { return bestDifficulty; }

    public void setBestDifficulty(String bestDifficulty) { this.bestDifficulty = bestDifficulty; }

    public String getBestScore() {
        return bestScore;
    }

    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }

    public String getActualScore() {
        return actualScore;
    }

    public void setActualScore(String actualScore) {
        this.actualScore = actualScore;
    }

    public String getActualDifficulty() {
        return actualDifficulty;
    }

    public void setActualDifficulty(String actualDifficulty) {
        this.actualDifficulty = actualDifficulty;
    }

    public String getActualMode() {
        return actualMode;
    }

    public void setActualMode(String actualMode) {
        this.actualMode = actualMode;
    }


    public void initListScore(){
        listScore = new java.util.ArrayList<>();
        listPseudo = new java.util.ArrayList<>();
        listMode = new java.util.ArrayList<>();
        listDifficulty = new java.util.ArrayList<>();
        listData = new java.util.ArrayList<>();
    }

    public void addScore() {
        getListMode().add(getActualMode());
        getListDifficulty().add(getActualDifficulty());
        getListPseudo().add(getActualPseudo());
        getListScore().add(String.valueOf(getActualScore()));
    }

    public void addScoreInFich() throws IOException {
        Files.write(getFichierScore(), getListMode(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierScore(), getListDifficulty(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierScore(), getListPseudo(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierScore(), getListScore(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public List<Integer> getListData() {
        return listData;
    }

    public void setListData(List<Integer> listData) {
        this.listData = listData;
    }

    public Path getFichierScore() {
        return fichierScore;
    }

    public void setFichierScore(Path fichierScore) {
        this.fichierScore = fichierScore;
    }

    public List<String> getListScore() {
        return listScore;
    }

    public void setListScore(List<String> listScore) {
        this.listScore = listScore;
    }

    public List<String> getListPseudo() {
        return listPseudo;
    }

    public void setListPseudo(List<String> listPseudo) {
        this.listPseudo = listPseudo;
    }

    public List<String> getListMode() {
        return listMode;
    }

    public void setListMode(List<String> listMode) {
        this.listMode = listMode;
    }

    public List<String> getListDifficulty() {
        return listDifficulty;
    }

    public void setListDifficulty(List<String> listDifficulty) {
        this.listDifficulty = listDifficulty;
    }
}

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

class SansPseudoException extends Exception{
    SansPseudoException (){
        super(
                "<html><center>Veuillez saisir un " +
                        "<strong style='color:red'>pseudo.</strong>" +
                        "</center></html>"
        );
    }
}