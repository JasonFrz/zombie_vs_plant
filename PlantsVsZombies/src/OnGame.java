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


public class OnGame extends JLayeredPane implements MouseMotionListener {

    Image backgroundImg;
    Image peashooterImage;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;
    Image wallnutImage;
    Image pea2Image;

    Image normalZombieImage;
    Image coneHeadZombieImage;
    Image bucketheadZombieImage;
    Image footballZombieImage;
    Image gargantuarZombieImage;
    Utama[] OnFirst;
    
    ArrayList<ArrayList<Zombie>> laneZombies;
    ArrayList<ArrayList<Pea>> lanePeas;
    ArrayList<Sun> activeSuns;

    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunProducer;
    Timer zombieProducer;
    JLabel sunScoreboard;

    GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;

    int mouseX , mouseY;

    private int sunScore;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public OnGame(JLabel sunScoreboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;
        setSunScore(150);  

        backgroundImg  = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peashooterImage = new ImageIcon(this.getClass().getResource("images/plants/peashooter.png")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("images/plants/freezepeashooter.png")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        pea2Image = new ImageIcon(this.getClass().getResource("images/pea_2.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("images/freezepea.png")).getImage();
        wallnutImage = new ImageIcon(this.getClass().getResource("images/plants/wallnut.png")).getImage();

        normalZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/normalzombie.gif")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie2.png")).getImage();
        bucketheadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie3.png")).getImage();
        footballZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie4.png")).getImage();
        gargantuarZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/zombie5.png")).getImage();

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

        OnFirst = new Utama[45];
        for (int i = 0; i < 45; i++) {
            Utama a = new Utama();
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
            Random rnd = new Random();
            Sun sta = new Sun(this,rnd.nextInt(800)+100,0,rnd.nextInt(300)+200);
            activeSuns.add(sta);
            add(sta,new Integer(1));
        });
        sunProducer.start();

        zombieProducer = new Timer(7000,(ActionEvent e) -> {
            Random rnd = new Random();
            LevelData lvl = new LevelData();
            String [] Level = lvl.Level[Integer.parseInt(lvl.Lvl)-1];
            int [][] LevelValue = lvl.LevelValue[Integer.parseInt(lvl.Lvl)-1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
            for(int i = 0;i<LevelValue.length;i++) {
                if(t>=LevelValue[i][0]&&t<=LevelValue[i][1]) {
                    z = Zombie.getZombie(Level[i],OnGame.this,l);
                }
            }
            laneZombies.get(l).add(z);
        });
        zombieProducer.start();

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
    //tes tes tes
    //tesssss

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg,0,0,null);

        //Draw Plants
        for (int i = 0; i < 45; i++) {
            Utama c = OnFirst[i];
            if(c.assignedPlant != null){
                Plant p = c.assignedPlant;
                if(p instanceof Peashooter){
                    g.drawImage(peashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof FreezePeashooter){
                    g.drawImage(freezePeashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof Sunflower){
                    g.drawImage(sunflowerImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(p instanceof Wallnut){
                    g.drawImage(wallnutImage,60 + (i%9)*100,120 + (i/9)*120,null);
                }
            }
        }

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
                }
                else if(z instanceof gargantuar){
                    g.drawImage(gargantuarZombieImage,z.posX,109+(i*120),null);
                }
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
                if(p instanceof FreezePea){
                    g.drawImage(freezePeaImage, p.posX, 130 + (i * 120), null);
                }else {
                    g.drawImage(peaImage, p.posX, 130 + (i * 120), null);
                }
            }

        }

        //if(!"".equals(activePlantingBrush)){
            //System.out.println(activePlantingBrush);
            /*if(activePlantingBrush == GameWindow.PlantType.Sunflower) {
                g.drawImage(sunflowerImage,mouseX,mouseY,null);
            }*/

        //}


    }

    class PlantActionListener implements ActionListener {

        int x,y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(activePlantingBrush == GameWindow.PlantType.Sunflower){
                if(getSunScore()>=50) {
                    OnFirst[x + y * 9].setPlant(new Sunflower(OnGame.this, x, y));
                    setSunScore(getSunScore()-50);
                }
            }
            if(activePlantingBrush == GameWindow.PlantType.Peashooter){
                if(getSunScore() >= 100) {
                    OnFirst[x + y * 9].setPlant(new Peashooter(OnGame.this, x, y));
                    setSunScore(getSunScore()-100);
                }
            }

            if(activePlantingBrush == GameWindow.PlantType.FreezePeashooter){
                if(getSunScore() >= 175) {
                    OnFirst[x + y * 9].setPlant(new FreezePeashooter(OnGame.this, x, y));
                    setSunScore(getSunScore()-175);
                }
            }

            if(activePlantingBrush == GameWindow.PlantType.Wallnut){
                if(getSunScore() >= 50) {
                    OnFirst[x + y * 9].setPlant(new Wallnut(OnGame.this, x, y));
                    setSunScore(getSunScore()-50);
                }
            }
            activePlantingBrush = GameWindow.PlantType.None;
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
    static int progress = 0;
    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if(progress>=150) {
            if("1".equals(LevelData.Lvl)) {
                JOptionPane.showMessageDialog(null,"Anda Menang" + '\n' + "Next Level");
                GameWindow.gameMenu.dispose();
                LevelData.write("2");
                GameWindow.gameMenu = new GameWindow();
            }  
            else if("2".equals(LevelData.Lvl)) {
                JOptionPane.showMessageDialog(null,"Anda Menang" + '\n' + "Next Level");
                GameWindow.gameMenu.dispose();
                LevelData.write("3");
                GameWindow.gameMenu = new GameWindow();
            }  
            else {
                JOptionPane.showMessageDialog(null,"Anda Menang");
                LevelData.write("1");
                System.exit(0);
            }
           progress = 0;
        }
    }
}
