import java.awt.event.ActionEvent;
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
            String imagePath;
            switch (growthStage) {
                case 1:
                    imagePath = "images/plants/PotatoMine1.png";
                    break;
                case 2:
                    imagePath = "images/plants/PotatoMine2.png";
                    break;
                default:
                    imagePath = "images/plants/PotatoMine1.png";
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
        explosionTimer = new Timer(1000, (ActionEvent e) -> {
            if (checkForZombie()) {
                explode();
            }
        });
        explosionTimer.setRepeats(true);
        explosionTimer.start();
    }

    private boolean checkForZombie() {
        boolean isZombie = game.isZombieAt(getX(), getY());
        return isZombie;
    }

    private void explode() {
        // remove all zombies in the lane
        game.clearLaneOfZombies(y);
        // Remove this potatomine from the game
        game.removePlant(this);
        System.out.println("Potatomine at (" + getX() + ", " + getY() + ") exploded!");
        // Stop the explosion timer
        explosionTimer.stop();
    }
}
