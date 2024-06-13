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
        PotatoMine,
        CherryBomb
    }

    static boolean cardSunflower = false;
    static boolean cardPeashooter = false;
    static boolean cardWallnut = false;
    static boolean cardFreezePeashooter = false;
    static boolean cardRepeater = false;
    static boolean cardJalapeno = false;
    static boolean cardPotato = false;
    static boolean cardCherryBomb = false;

    static boolean showCardSunflower = true;
    static boolean showCardPeashooter = true;
    static boolean showCardWallnut = true;
    static boolean showCardFreezePeashooter = false;
    static boolean showCardRepeater = false;
    static boolean showCardJalapeno = false;
    static boolean showCardPotato = false;
    static boolean showCardCherryBomb = false;

    int jarak = 65;
    int xCard = 110;
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
            sunflower.setLocation(xCard,8);
            sunflower.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Sunflower;
            });
            getLayeredPane().add(sunflower,new Integer(3));
            xCard += jarak;
        }
        
        if (cardPeashooter){
            PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_Peashooter.png")).getImage());
            peashooter.setLocation(xCard,8);
            peashooter.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Peashooter;
            });
            getLayeredPane().add(peashooter,new Integer(3));
            xCard += jarak;
        }

        if (cardWallnut){{
            PlantCard wallnut = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_wallnut.png")).getImage());
            wallnut.setLocation(xCard,8);
            wallnut.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Wallnut;
            });
            getLayeredPane().add(wallnut,new Integer(3));
            xCard += jarak;
        }}

        if (cardFreezePeashooter){
            PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
            freezepeashooter.setLocation(xCard,8);
            freezepeashooter.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.FreezePeashooter;
            });
            getLayeredPane().add(freezepeashooter,new Integer(3));
            xCard += jarak;
        }
        
        if (cardRepeater){
            PlantCard repeater = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_repeater.png")).getImage());
            repeater.setLocation(xCard,8);
            repeater.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Repeater;
            });
            getLayeredPane().add(repeater,new Integer(3));
            xCard += jarak;
        }

        if (cardJalapeno){
            PlantCard jalapeno = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_jalapeno.png")).getImage());
            jalapeno.setLocation(xCard,8);
            jalapeno.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.Jalapeno;
            });
            getLayeredPane().add(jalapeno,new Integer(3));
            xCard += jarak;
        }

        if (cardPotato){
            PlantCard potatomine = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_potatomine.png")).getImage());
            potatomine.setLocation(xCard,8);
            potatomine.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.PotatoMine;
            });
            getLayeredPane().add(potatomine,new Integer(3));
            xCard += jarak;
        }

        if (cardCherryBomb){
            PlantCard cherrybomb = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_cherrybomb.png")).getImage());
            cherrybomb.setLocation(xCard,8);
            cherrybomb.setAction((ActionEvent e) -> {
                onGame.activePlantingBrush = PlantType.CherryBomb;
            });
            getLayeredPane().add(cherrybomb,new Integer(3));
            xCard += jarak;
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
