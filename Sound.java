import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class Sound {

    /**
     * Lecture d'un fichier wav en lecture en boucle (pour la musique principal)
     * @param audioLocation chemin du fichier audio
     * @param defaultVolume volume par defaut
     * @return clip permettant l'acces au cet objet pour ajuster le volume
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static Clip playMusic(String audioLocation, int defaultVolume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // fichier a lire
        File audioPath = new File(audioLocation);

        // lecture audio
        AudioInputStream audio = AudioSystem.getAudioInputStream(audioPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);

        // volume par defaut
        setVolume(clip, defaultVolume);

        // lancement de la lecture
        clip.start();

        // lecture en boucle
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        return clip;
    }

    /**
     * Lecture d'un fichier wav en lecture unique (pour les bruitages)
     * @param audioLocation
     * @param volume
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static void playSound(String audioLocation, int volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // fichier a lire
        File audioPath = new File(audioLocation);

        // lecture audio
        AudioInputStream audio = AudioSystem.getAudioInputStream(audioPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);

        // volume par defaut
        setVolume(clip, volume);

        // lancement de la lecture
        clip.start();
    }

    /**
     * Ajuste le volume
     * @param clip objet a editer
     * @param volume volume a appliquer
     */
    public static void setVolume(Clip clip, int volume){
        FloatControl volControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volControl.setValue(volumeToGain(volume));
    }

    /**
     * Conversion de la valeur choisi dans le menu [0;100]
     * en une valeur utiliser par la bibliotheque de son [-80;6.0206]
     * @param volume volume choisi
     * @return valeur a appliquer sur un clip
     */
    private static float volumeToGain(int volume) {
        return (float) ((86.0206/100)*volume-80);
    }

}