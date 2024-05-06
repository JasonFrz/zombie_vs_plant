import javax.swing.*;
import java.awt.event.ActionEvent;


public class PlantVsZombie extends JFrame {

    enum PlantType{
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter,
        Wallnut,
        repeater,
        jalapeno,
        potatomine
        
    }

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
        
        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110,8);
        sunflower.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.Sunflower;
        });
        getLayeredPane().add(sunflower,new Integer(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175,8);
        peashooter.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.Peashooter;
        });
        getLayeredPane().add(peashooter,new Integer(3));

        PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
        freezepeashooter.setLocation(240,8);
        freezepeashooter.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.FreezePeashooter;
        });
        getLayeredPane().add(freezepeashooter,new Integer(3));

        PlantCard wallnut = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_wallnut.png")).getImage());
        wallnut.setLocation(305,8);
        wallnut.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.Wallnut;
        });
        getLayeredPane().add(wallnut,new Integer(3));

        PlantCard repeater = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_repeater.png")).getImage());
        repeater.setLocation(370,8);
        repeater.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.repeater;
        });
        getLayeredPane().add(repeater,new Integer(3));

        PlantCard jalapeno = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_jalapeno.png")).getImage());
        jalapeno.setLocation(433,8);
        jalapeno.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.jalapeno;
        });
        getLayeredPane().add(jalapeno,new Integer(3));

        PlantCard potatomine = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_potatomine.png")).getImage());
        potatomine.setLocation(498,8);
        potatomine.setAction((ActionEvent e) -> {
            onGame.activePlantingBrush = PlantType.potatomine;
        });
        getLayeredPane().add(potatomine,new Integer(3));

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
    }
    public static void main(String[] args) {
        gameMenu = new PlantVsZombie(true);   
    }

}
