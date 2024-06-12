import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class CherryBomb extends Plant {

    public CherryBomb(Game game, int x, int y) {
        super(game, x, y);
        Timer explosionTimer = new Timer(1000, (ActionEvent e) -> {
            explode();
        });
        explosionTimer.setRepeats(false);
        explosionTimer.start();
    }

    private void explode() {
        
        for (int i = 0; i < 5; i++) {
            game.clearLaneOfZombies(i); 
            game.clearColumnOfZombies(x); 
        }
        
        
        game.removePlant(this);
    }
}
