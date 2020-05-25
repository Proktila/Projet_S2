import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class Sound {

    public static Clip playBgMusic(String audioLocation) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        File audioPath = new File(audioLocation);

        AudioInputStream audio = AudioSystem.getAudioInputStream(audioPath);
        Clip clip = AudioSystem.getClip();

        clip.open(audio);
        clip.start();

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        return clip;
    }

    public static void playSound(String audioLocation, int volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        File audioPath = new File(audioLocation);

        AudioInputStream audio = AudioSystem.getAudioInputStream(audioPath);
        Clip clip = AudioSystem.getClip();

        clip.open(audio);
        setVolume(clip, volume);
        clip.start();
    }

    public static void setVolume(Clip clip, int volume){
        FloatControl volControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volControl.setValue(volumeToGain(volume));
    }

    private static float volumeToGain(int volume) {
        return (float) ((86.0206/100)*volume-80);
    }


}