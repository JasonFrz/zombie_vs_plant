import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;

// import javafx.scene.chart.PieChart.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Game extends JLayeredPane implements MouseMotionListener {

    Image backgroundImg;
    Image peashooterImage;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;
    Image wallnutImage;
    Image repeaterImage;
    Image potatoMineImage;
    Image jalapenoImage;
    Image cheriBombImage;

    Image winImage;

    Image normalZombieImage;
    Image coneHeadZombieImage;
    Image bucketheadZombieImage;
    Image footballZombieImage;
    Image gargantuarZombieImage;
    Collider[] OnFirst;
    
    ArrayList<ArrayList<Zombie>> laneZombies;
    ArrayList<ArrayList<Pea>> lanePeas;
    ArrayList<Sun> activeSuns;

    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunProducer;
    Timer spawnZombie;
    JLabel sunScoreboard;

    PlantVsZombie.PlantType activePlantingBrush = PlantVsZombie.PlantType.None;

    int mouseX , mouseY;

    private int sunScore;
    private JButton menuButton;
    private OptionsMenu optionsMenu;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public Game(JLabel sunScoreboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;
        setSunScore(1000);  

        backgroundImg  = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peashooterImage = new ImageIcon(this.getClass().getResource("images/plants/peashooter1.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("images/plants/SnowPea.png")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();
        wallnutImage = new ImageIcon(this.getClass().getResource("images/plants/wallnut.gif")).getImage();
        repeaterImage = new ImageIcon(this.getClass().getResource("images/plants/repeater.gif")).getImage();
        potatoMineImage = new ImageIcon(this.getClass().getResource("images/plants/PotatoMine1.png")).getImage();
        jalapenoImage = new ImageIcon(this.getClass().getResource("images/plants/jalapeno.gif")).getImage();
        cheriBombImage = new ImageIcon(this.getClass().getResource("images/plants/CherryBomb.png")).getImage();

        normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/NormalZombiee.gif")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/ConeheadZombie.png")).getImage();
        bucketheadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/BuckheadZombie.png")).getImage();
        footballZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/FootballZombie.png")).getImage();
        gargantuarZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/Gargantuar.png")).getImage();

        laneZombies = new ArrayList<>();
        laneZombies.add(new ArrayList<>());
        laneZombies.add(new ArrayList<>());
        laneZombies.add(new ArrayList<>());
        laneZombies.add(new ArrayList<>()); 
        laneZombies.add(new ArrayList<>());

        lanePeas = new ArrayList<>();
        lanePeas.add(new ArrayList<>()); 
        lanePeas.add(new ArrayList<>());
        lanePeas.add(new ArrayList<>()); 
        lanePeas.add(new ArrayList<>()); 
        lanePeas.add(new ArrayList<>()); 

        OnFirst = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i%9)*100,109 + (i/9)*120);
            a.setAction(new PlantActionListener((i%9),(i/9)));
            OnFirst[i] = a;
            add(a,new Integer(0));
        }

        activeSuns = new ArrayList<>();

        redrawTimer = new Timer(25,(ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        advancerTimer = new Timer(60,(ActionEvent e) -> advance());
        advancerTimer.start();

        sunProducer = new Timer(5000,(ActionEvent e) -> {
            Random random = new Random();
            Sun sun = new Sun(this,random.nextInt(800)+100,0,random.nextInt(300)+200);
            activeSuns.add(sun);
            add(sun,new Integer(1));
        });
        sunProducer.start();

        spawnZombie = new Timer(7000,(ActionEvent e) -> {
            Random random = new Random();
            DataLevel lvl = new DataLevel();
            String [] jnsZombie = lvl.jenisZombie[Integer.parseInt(lvl.Lvl)-1];
            int [][] persen = lvl.Persentase[Integer.parseInt(lvl.Lvl)-1];
            int lane = random.nextInt(5);
            int prsn = random.nextInt(100);
            Zombie zombie = null;
            for(int i = 0; i < persen.length; i++) {
                if(prsn >= persen[i][0] && prsn <= persen[i][1]) {
                    zombie = Zombie.getZombie(jnsZombie[i],Game.this,lane);
                }
            }
            laneZombies.get(lane).add(zombie);
        });
        spawnZombie.start();

        optionsMenu = new OptionsMenu();
        optionsMenu.setBounds(250, 120, 500, 500);
        add(optionsMenu, new Integer(2));
        optionsMenu.setVisible(false);

        menuButton = new JButton("Menu");
        menuButton.setBounds(855, 0, 140, 35);
        menuButton.addActionListener(e -> showOptionsMenu());
        
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Arial", Font.BOLD, 18));
        add(menuButton, new Integer(1));
    }

    // untuk melakukan pengecekan apakah terdapat zombie pada posisi tertentu
    public boolean isZombieAt(int x, int y) {
        for (ArrayList<Zombie> lane : laneZombies) {
            for (Zombie z : lane) {
                if (z.posX == x && z.myLane == y) {
                    return true;
                }
            }
        }
        return false;
    }

    // untuk membunuh zombie pada posisi tertentu
    public void killZombieAt(int x, int y) {
        for (ArrayList<Zombie> lane : laneZombies) {
            for (Zombie z : lane) {
                if (z.posX == x && z.myLane == y) {
                    lane.remove(z);
                    return;
                }
            }
        }
    }

    // untuk meremove plant potatomine
    public void removePlant(Plant plant) {
        for (int i = 0; i < 45; i++) {
            if (OnFirst[i].assignedPlant == plant) {
                OnFirst[i].removePlant();
                return;
            }
        }
    }
    
    public void clearLaneOfZombies(int lane) {
        laneZombies.get(lane).clear();
    }

    private void showOptionsMenu() {
        pauseGame();
        optionsMenu.setVisible(true);
    }

    public void pauseGame() {
        redrawTimer.stop();
        advancerTimer.stop();
        sunProducer.stop();
        spawnZombie.stop();

        for (ArrayList<Zombie> lane : laneZombies) {
            for (Zombie z : lane) {
                z.isMoving = false;
            }
        }

        for (Sun s : activeSuns) {
            s.pause();
        }

        for (int i = 0; i < OnFirst.length; i++) {
            if (OnFirst[i].assignedPlant instanceof Sunflower) {
                ((Sunflower) OnFirst[i].assignedPlant).pause();
            }
            else if (OnFirst[i].assignedPlant instanceof Peashooter) {
                ((Peashooter) OnFirst[i].assignedPlant).pause();
            }
            else if (OnFirst[i].assignedPlant instanceof repeater) {
                ((repeater) OnFirst[i].assignedPlant).pause();
            }
            else if (OnFirst[i].assignedPlant instanceof FreezePeashooter) {
                ((FreezePeashooter) OnFirst[i].assignedPlant).pause();
            } 
            else if (OnFirst[i].assignedPlant instanceof Potatomine) {
                ((Potatomine) OnFirst[i].assignedPlant).pause();
            }
            
        }

    }

    public void resumeGame() {
        redrawTimer.start();
        advancerTimer.start();
        sunProducer.start();
        spawnZombie.start();
        

        for (ArrayList<Zombie> lane : laneZombies) {
            for (Zombie z : lane) {
                z.isMoving = true;
            }
        }

        for (Sun s : activeSuns) {
            s.resume();
        }

        for (int i = 0; i < OnFirst.length; i++) {
            if (OnFirst[i].assignedPlant instanceof Sunflower) {
                ((Sunflower) OnFirst[i].assignedPlant).resume();
            }
            else if (OnFirst[i].assignedPlant instanceof Peashooter) {
                ((Peashooter) OnFirst[i].assignedPlant).resume();
            }
            else if (OnFirst[i].assignedPlant instanceof repeater) {
                ((repeater) OnFirst[i].assignedPlant).resume();
            }
            else if (OnFirst[i].assignedPlant instanceof FreezePeashooter) {
                ((FreezePeashooter) OnFirst[i].assignedPlant).resume();
            }
            else if (OnFirst[i].assignedPlant instanceof Potatomine) {
                ((Potatomine) OnFirst[i].assignedPlant).resume();
            }
        }
    }

    public void nextLevel() {
        resetGame();

        progress = 0;
        int currentLevel = Integer.parseInt(DataLevel.Lvl);
        int nextLevell = currentLevel + 1;
    
        if (nextLevell > 5) {
            nextLevell = 1;
            PlantVsZombie.cardFreezePeashooter = false;
            PlantVsZombie.cardRepeater = false;
            PlantVsZombie.cardJalapeno = false;
            PlantVsZombie.cardPotato = false;
        }

        if (nextLevell == 2){
            PlantVsZombie.cardFreezePeashooter = true;
        } else if (nextLevell == 3){
            PlantVsZombie.cardRepeater = true;
        } else if (nextLevell == 4){
            PlantVsZombie.cardJalapeno = true;
        } else if (nextLevell == 5){
            PlantVsZombie.cardPotato = true;
        }

        DataLevel.write(String.valueOf(nextLevell));

        // ((Game) PlantVsZombie.gameMenu.getContentPane()).repaint();
        PlantVsZombie.gameMenu.dispose();
        PlantVsZombie.gameMenu = new PlantVsZombie();
    }

    private void resetGame() {
        redrawTimer.stop();
        advancerTimer.stop();
        sunProducer.stop();
        spawnZombie.stop();
    
        for (Collider collider : OnFirst) {
            if (collider.assignedPlant != null) {
                collider.removePlant();
            }
        }

        for (int i = 0; i < 5; i++) {
            laneZombies.get(i).clear();
            lanePeas.get(i).clear();
        }

        activeSuns.clear();
        setSunScore(1000);
    
        redrawTimer.start();
        advancerTimer.start();
        sunProducer.start();
        spawnZombie.start();

        this.revalidate();
        this.repaint();
    }

    private void advance(){
        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombies.get(i)){
                z.advance();
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
                p.advance();
            }

        }

        for (int i = 0; i < activeSuns.size() ; i++) {
            activeSuns.get(i).advance();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImg, 0, 0, null);

    // Draw Plants
    for (int i = 0; i < 45; i++) {
        Collider c = OnFirst[i];
        if (c.assignedPlant != null) {
            Plant p = c.assignedPlant;
            if (p instanceof Peashooter) {
                g.drawImage(peashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
            } else if (p instanceof FreezePeashooter) {
                g.drawImage(freezePeashooterImage, 48 + (i % 9) * 100, 110 + (i / 9) * 120, null);
            } else if (p instanceof Sunflower) {
                g.drawImage(sunflowerImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
            } else if (p instanceof Wallnut) {
                g.drawImage(wallnutImage, 50 + (i % 9) * 100, 120 + (i / 9) * 120, null);
            } else if (p instanceof repeater) {
                g.drawImage(repeaterImage, 33 + (i % 9) * 100, 100 + (i / 9) * 120, null);
            } else if(p instanceof Potatomine){
                g.drawImage(potatoMineImage, 45 + (i % 9) * 100, 129 + (i / 9) * 120, null);
            } else if(p instanceof jalapeno){
                g.drawImage(jalapenoImage, 58 + (i % 9) * 100, 129 + (i / 9) * 120, null);
            } else if (p instanceof CherryBomb) {
                g.drawImage(cheriBombImage, 48 + (i % 9) * 100, 115 + (i / 9) * 120, null);
            }
        }
    }

    // Draw Zombies
    for (int i = 0; i < 5; i++) {
        for (Zombie z : laneZombies.get(i)) {
            if (z instanceof NormalZombie) {
                g.drawImage(normalZombieImage, z.posX, 109 + (i * 120), null);
            } else if (z instanceof ConeHeadZombie) {
                g.drawImage(coneHeadZombieImage, z.posX, 109 + (i * 120), null);
            } else if (z instanceof BucketHeadZombie) {
                g.drawImage(bucketheadZombieImage, z.posX, 109 + (i * 120), null);
            } else if (z instanceof FootballZombie) {
                g.drawImage(footballZombieImage, z.posX, 109 + (i * 120), null);
            } else if (z instanceof gargantuar) {
                g.drawImage(gargantuarZombieImage, z.posX, 109 + (i * 120), null);
            }
        }

        // Draw Peas
        for (int j = 0; j < lanePeas.get(i).size(); j++) {
            Pea p = lanePeas.get(i).get(j);

            if (p instanceof FreezePea) {
                g.drawImage(freezePeaImage, p.posX, 140 + (i * 120), null);
            } else {
                g.drawImage(peaImage, p.posX, 130 + (i * 120), null);
            }
        }
    }

    // Draw Win Image if progress >= 150
    // if (progress >= 150) {
    //     g.drawImage(winImage, 250, 150, null); // Adjust the position as needed
    // }
}

    

    class OptionsMenu extends JPanel {
        private Image pauseImage;
        
        public OptionsMenu() {
            setLayout(null);
            pauseImage = new ImageIcon(this.getClass().getResource("images/Pause.png")).getImage();
            JButton resume = new JButton("Resume");
            JButton next = new JButton("Next");

            resume.addActionListener((ActionEvent e) -> {
                setVisible(false);
                ((Game) getParent()).resumeGame();
            });
            next.addActionListener((ActionEvent e) -> {
                setVisible(false);
                ((Game) getParent()).nextLevel();
            });

            resume.setBounds(55, 425, 193, 45);
            next.setBounds(255, 425, 193, 45);

            resume.setOpaque(false);
            resume.setContentAreaFilled(false);
            resume.setBorderPainted(false);
            resume.setFocusPainted(false);

            resume.setForeground(new Color(0, 0, 0, 0));
            next.setForeground(new Color(0, 0, 0, 0));

            next.setOpaque(false);
            next.setContentAreaFilled(false);
            next.setBorderPainted(false);
            next.setFocusPainted(false);

            add(resume);
            add(next);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(pauseImage, -249, -120, 1015, 760, this);
        }
    }

    // Card di click
    class PlantActionListener implements ActionListener {

        int x,y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(activePlantingBrush == PlantVsZombie.PlantType.Sunflower){
                if(getSunScore()>=50) {
                    OnFirst[x + y * 9].setPlant(new Sunflower(Game.this, x, y));
                    setSunScore(getSunScore()-50);
                }
            }
            if(activePlantingBrush == PlantVsZombie.PlantType.Peashooter){
                if(getSunScore() >= 100) {
                    OnFirst[x + y * 9].setPlant(new Peashooter(Game.this, x, y));
                    setSunScore(getSunScore()-100);
                }
            }

            if(activePlantingBrush == PlantVsZombie.PlantType.FreezePeashooter){
                if(getSunScore() >= 175) {
                    OnFirst[x + y * 9].setPlant(new FreezePeashooter(Game.this, x, y));
                    setSunScore(getSunScore()-175);
                }
            }

            if(activePlantingBrush == PlantVsZombie.PlantType.Wallnut){
                if(getSunScore() >= 50) {
                    OnFirst[x + y * 9].setPlant(new Wallnut(Game.this, x, y));
                    setSunScore(getSunScore()-50);
                }
            }

            if(activePlantingBrush == PlantVsZombie.PlantType.Repeater){
                if(getSunScore() >= 200) {
                    OnFirst[x + y * 9].setPlant(new repeater(Game.this, x, y));
                    setSunScore(getSunScore()-200);
                }
            }

            if(activePlantingBrush == PlantVsZombie.PlantType.PotatoMine){
                if(getSunScore() >= 25){
                    OnFirst[x + y * 9].setPlant(new Potatomine(Game.this, x, y));
                    setSunScore(getSunScore() - 25);
                }
            }
            if(activePlantingBrush == PlantVsZombie.PlantType.Jalapeno){
                if(getSunScore() >= 125){
                    OnFirst[x + y * 9].setPlant(new jalapeno(Game.this, x, y));
                    setSunScore(getSunScore() - 125);
                }
            }
            if (activePlantingBrush == PlantVsZombie.PlantType.CherryBomb) {
                if (getSunScore() >= 150) {
                    OnFirst[x + y * 9].setPlant(new CherryBomb(Game.this, x, y));
                    setSunScore(getSunScore() - 150);
                }
            }
            activePlantingBrush = PlantVsZombie.PlantType.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    // Level
    static int progress = 0;
    //public static void setProgress(int num) {
        //progress = progress + num;
        //System.out.println(progress);
        //if (progress >= 150) {
            //((Game) PlantVsZombie.gameMenu.getContentPane()).repaint(); 
        //}
    //}
    
    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        System.out.println(DataLevel.Lvl);
        if(progress>=150) {
            if("1".equals(DataLevel.Lvl)) {
                ((Game) PlantVsZombie.gameMenu.getContentPane()).repaint();
                PlantVsZombie.gameMenu.dispose();
                DataLevel.write("2");
                PlantVsZombie.cardFreezePeashooter = true;
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }  
            else if("2".equals(DataLevel.Lvl)) {
                ((Game) PlantVsZombie.gameMenu.getContentPane()).repaint();
                PlantVsZombie.gameMenu.dispose();
                DataLevel.write("3");
                PlantVsZombie.cardRepeater = true;
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }  
            else if("3".equals(DataLevel.Lvl)) {
                ((Game) PlantVsZombie.gameMenu.getContentPane()).repaint();
                PlantVsZombie.gameMenu.dispose();
                DataLevel.write("4");
                PlantVsZombie.cardJalapeno = true;
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }  
            else if("4".equals(DataLevel.Lvl)) {
                ((Game) PlantVsZombie.gameMenu.getContentPane()).repaint();
                PlantVsZombie.gameMenu.dispose();
                DataLevel.write("5");
                PlantVsZombie.cardPotato = true;
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }  
            else {
                ((Game) PlantVsZombie.gameMenu.getContentPane()).repaint();
                DataLevel.write("1");
                System.exit(0);
            }
            progress = 0;
        }
    }

}
