import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class jalapeno extends Plant {

    public jalapeno(Game game, int x, int y) {
        super(game, x, y);
        Timer explosionTimer = new Timer(1000, (ActionEvent e) -> {
            explode();
        });
        explosionTimer.setRepeats(false);
        explosionTimer.start();
    }

    private void explode() {
        // Remove all zombies in the lane
        game.clearLaneOfZombies(y);
        // Remove this jalapeno from the game
        game.removePlant(this);
    }
}

