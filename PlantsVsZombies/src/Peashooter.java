import java.awt.event.ActionEvent;
import javax.swing.*;


public class Peashooter extends Plant {

    public Timer shootTimer;


    public Peashooter(Game parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(2000, new PeashooterShoot(parent, y, x));
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

    public void resume(){
        shootTimer.start();
    }

    public void pause(){
        shootTimer.stop();
    }
}
