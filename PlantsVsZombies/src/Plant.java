import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

public abstract class Plant {

    public int health = 200;
    public int x;
    public int y;
    public Game game;
    private ImageIcon image;

    public Plant(Game parent, int x, int y) {
        this.x = x;
        this.y = y;
        game = parent;
    }

    public void stop() {
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public ImageIcon getImage() {
        return image;
    }

    // Method to update the image of the plant
    public void updateImage(String imagePath) {
        try {
            java.net.URL imageUrl = getClass().getResource(imagePath); // Assuming images are in the same package or a
                                                                       // subpackage
            if (imageUrl != null) {
                ImageIcon imageIcon = new ImageIcon();
                // Set the imageIcon to the appropriate component here (e.g., JLabel)
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath + ", " + e.getMessage());
        }
    }

}
