import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;


public class PlantVsZombie extends JFrame {

    enum PlantType{
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter,
        Wallnut,
        Repeater,
        Jalapeno,
        PotatoMine
    }

    static boolean cardSunflower = true;
    static boolean cardPeashooter = true;
    static boolean cardWallnut = true;
    static boolean cardFreezePeashooter = false;
    static boolean cardRepeater = false;
    static boolean cardJalapeno = false;
    static boolean cardPotato = false;
    //PlantType activePlantingBrush = PlantType.None;
    
    public PlantVsZombie(){
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37,80);
        sun.setSize(60,20);

        Game onGame = new Game(sun);
        onGame.setLocation(0,0);
        getLayeredPane().add(onGame,new Integer(0));
        
        if (cardSunflower){
            PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
            sunflower.setLocation(110,8);
            sunflower.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Sunflower;
            });
            getLayeredPane().add(sunflower,new Integer(3));
        }
        
        if (cardPeashooter){
            PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
            peashooter.setLocation(175,8);
            peashooter.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Peashooter;
            });
            getLayeredPane().add(peashooter,new Integer(3));
        }

        if (cardWallnut){{
            PlantCard wallnut = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_wallnut.png")).getImage());
            wallnut.setLocation(240,8);
            wallnut.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Wallnut;
            });
            getLayeredPane().add(wallnut,new Integer(3));
        }}

        if (cardFreezePeashooter){
            PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
            freezepeashooter.setLocation(305,8);
            freezepeashooter.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.FreezePeashooter;
            });
            getLayeredPane().add(freezepeashooter,new Integer(3));
        }
        
        if (cardRepeater){
            PlantCard repeater = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_repeater.png")).getImage());
            repeater.setLocation(370,8);
            repeater.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Repeater;
            });
            getLayeredPane().add(repeater,new Integer(3));
        }

        if (cardJalapeno){
            PlantCard jalapeno = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_jalapeno.png")).getImage());
            jalapeno.setLocation(433,8);
            jalapeno.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Jalapeno;
            });
            getLayeredPane().add(jalapeno,new Integer(3));
        }

        if (cardPotato){
            PlantCard potatomine = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_potatomine.png")).getImage());
            potatomine.setLocation(498,8);
            potatomine.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.PotatoMine;
            });
            getLayeredPane().add(potatomine,new Integer(3));
        }
        
        getLayeredPane().add(sun,new Integer(2));
        setResizable(false);
        setVisible(true);
    }

    public PlantVsZombie(boolean bool) {
        MainMenu menu = new MainMenu();
        menu.setLocation(0,0);
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu,new Integer(0));
        menu.repaint();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void unlock(int level){
        switch(level){
            case 2:
                cardFreezePeashooter = true;
                break;
            case 3:
                cardRepeater = true;
                break;
            case 4:
                cardJalapeno = true;
                break;
            case 5:
                cardPotato = true;
                break;
        }
    }

    static PlantVsZombie gameMenu;
    
    public static void begin() {
        gameMenu.dispose();
        gameMenu = new PlantVsZombie();
        gameMenu.setLocationRelativeTo(null);
        DataLevel.write("1");
    }
    public static void main(String[] args) {
        gameMenu = new PlantVsZombie(true);   
    }

}
