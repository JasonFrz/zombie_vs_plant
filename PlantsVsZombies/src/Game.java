import javax.swing.*;
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
    Image pea2Image;
    Image repeaterImage;

    Image normalZombieImage;
    Image coneHeadZombieImage;
    Image bucketheadZombieImage;
    Image footballZombieImage;
    Image gargantuarZombieImage;
    Collider[] OnFirst;
    
    ArrayList<ArrayList<Zombie>> laneZombies;
    ArrayList<ArrayList<Pea>> lanePeas;
    ArrayList<Sun> activeSuns;

    // sementara
    ArrayList<Plant> plants = new ArrayList<>();

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

    // ini sementara
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Game(JLabel sunScoreboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;
        setSunScore(1000);  

        backgroundImg  = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peashooterImage = new ImageIcon(this.getClass().getResource("images/plants/peashooter.png")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("images/plants/SnowPea.png")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        pea2Image = new ImageIcon(this.getClass().getResource("images/pea_2.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();
        wallnutImage = new ImageIcon(this.getClass().getResource("images/plants/wallnut.png")).getImage();
        repeaterImage = new ImageIcon(this.getClass().getResource("images/plants/repeater1.png")).getImage();

        // ImageIcon[] normalZombieImages = new ImageIcon[22];
        // for (int i = 0; i < 21; i++) {
        //     normalZombieImages[i] = new ImageIcon(this.getClass().getResource("images/zombie/normal" + i + ".png"));
        // }
        // int frameDelay = 100;
        // animateSprite normalZombieSprite = new animateSprite(normalZombieImages, frameDelay);
        // add(normalZombieSprite);

        normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/NormalZombie.png")).getImage();
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

        // untuk pause sunflower tapi masi gagal
        // for (Plant plant : plants) {
        //     if (plant instanceof Sunflower) {
        //         ((Sunflower) plant).pause();
        //     }
        // }
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

        // utk resume sunflower tapi masi gagal
        // for (Plant plant : plants) {
        //     if (plant instanceof Sunflower) {
        //         ((Sunflower) plant).resume();
        //     }
        // }
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
        g.drawImage(backgroundImg,0,0,null);

        //Draw Plants
        for (int i = 0; i < 45; i++) {
            Collider c = OnFirst[i];
            if(c.assignedPlant != null){
                Plant p = c.assignedPlant;
                if(p instanceof Peashooter){
                    g.drawImage(peashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof FreezePeashooter){
                    g.drawImage(freezePeashooterImage,48 + (i%9)*100,110 + (i/9)*120,null);
                }
                if(p instanceof Sunflower){
                    g.drawImage(sunflowerImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof Wallnut){
                    g.drawImage(wallnutImage,45 + (i%9)*100,110 + (i/9)*120,null);
                }
                if(p instanceof repeater){
                    g.drawImage(repeaterImage,65 + (i%9)*100,129 + (i/9)*120,null);
                }
            }
        }

        // Draw Zombie
        for (int i = 0; i < 5 ; i++) {
            for(Zombie z : laneZombies.get(i)){
                if(z instanceof NormalZombie){
                    g.drawImage(normalZombieImage,z.posX,109+(i*120),null);
                }else if(z instanceof ConeHeadZombie){
                    g.drawImage(coneHeadZombieImage,z.posX,109+(i*120),null);
                }else if(z instanceof BucketHeadZombie){
                    g.drawImage(bucketheadZombieImage,z.posX,109+(i*120),null);
                }else if(z instanceof FootballZombie){
                    g.drawImage(footballZombieImage,z.posX,109+(i*120),null);
                }else if(z instanceof gargantuar){
                    g.drawImage(gargantuarZombieImage,z.posX,109+(i*120),null);
                }
            }

            // Draw Ball
            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
                
                if(p instanceof FreezePea){
                    g.drawImage(freezePeaImage, p.posX, 140 + (i * 120), null);
                } else if (p instanceof Pea) {
                    g.drawImage(peaImage, p.posX, 130 + (i * 120), null);
                } else if (p instanceof DoublePea) {
                    g.drawImage(pea2Image, p.posX, 130 + (i * 120), null);
                }
            }

        }
    }

    // ini method yang di panggil kalau tombol next di pencet
    // public void nextLevel() {
    //     progress = 0; // Reset progress
    //     int currentLevel = Integer.parseInt(DataLevel.Lvl);
    //     int nextLevel = currentLevel + 1;
    
    //     if (nextLevel > 4) {
    //         // Jika level berikutnya melebihi level maksimum, reset ke level pertama atau keluar
    //         nextLevel = 1;
    //     }
    
    //     DataLevel.write(String.valueOf(nextLevel));
    //     resetGame();
    // }

    // private void resetGame() {
    //     // Hentikan semua timer
    //     redrawTimer.stop();
    //     advancerTimer.stop();
    //     sunProducer.stop();
    //     spawnZombie.stop();
    
    //     // Hapus semua objek yang ada
    //     for (Plant plant : plants) {
    //         remove(plant);
    //     }
    //     plants.clear();
    
    //     for (Sun sun : activeSuns) {
    //         remove(sun);
    //     }
    //     activeSuns.clear();
    
    //     for (ArrayList<Zombie> lane : laneZombies) {
    //         for (Zombie z : lane) {
    //             remove(z);
    //         }
    //         lane.clear();
    //     }
    
    //     for (ArrayList<Pea> lane : lanePeas) {
    //         for (Pea p : lane) {
    //             remove(p);
    //         }
    //         lane.clear();
    //     }
    
    //     // Setel ulang matahari dan skor
    //     setSunScore(1000); // Anda dapat mengatur nilai awal yang diinginkan
    
    //     // Mulai ulang timer
    //     redrawTimer.start();
    //     advancerTimer.start();
    //     sunProducer.start();
    //     spawnZombie.start();
    // }

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
                // ((Game) getParent()).nextLevel();
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
                    addPlant(new Sunflower(Game.this, x, y));
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
    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if(progress>=150) {
            if("1".equals(DataLevel.Lvl)) {
                JOptionPane.showMessageDialog(null,"Anda Menang" + '\n' + "Next Level");
                PlantVsZombie.gameMenu.dispose();
                DataLevel.write("2");
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }  
            else if("2".equals(DataLevel.Lvl)) {
                JOptionPane.showMessageDialog(null,"Anda Menang" + '\n' + "Next Level");
                PlantVsZombie.gameMenu.dispose();
                DataLevel.write("3");
                PlantVsZombie.gameMenu = new PlantVsZombie();
            }  
            else {
                JOptionPane.showMessageDialog(null,"Anda Menang");
                DataLevel.write("1");
                System.exit(0);
            }
            progress = 0;
        }
    }
}
