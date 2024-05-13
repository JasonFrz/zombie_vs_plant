
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JASON
 */
public class Wallnut extends Plant{

    private int x;
     public Wallnut(Game parent,int x,int y) {
        super(parent,x,y);
        setHealth(2000);
    }
}
