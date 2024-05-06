import java.awt.*;


public class FreezePea extends Pea {

    public FreezePea(Game parent,int lane,int startX){
        super(parent,lane,startX);
    }

    @Override
    public void advance(){
        Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
        for (int i = 0; i < game.laneZombies.get(myLane).size(); i++) {
            Zombie z = game.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 300;
                z.slow();
                boolean exit = false;
                if(z.health < 0){
                    System.out.println("ZOMBIE DIE");
                    Game.setProgress(10);
                    game.laneZombies.get(myLane).remove(i);
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
