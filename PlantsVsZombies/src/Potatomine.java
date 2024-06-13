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

public class Potatomine extends Plant {
    private Timer growthTimer;
    private Timer explosionTimer;
    private int growthStage;
    private BufferedImage plantImage;
    private static final int GROWTH_DURATION = 6000;
    private static final int TOTAL_STAGES = 3;

    public Potatomine(Game parent, int x, int y) {
        super(parent, x, y);
        setHealth(1);
        growthStage = 0;

        growthTimer = new Timer(GROWTH_DURATION / TOTAL_STAGES, (ActionEvent e) -> {
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
            Image imagePath;
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
        explosionTimer = new Timer(2000, (ActionEvent e) -> {
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
        // List<Zombie> lane = getGame().laneZombies.get(getY());
        // Iterator<Zombie> it = lane.iterator();
        // while (it.hasNext()) {
        // Zombie zombie = it.next();
        // System.out.println("Checking zombie at (" + zombie.posX + ", " + getY() +
        // ")");
        // if (zombie.posX == getX()) {
        // System.out.println("Removing zombie at (" + zombie.posX + ", " + getY() +
        // ")");
        // // Remove the zombie using the iterator
        // it.remove();
        // getGame().killZombieAt(zombie.posX, getY());
        // // Remove all zombies in the lane
        // game.clearLaneOfZombies(y);
        // }
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
