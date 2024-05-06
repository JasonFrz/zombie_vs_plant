import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Sun extends JPanel implements MouseListener {

    Game gamePlay;
    Image sunImage;

    int myX;
    int myY;
    int endY;

    int hancur = 200;

    public Sun(Game parent,int startX,int startY,int endY){
        this.gamePlay = parent;
        this.endY = endY;
        setSize(80,80);
        setOpaque(false);
        myX = startX;
        myY = startY;
        setLocation(myX,myY);
        sunImage = new ImageIcon(this.getClass().getResource("images/sun.png")).getImage();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage,0,0,null);
    }

    public void advance(){
        if(myY < endY) {
            myY+= 4;
        }else{
            hancur--;
            if(hancur<0){
                gamePlay.remove(this);
                gamePlay.activeSuns.remove(this);
            }
        }
        setLocation(myX,myY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gamePlay.setSunScore(gamePlay.getSunScore()+25);
        gamePlay.remove(this);
        gamePlay.activeSuns.remove(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
