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
        // int[][] area = {
        //     {0, 0}, {0, -1}, {0, 1}, {-100, 0}, {100, 0},
        //     {-100, -1}, {-100, 1}, {100, -1}, {100, 1}
        // };

        // System.out.println("CherryBomb exploding at (" + x + ", " + y + ")");

        // // int posX = Zombie.getPosX();
        // for (int[] areaAtk : area) {
        //     int checkX = x + areaAtk[0];
        //     int checkY = y + areaAtk[1];
            
        //     if (isValidPosition(checkX, checkY)) {
        //         game.killZombieAt(checkX, checkY);
        //     }
        // }

        game.removePlant(this);
    }

    // private boolean isValidPosition(int x, int y) {
    //     return x >= 0 && x < 9 && y >= 0 && y < 5;
    // }
}
