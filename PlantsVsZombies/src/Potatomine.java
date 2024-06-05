import java.util.Timer;
import java.util.TimerTask;

public class Potatomine extends Plant{
    public Timer shootTimer;

    public Potatomine(Game parent, int x,int y){
        super(parent,x,y);
        setHealth(1);
        // shootTimer = new Timer();
        // shootTimer.schedule(new TimerTask() {
        //     @Override
        //     public void run() {
                
        //     }
        // });

    }

    // @Override
    // public void stop(){
    //     shootTimer.stop();
    // }

    // public void resume(){
    //     shootTimer.start();
    // }

    // public void pause(){
    //     shootTimer.stop();
    // }
}
