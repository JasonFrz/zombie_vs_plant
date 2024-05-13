import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FreezePeaShoot implements ActionListener {
    private final Game game;
    private final int lane;
    private final int x;

    public FreezePeaShoot(Game game, int lane, int x) {
        this.game = game;
        this.lane = lane;
        this.x = x;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.laneZombies.get(lane).size() > 0) {
            game.lanePeas.get(lane).add(new FreezePea(game, lane, 103 + x * 100));
        }
    }
}
