import java.awt.event.ActionEvent;
import javax.swing.*;


public class FreezePeashooter extends Plant {

    public Timer shootTimer;


    public FreezePeashooter(Game parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            System.out.println("beciu beciu");
            if(game.laneZombies.get(y).size() > 0) {
                game.lanePeas.get(y).add(new FreezePea(game, y, 103 + this.x * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}