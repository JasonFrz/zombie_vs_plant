import java.awt.event.ActionEvent;
import javax.swing.*;


public class Repeater extends Plant {

    public Timer shootTimer;


    public Repeater(Game parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            //System.out.println("SHOOT");
            if(game.laneZombies.get(y).size() > 0) {
                game.lanePeas.get(y).add(new Pea(game, y, 103 + this.x * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}
