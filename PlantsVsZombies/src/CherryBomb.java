import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class CherryBomb extends Plant {
    private static final int CELL_SIZE = 80; // ukuran setiap sel dalam piksel
    private static final int BLAST_RADIUS = 120; // radius ledakan dalam piksel

    public CherryBomb(Game game, int x, int y) {
        super(game, x, y);
        Timer explosionTimer = new Timer(1000, (ActionEvent e) -> {
            explode();
        });
        explosionTimer.setRepeats(false);
        explosionTimer.start();
    }

    private void explode() {
        game.clearLaneOfZombies(y);
        game.removePlant(this);
    }
}
