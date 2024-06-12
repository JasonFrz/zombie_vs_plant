
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Wallnut extends Plant{

    private int x;
    
    public Wallnut(Game parent,int x,int y) {
        super(parent,x,y);
        setHealth(2000);
    }
}
