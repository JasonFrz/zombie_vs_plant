import javax.swing.*;
import java.awt.event.ActionEvent;


public class Sunflower extends Plant {

    Timer sunProduction;

    public Sunflower(Game parent,int x,int y) {
        super(parent, x, y);
        sunProduction = new Timer(15000,(ActionEvent e) -> {
            Sun sun = new Sun(game,60 + x*100,110 + y*120,130 + y*120);
            game.activeSuns.add(sun);
            game.add(sun,new Integer(1));
        });
        sunProduction.start();
    }

    public void pause() {
        sunProduction.stop();
    }

    public void resume() {
        sunProduction.start();
    }
}
