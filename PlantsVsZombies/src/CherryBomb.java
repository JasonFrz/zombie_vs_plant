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
        // Hapus zombie di sekitar CherryBomb dalam baris (y) dan kolom (x)
        for (int i = 0; i < 5; i++) {
            game.clearLaneOfZombies(i); // Hapus zombie dalam baris (y)
            game.clearColumnOfZombies(x); // Hapus zombie dalam kolom (x)
        }
        
        // Hapus CherryBomb setelah meledak
        game.removePlant(this);
    }
}
