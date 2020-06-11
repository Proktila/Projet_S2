import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;

class Model {

    private String difficulty;
    private String mode;
    private String map = "";

    private final int VOL_MIN = 0;
    private final int VOL_MAX = 100;
    private final int VOL_INIT = 75;

    private int volumeMusique = VOL_INIT;
    private int volumeBruits = VOL_INIT;

    private Score score;
    private Snake j1;
    private Snake j2;

    private String chemin = "img/snake/";
    Clip musicClip;

    private List<Wall> listeWall=new ArrayList<Wall>();
    private List<Fruit> listeFruit=new ArrayList<Fruit>();
    private java.util.List<Fruit> toAdd=new ArrayList<Fruit>();

    public Model(){
        score = new Score();
        j1 = new Snake(this);
        j2 = new Snake(this);

    }
    public Fruit choisirFruit(){
        String difficulte = this.difficulty;
        String mode = this.mode;
        Fruit fruit = new Fruit();
        int randFruit = (int)(Math.random()*((100)));
        if (difficulte == "easy" && mode == "traditionnel"){
            if (randFruit<10) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>9 && randFruit<20) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>19 && randFruit<30) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>29 && randFruit<40) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>39 && randFruit<50) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>49 && randFruit<60) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>59 && randFruit<70) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>69 && randFruit<75) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>74 && randFruit<80) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>79 && randFruit<85) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>84 && randFruit<90) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>89 && randFruit<95) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>94 && randFruit<100) {
                fruit = new Fruit("radis");
            }
        }

        if (difficulte == "normal" && mode == "traditionnel"){
            if (randFruit<6) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>5 && randFruit<14) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>13 && randFruit<20) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>19 && randFruit<28) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>27 && randFruit<36) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>35 && randFruit<42) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>41 && randFruit<50) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>49 && randFruit<60) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>59 && randFruit<68) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>67 && randFruit<76) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>75 && randFruit<86) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>85 && randFruit<92) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>91 && randFruit<100) {
                fruit = new Fruit("radis");
            }
        }

        if (difficulte == "hard" && mode == "traditionnel"){
            if (randFruit<4) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>3 && randFruit<10) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>9 && randFruit<12) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>11 && randFruit<18) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>17 && randFruit<22) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>21 && randFruit<26) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>25 && randFruit<30) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>29 && randFruit<42) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>41 && randFruit<54) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>53 && randFruit<66) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>65 && randFruit<78) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>77 && randFruit<88) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>87 && randFruit<100) {
                fruit = new Fruit("radis");
            }
        }

        if (difficulte == "easy" && (mode == "chrono" || mode == "labyrinthe")){
            if (randFruit<8) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>7 && randFruit<18) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>17 && randFruit<28) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>27 && randFruit<36) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>35 && randFruit<46) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>45 && randFruit<54) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>53 && randFruit<62) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>61 && randFruit<70) {
                fruit = new Fruit("ananas");
            }
            else if (randFruit>69 && randFruit<74) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>73 && randFruit<78) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>77 && randFruit<82) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>81 && randFruit<88) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>87 && randFruit<90) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>89 && randFruit<96) {
                fruit = new Fruit("radis");
            }
            else if (randFruit>95 && randFruit<100) {
                fruit = new Fruit("aubergine");
            }
        }

        if (difficulte == "normal" && (mode == "chrono" || mode == "labyrinthe")){
            if (randFruit<4) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>3 && randFruit<12) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>11 && randFruit<18) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>17 && randFruit<24) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>23 && randFruit<32) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>31 && randFruit<38) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>37 && randFruit<42) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>41 && randFruit<50) {
                fruit = new Fruit("ananas");
            }
            else if (randFruit>49 && randFruit<58) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>57 && randFruit<66) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>65 && randFruit<72) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>71 && randFruit<80) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>79 && randFruit<84) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>83 && randFruit<92) {
                fruit = new Fruit("radis");
            }
            else if (randFruit>91 && randFruit<100) {
                fruit = new Fruit("aubergine");
            }
        }

        if (difficulte == "hard" && (mode == "chrono" || mode == "labyrinthe")){
            if (randFruit<4) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>3 && randFruit<10) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>9 && randFruit<12) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>11 && randFruit<16) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>15 && randFruit<20) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>19 && randFruit<24) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>23 && randFruit<26) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>25 && randFruit<30) {
                fruit = new Fruit("ananas");
            }
            else if (randFruit>29 && randFruit<42) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>41 && randFruit<52) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>51 && randFruit<62) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>61 && randFruit<72) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>71 && randFruit<80) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>79 && randFruit<92) {
                fruit = new Fruit("radis");
            }
            else if (randFruit>91 && randFruit<100) {
                fruit = new Fruit("aubergine");
            }
        }

        if (difficulte == "easy" && (mode == "duo")){
            if (randFruit<8) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>7 && randFruit<18) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>17 && randFruit<28) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>27 && randFruit<38) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>37 && randFruit<48) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>47 && randFruit<56) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>55 && randFruit<64) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>63 && randFruit<70) {
                fruit = new Fruit("ananas");
            }
            else if (randFruit>69 && randFruit<75) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>74 && randFruit<80) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>79 && randFruit<85) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>84 && randFruit<90) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>89 && randFruit<95) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>94 && randFruit<100) {
                fruit = new Fruit("radis");
            }
        }

        if (difficulte == "normal" && mode == "duo") {
            if (randFruit < 6) {
                fruit = new Fruit("peche");
            } else if (randFruit > 5 && randFruit < 14) {
                fruit = new Fruit("pomme");
            } else if (randFruit > 13 && randFruit < 20) {
                fruit = new Fruit("banane");
            } else if (randFruit > 19 && randFruit < 26) {
                fruit = new Fruit("raisin");
            } else if (randFruit > 25 && randFruit < 32) {
                fruit = new Fruit("mure");
            } else if (randFruit > 31 && randFruit < 38) {
                fruit = new Fruit("pasteque");
            } else if (randFruit > 37 && randFruit < 44) {
                fruit = new Fruit("framboise");
            } else if (randFruit > 43 && randFruit < 50) {
                fruit = new Fruit("cerise");
            } else if (randFruit > 49 && randFruit < 60) {
                fruit = new Fruit("chou-fleur");
            } else if (randFruit > 59 && randFruit < 68) {
                fruit = new Fruit("asperge");
            } else if (randFruit > 67 && randFruit < 76) {
                fruit = new Fruit("poivron");
            } else if (randFruit > 75 && randFruit < 86) {
                fruit = new Fruit("carotte");
            } else if (randFruit > 85 && randFruit < 92) {
                fruit = new Fruit("piment");
            } else if (randFruit > 91 && randFruit < 100) {
                fruit = new Fruit("radis");
            }
        }

        if (difficulte == "hard" && mode == "duo"){
            if (randFruit<2) {
                fruit = new Fruit("peche");
            }
            else if (randFruit>1 && randFruit<6) {
                fruit = new Fruit("pomme");
            }
            else if (randFruit>5 && randFruit<10) {
                fruit = new Fruit("banane");
            }
            else if (randFruit>9 && randFruit<14) {
                fruit = new Fruit("raisin");
            }
            else if (randFruit>13 && randFruit<18) {
                fruit = new Fruit("mure");
            }
            else if (randFruit>17 && randFruit<22) {
                fruit = new Fruit("pasteque");
            }
            else if (randFruit>21 && randFruit<26) {
                fruit = new Fruit("framboise");
            }
            else if (randFruit>25 && randFruit<30) {
                fruit = new Fruit("cerise");
            }
            else if (randFruit>29 && randFruit<42) {
                fruit = new Fruit("chou-fleur");
            }
            else if (randFruit>41 && randFruit<54) {
                fruit = new Fruit("asperge");
            }
            else if (randFruit>53 && randFruit<66) {
                fruit = new Fruit("poivron");
            }
            else if (randFruit>65 && randFruit<78) {
                fruit = new Fruit("carotte");
            }
            else if (randFruit>77 && randFruit<88) {
                fruit = new Fruit("piment");
            }
            else if (randFruit>87 && randFruit<100) {
                fruit = new Fruit("radis");
            }
        }
        return fruit;
    }

    public Score getScore() { return score; }

    public void setScore(Score score) { this.score = score; }

    public String getDifficulty() { return difficulty; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getMode() { return mode; }

    public String getMap() { return map; }

    public int getVOL_MIN() { return VOL_MIN; }

    public int getVOL_MAX() { return VOL_MAX;}

    public int getVOL_INIT() { return VOL_INIT; }

    public int getVolumeMusique() { return volumeMusique; }

    public int getVolumeBruits() { return volumeBruits; }

    public void setMode(String mode) { this.mode = mode; }

    public void setVolumeMusique(int volumeMusique) { this.volumeMusique = volumeMusique; }

    public void setVolumeBruits(int volumeBruits) { this.volumeBruits = volumeBruits; }

    public void setMap(String map) { this.map = map; }

    public String getChemin() { return chemin; }

    public Snake getJ1() { return j1; }

    public void setJ1(Snake j1) { this.j1 = j1; }

    public List<Wall> getListeWall() { return listeWall; }

    public List<Fruit> getListeFruit() { return listeFruit; }

    public List<Fruit> getToAdd() { return toAdd; }

    public Clip getMusicClip() { return musicClip; }
    public void setMusicClip(Clip musicClip) { this.musicClip = musicClip; }

    public Snake getJ2() { return j2; }
}