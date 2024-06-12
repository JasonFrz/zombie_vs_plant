import java.awt.event.ActionEvent;
import java.util.TimerTask;

import javax.swing.Timer;

public class Potatomine extends Plant {
    public Timer shootTimer;

    public Potatomine(Game parent, int x, int y) {
        super(parent, x, y);
        setHealth(1);
        Timer explosionTimer = new Timer(2000, (ActionEvent e) -> {
            explode();
        });
        explosionTimer.setRepeats(false);
        explosionTimer.start();
    }

    private void explode() {
        // remove all zombies in the lane
        game.clearLaneOfZombies(y);
        // Remove this potatomine from the game
        game.removePlant(this);
    }
}
