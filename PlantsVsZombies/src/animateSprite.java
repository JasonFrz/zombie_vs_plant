import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class animateSprite extends JLayeredPane implements MouseInputListener{
    private URL alamatImage;

    private  BufferedImage gambar ;
    private BufferedImage[] listgambar = new BufferedImage[22];
    private int indexGambar = 0;
    private Timer timer;
    public animateSprite() {
        addMouseListener(this);
        try{
            for (int i = 0; i <= 21; i++) {
                String imagePath = "images/zombies/NormalZombie" + i + ".png";
                URL fileUrl = new URL("file:///" + imagePath);
                gambar = ImageIO.read(fileUrl);
                listgambar[i]=gambar;
            }   
        }catch(IOException e){
            e.printStackTrace();
        }
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexGambar = (indexGambar + 1)%listgambar.length ; // Modulo 6 ensures it cycles from 0 to 5
                repaint();
            }
        });
        timer.start();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the current frame of the sprite
        // g.drawImage(listgambar[indexGambar], x, y, null);
        for (int i = 0; i < 5; i++) {
            g.drawImage(listgambar[indexGambar], 1000,109+(i*120),null);
        }
    }

    // Implementing the missing methods from MouseListener interface
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: Implement the logic for mouseClicked event
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO: Implement the logic for mousePressed event
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO: Implement the logic for mouseReleased event
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO: Implement the logic for mouseEntered event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO: Implement the logic for mouseExited event
    }

    // Implementing the missing methods from MouseMotionListener interface
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO: Implement the logic for mouseDragged event
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO: Implement the logic for mouseMoved event
    }
}
