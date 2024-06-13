import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zombie {
    Image backgroundImage;

    public int health = 1000;
    public int speed = 1;

    private Game game;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;

    public Zombie(Game parent, int lane) {
        this.game = parent;
        myLane = lane;
    }

    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (game.OnFirst[i].assignedPlant != null && game.OnFirst[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = game.OnFirst[i];
                }
            }
            if (!isCollides) {
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                } else {
                    posX -= 1;
                }
            } else {
                collided.assignedPlant.health -= 5;
                if (collided.assignedPlant.health < 0) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                // JOptionPane.showMessageDialog(game,"ZOMBIE MAKAN OTAK MU :P");
                showGameOverPanel();
                PlantVsZombie.gameMenu.dispose();
                // PlantVsZombie.gameMenu = new PlantVsZombie();
            }
        }
    }

    int slowInt = 0;

    public void slow() {
        slowInt = 1000;
    }

    public static Zombie getZombie(String type, Game parent, int lane) {
        Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "NormalZombie":
                z = new NormalZombie(parent, lane);
                break;
            case "ConeHeadZombie":
                z = new ConeHeadZombie(parent, lane);
                break;
            case "BucketHeadZombie":
                z = new BucketHeadZombie(parent, lane);
                break;
            case "FootballZombie":
                z = new FootballZombie(parent, lane);
                break;
            case "Gargantuar":
                z = new gargantuar(parent, lane);
                break;
        }
        return z;
    }

    private void showGameOverPanel() {
        backgroundImage = new ImageIcon(this.getClass().getResource("images/GameOver.png")).getImage();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);

        // Buat panel latar belakang dengan gambar atau warna
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Gambar background di sini
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        // Tambahkan tombol ke panel latar belakang
        JButton exit1Button = new JButton("Exit");
        exit1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        exit1Button.setBounds(360, 275, 100, 23);
        backgroundPanel.add(exit1Button);

        JButton exit2Button = new JButton("Exit");
        exit2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        exit2Button.setBounds(217, 275, 100, 23);
        backgroundPanel.add(exit2Button);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    public int getX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }

}
