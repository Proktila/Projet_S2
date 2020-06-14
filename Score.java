import java.io.*;
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

    //chemins contenant les données (garde historique des scores)
    private Path fichierScore = Paths.get("score/score.txt");
    private Path fichierPseudo = Paths.get("score/pseudo.txt");
    private Path fichierMode = Paths.get("score/mode.txt");
    private Path fichierDiff = Paths.get("score/difficulte.txt");

    //vairables pour lires les fichiers de lecture
    private BufferedReader readerScore;
    private BufferedReader readerPseudo;
    private BufferedReader readerMode;
    private BufferedReader readerDiff;

    //variables pour lire les fichiers
    private FileReader fileReaderScore;
    private FileReader fileReaderPseudo;
    private FileReader fileReaderMode;
    private FileReader fileReaderDiff;

    //variables pour création des fichiers
    private File fileScore;
    private File filePseudo;
    private File fileMode;
    private File fileDiff;

    //constructeur vide
    public Score(){}

    /**initialisation des listes
     */
    public void initList(){
        listScore = new java.util.LinkedList<>();
        listPseudo = new java.util.LinkedList<>();
        listMode = new java.util.LinkedList<>();
        listDifficulty = new java.util.LinkedList<>();
        listData = new java.util.LinkedList<>();
    }

    /**ajout du score, mode, difficulté, pseudo dans la liste correspondante
     */
    public void addScore() {
        getListMode().add(getActualMode());
        getListDifficulty().add(getActualDifficulty());
        getListPseudo().add(getActualPseudo());
        getListScore().add(String.valueOf(getActualScore()));
    }

    /**ajout des valeurs des scores,modes, difficultés et pseudo dans les fichiers textes correspondants
        @throws java.io.IOException
     */
    public void addScoreInFich() throws IOException {
        Files.write(getFichierMode(), getListMode(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierDiff(), getListDifficulty(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierPseudo(), getListPseudo(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        Files.write(getFichierScore(), getListScore(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    /**repère de l'indice du score dans la liste trié pour pouvoir trier le pseudo, la difficulté et le mode
     en fonction de cet indice
     @param score
     @return int : l'indice
     */
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

    /**tri des scores dans la liste d'entier
     */
    public void triScore(){
        getListScore().clear();
        for (Integer myInt :getListData()) {
            //injection de ces entiers dans la liste des scores en format String
            getListScore().add(String.valueOf(myInt));
        }
    }

    /**méthodes qui initialise les fichiers (qui seront à lire)
     * @throws java.io.IOException
     */
    public void initFich() throws IOException {
        fileScore = new File("score/score.txt");
        filePseudo = new File("score/pseudo.txt");
        fileMode = new File("score/mode.txt");
        fileDiff = new File("score/difficulte.txt");
        //création de ces fichiers
        fileScore.createNewFile();
        filePseudo.createNewFile();
        fileMode.createNewFile();
        fileDiff.createNewFile();
    }

    /**méthode qui initialise les fichiers textes de lecture
     * @throws java.io.FileNotFoundException
     */
    public void initReaderFich() throws FileNotFoundException {
        fileReaderScore = new FileReader("score/score.txt");
        fileReaderPseudo = new FileReader("score/pseudo.txt");
        fileReaderMode = new FileReader("score/mode.txt");
        fileReaderDiff = new FileReader("score/difficulte.txt");
    }

    /**méthode qui lie initialise la lecture des fichiers à lire
     */
    public void readFich() {
        readerScore = new BufferedReader(getFileReaderScore());
        readerPseudo = new BufferedReader(getFileReaderPseudo());
        readerMode = new BufferedReader(getFileReaderMode());
        readerDiff = new BufferedReader(getFileReaderDiff());
    }

    /**méthode qui vide les listes
     */
    public void videScore(){
        getListMode().clear();
        getListDifficulty().clear();
        getListPseudo().clear();
        getListScore().clear();
        getListData().clear();
    }

    /**méthode qui vide les fichiers textes
     * @throws java.io.IOException
     */
    public void videFich() throws IOException {
        FileWriter newFichScore = new FileWriter(getFileScore(),false);
        FileWriter newFichPseudo = new FileWriter(getFilePseudo(),false);
        FileWriter newFichMode = new FileWriter(getFileMode(),false);
        FileWriter newFichDiff = new FileWriter(getFileDiff(),false);
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
    public BufferedReader getReaderScore() { return readerScore; }
    public BufferedReader getReaderPseudo() { return readerPseudo; }
    public BufferedReader getReaderMode() { return readerMode; }
    public BufferedReader getReaderDiff() { return readerDiff; }
    public File getFileScore() { return fileScore; }
    public File getFilePseudo() { return filePseudo; }
    public File getFileMode() { return fileMode; }
    public File getFileDiff() { return fileDiff; }
    public FileReader getFileReaderScore() { return fileReaderScore; }
    public FileReader getFileReaderPseudo() { return fileReaderPseudo; }
    public FileReader getFileReaderMode() { return fileReaderMode; }
    public FileReader getFileReaderDiff() { return fileReaderDiff; }


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