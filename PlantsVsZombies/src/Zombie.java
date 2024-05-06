import javax.swing.*;
import java.awt.event.ActionEvent;


public class Zombie {

    public int health = 1000;
    public int speed = 1;

    private OnGame gp;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;

    public Zombie(OnGame parent,int lane){
        this.gp = parent;
        myLane = lane;
    }

    public void advance(){
        if(isMoving) {
            boolean isCollides = false;
            Utama collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp.OnFirst[i].assignedPlant != null && gp.OnFirst[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.OnFirst[i];
                }
            }
            if (!isCollides) {
                if(slowInt>0){
                    if(slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                }else {
                    posX -= 1;
                }
            } else {
                collided.assignedPlant.health -= 10;
                if (collided.assignedPlant.health < 0) {
                    collided.removePlant();
                }
            } 
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp,"ZOMBIE MAKAN OTAK MU :P");
                GameWindow.gameMenu.dispose();
                GameWindow.gameMenu = new GameWindow();
            }
        }
    }

    int slowInt = 0;
    public void slow(){
        slowInt = 1000;
    }
    public static Zombie getZombie(String type,OnGame parent, int lane) {
        Zombie z = new Zombie(parent,lane);
       switch(type) {
           case "NormalZombie" : z = new NormalZombie(parent,lane);
                                 break;
           case "ConeHeadZombie" : z = new ConeHeadZombie(parent,lane);
                                 break;
           case "BucketHeadZombie" : z = new BucketHeadZombie(parent,lane);
                                 break;
           case "FootballZombie" : z = new FootballZombie(parent,lane);
                                 break;
            case "gargantuar" : z = new gargantuar(parent,lane);
                                 break;
    }
       return z;
    }

}
