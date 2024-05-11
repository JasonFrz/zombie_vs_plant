import java.awt.event.ActionEvent;
import javax.swing.*;


public class repeater extends Plant {

    public Timer shootTimer;


    public repeater(Game parent,int x,int y) {
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
