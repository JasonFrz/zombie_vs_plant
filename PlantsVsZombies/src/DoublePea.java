import javax.swing.*;
import java.awt.*;


public class DoublePea {

    public int posX;
    protected Game game;
    public int myLane;

    public DoublePea(Game parent,int lane,int startX){
        this.game = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance(){
        Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
        for (int i = 0; i < game.laneZombies.get(myLane).size(); i++) {
            Zombie z = game.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 600;
                boolean exit = false;
                if(z.health < 0){
                    System.out.println("ZOMBIE DIE");
                    
                    game.laneZombies.get(myLane).remove(i);
                    Game.setProgress(10);
                    exit = true;
                }
                game.lanePeas.get(myLane).remove(this);
                if(exit) break;
            }
        }
        /*if(posX > 2000){
            game.lanePeas.get(myLane).remove(this);
        }*/
        posX += 15;
    }

}
