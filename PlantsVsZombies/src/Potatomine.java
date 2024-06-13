import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Potatomine extends Plant {
    private Timer growthTimer;
    private Timer explosionTimer;
    private int growthStage;
    private static final int GROWTH_DURATION = 3000;
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
            String imagePath = null;
            switch (growthStage) {
                case 1:
                    imagePath = "images/plants/PotatoMine1.png";
                    break;
                case 2:
                    imagePath = "images/plants/PotatoMine2.png";
                    break;
            }

            if (imagePath != null) {
                try {
                    updateImage(imagePath); // Assuming updateImage handles image loading
                } catch (Exception e) {
                    System.err.println("Error updating image for Potatomine: " + e.getMessage());
                }
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
        // Remove all zombies in the lane
        getGame().clearLaneOfZombies(getY());
        // Remove this potatomine from the game
        getGame().removePlant(this);
        System.out.println("Potatomine at (" + getX() + ", " + getY() + ") exploded!");
        // Stop the explosion timer
        explosionTimer.stop();
    }

    public void updateImage(String path) {
        try {
            // Load the image from the given path and set it to the plant
            BufferedImage newImage = ImageIO.read(new File(path));
            this.setImage(newImage);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + path);
        }
    }
}
