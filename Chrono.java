
import java.util.Timer;
import java.util.TimerTask;

public class Chrono {
    private int time;


    public Chrono(int time){
        setChrono();
        this.time = time;
    }

    /**
     * lance le chrono
     */
    public void setChrono(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask(){

            public void run() {
                // Action répétée à chaque période (d'une seconde ici) du Timer
                time--;
                if(time <= 0){
                    cancel();
                }
            }
        }, 1000, 1000); // Le Timer se lance après 1000ms et le TimerTask se répète toutes les 1000ms
    }

    public int getTime() { return time; }

    public void setTime(int time) { this.time = time; }
}