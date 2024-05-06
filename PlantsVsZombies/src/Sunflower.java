import javax.swing.*;
import java.awt.event.ActionEvent;


public class Sunflower extends Plant {

    Timer sunProduction;

    public Sunflower(Game parent,int x,int y) {
        super(parent, x, y);
        sunProduction = new Timer(15000,(ActionEvent e) -> {
            Sun sta = new Sun(game,60 + x*100,110 + y*120,130 + y*120);
            game.activeSuns.add(sta);
            game.add(sta,new Integer(1));
        });
        sunProduction.start();
    }

}
