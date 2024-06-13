import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import javafx.scene.shape.Rectangle;

public class Potatomine extends Plant {
    private Timer growthTimer;
    private Timer explosionTimer;
    private int growthStage;
    private BufferedImage plantImage;
    private static final int GROWTH_GROW = 60000;
    private static final int TOTAL_STAGES = 3;
    static Image imagePath;

    public Potatomine(Game parent, int x, int y) {
        super(parent, x, y);
        setHealth(20);
        growthStage = 0;

        grow();

        growthTimer = new Timer(GROWTH_GROW / TOTAL_STAGES, (ActionEvent e) -> {
            grow();
        });
        
        growthTimer.setRepeats(true);
        growthTimer.start();
    }

    private void grow() {
        if (growthStage < TOTAL_STAGES) {
            growthStage++;
            System.out.println("Potatomine at (" + getX() + ", " + getY() + ") growth stage " + growthStage);

            // Update image based on growth stage
            
            switch (growthStage) {
                case 1:
                    imagePath = new ImageIcon(this.getClass().getResource("images/plants/PotatoMine1.png")).getImage();
                    break;
                case 2:
                    imagePath = new ImageIcon(this.getClass().getResource("images/plants/PotatoMine2.png")).getImage();
                    break;
            }
        } else {
            growthTimer.stop();
            startCheckZombies();
        }
    }

    private void startCheckZombies() {
        explosionTimer = new Timer(1000, (ActionEvent e) -> {
            if (checkForZombie()) {
                explode();
            }
        });
        explosionTimer.setRepeats(true);
        explosionTimer.start();
    }

    private boolean checkForZombie() {
        return getGame().isZombieAt(getX(), getY());
    }

    private void explode() {
        // Rectangle pRect = new Rectangle(getX(),130+y*120,28,28);
        // for (int i = 0; i < game.laneZombies.get(y).size(); i++) {
        //     Zombie z = game.laneZombies.get(y).get(i);
        //     Rectangle zRect = new Rectangle(z.posX,109 + y*120,400,120);
        //     if(pRect.intersects(zRect)){
        //         z.health -= 143;
        //         boolean exit = false;
        //         if(z.health < 0){
        //             System.out.println("Gacor");
        //             System.out.println(posX + " " + z.posX + " " + myLane + " " + i);

                    
        //             game.laneZombies.get(myLane).remove(i);
        //             Game.setProgress(10);
        //             exit = true;
        //         }
        //         game.lanePeas.get(myLane).remove(this);
        //         if(exit) break;
        //     }
        // }

        if (checkForZombie()) {
            getGame().clearLaneOfZombies(getY());
        }

        // Remove this potatomine from the game
        getGame().removePlant(this);
        System.out.println("Potatomine at (" + getX() + ", " + getY() + ") exploded!");

        // Stop the explosion timer
        explosionTimer.stop();
    }

    public void setImage(BufferedImage image) {
        this.plantImage = image;
    }

    public void updateImage(String imagePath) {
        try {
            // Load the image from the given path and set it to the plant
            BufferedImage newImage = ImageIO.read(new File(imagePath));
            setImage(newImage);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + imagePath);
        }
    }
}
