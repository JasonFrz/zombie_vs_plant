import java.awt.event.ActionEvent;
import javax.swing.*;


public class repeater extends Plant {

    public Timer shootTimer;


    public repeater(Game parent,int x,int y) {
        super(parent,x,y);
        shootTimer =  new Timer(2000, new RepeaterShoot(parent, y, x));
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}
