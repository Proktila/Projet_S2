class Model {

    private String difficulty;
    private String mode;

    private final int VOL_MIN = 0;
    private final int VOL_MAX = 100;
    private final int VOL_INIT = 75;

    private int volumeMusique = VOL_INIT;
    private int volumeBruits = VOL_INIT;
    public Model(){

    }

    public String getDifficulty() { return difficulty; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getMode() { return mode; }

    public int getVOL_MIN() { return VOL_MIN; }

    public int getVOL_MAX() { return VOL_MAX;}

    public int getVOL_INIT() { return VOL_INIT; }

    public void setMode(String mode) { this.mode = mode; }

    public int getVolumeMusique() { return volumeMusique; }

    public void setVolumeMusique(int volumeMusique) { this.volumeMusique = volumeMusique; }

    public int getVolumeBruits() { return volumeBruits; }

    public void setVolumeBruits(int volumeBruits) { this.volumeBruits = volumeBruits; }
}