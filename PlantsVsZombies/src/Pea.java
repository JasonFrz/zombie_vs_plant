import javax.swing.*;
import java.awt.*;


public class Pea {

    public int posX;
    protected Game game;
    public int myLane;

    public Pea(Game parent,int lane,int startX){
        this.game = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance(){
        Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
c
        /*if(posX > 2000){
            game.lanePeas.get(myLane).remove(this);
        }*/
        posX += 15;
    }

}
