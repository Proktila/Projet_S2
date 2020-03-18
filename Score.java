public class Score {
    private int bestScore = 0;
    private int actualScore = 0;
    private String pseudoBestScore;

    public Score(){
        
    }

    public int getBestScore(){
        return this.bestScore;
    }

    public int getActualScore(){
        return this.actualScore;
    }

    public String getPseudoBestScore(){
        return this.pseudoBestScore;
    }
}