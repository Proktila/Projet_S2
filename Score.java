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

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }

    public void setPseudoBestScore(String pseudoBestScore) {
        this.pseudoBestScore = pseudoBestScore;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Score score = (Score) object;
        return bestScore == score.bestScore &&
                actualScore == score.actualScore &&
                java.util.Objects.equals(pseudoBestScore, score.pseudoBestScore);
    }

}