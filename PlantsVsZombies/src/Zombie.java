import javax.swing.*;
import java.awt.event.ActionEvent;


public class Zombie {

    public int health = 1000;
    public int speed = 1;

    private Game game;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;
    private int currentSprite = 0;
    private int numSprites = 21;

    public Zombie(Game parent,int lane){
        this.game = parent;
        myLane = lane;
    }

    public void advance(){
        if(isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (game.OnFirst[i].assignedPlant != null && game.OnFirst[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = game.OnFirst[i];
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
                collided.assignedPlant.health -= 5;
                if (collided.assignedPlant.health < 0) {
                    collided.removePlant();
                }
            } 
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(game,"ZOMBIE MAKAN OTAK MU :P");
                PlantVsZombie.gameMenu.dispose();
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }
        }
    }

    public int getCurrentSpriteIndex() {
        return currentSprite;
    }

    // Metode untuk mengubah indeks sprite saat ini
    public void setCurrentSpriteIndex(int index) {
        currentSprite = index;
    }

    public void nextSprite() {
        // Implementasi logika untuk melompat ke sprite berikutnya
        currentSprite = (currentSprite + 1) % numSprites;
    }

    // Metode untuk mengubah indeks sprite saat ini ke nilai sebelumnya
    public void prevSprite() {
        // Implementasi logika untuk melompat ke sprite sebelumnya
        currentSprite = (currentSprite - 1 + numSprites) % numSprites;
    }

    int slowInt = 0;
    public void slow(){
        slowInt = 1000;
    }
    public static Zombie getZombie(String type,Game parent, int lane) {
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
            case "Gargantuar" : z = new gargantuar(parent,lane);
                                    break;
        }
        return z;
    }

}
