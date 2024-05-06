import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PlantCard extends JPanel implements MouseListener {

    Image img;
    ActionListener act;

    public PlantCard(Image img){
        setSize(64,90);
        this.img = img;
        addMouseListener(this);
    }

    public void setAction(ActionListener act){
        this.act = act;
    }

    @Override
    protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        graph.drawImage(img,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(act != null){
            act.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }
}
