import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// import javafx.event.ActionEvent;

public class PickPlants extends JPanel {
    private Image pickImage;

    public PickPlants() {
        setSize(650, 785);
        pickImage = new ImageIcon(this.getClass().getResource("images/PickPlant.png")).getImage();
        setLayout(null);

        if (PlantVsZombie.showCardSunflower) {
            JButton pickSunflower = new JButton();
            pickSunflower.setBounds(40, 55, 66, 95);
            pickSunflower.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower2.png")));
            pickSunflower.setContentAreaFilled(false);
            pickSunflower.setBorderPainted(false);
            pickSunflower.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardSunflower = true;
                }
            });
            add(pickSunflower);
        }

        if (PlantVsZombie.showCardPeashooter) {
            JButton pickPeashooter = new JButton();
            pickPeashooter.setBounds(143, 55, 66, 95);
            pickPeashooter.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_Peashooter2.png")));
            pickPeashooter.setContentAreaFilled(false);
            pickPeashooter.setBorderPainted(false);
            pickPeashooter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardPeashooter = true;
                }
            });
            add(pickPeashooter);
        }

        if (PlantVsZombie.showCardWallnut) {
            JButton pickWallnut = new JButton();
            pickWallnut.setBounds(246, 55, 66, 95);
            pickWallnut.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_Wallnut2.png")));
            pickWallnut.setContentAreaFilled(false);
            pickWallnut.setBorderPainted(false);
            pickWallnut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardWallnut = true;
                }
            });
            add(pickWallnut);
        }

        if (PlantVsZombie.showCardFreezePeashooter) {
            JButton pickFreezePeashooter = new JButton();
            pickFreezePeashooter.setBounds(349, 55, 66, 95);
            pickFreezePeashooter.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_FreezePeashooter2.png")));
            pickFreezePeashooter.setContentAreaFilled(false);
            pickFreezePeashooter.setBorderPainted(false);
            pickFreezePeashooter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardFreezePeashooter = true;
                }
            });
            add(pickFreezePeashooter);
        }

        if (PlantVsZombie.showCardRepeater) {
            JButton pickRepeater = new JButton();
            pickRepeater.setBounds(40, 169, 66, 95);
            pickRepeater.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_Repeater2.png")));
            pickRepeater.setContentAreaFilled(false);
            pickRepeater.setBorderPainted(false);
            pickRepeater.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardRepeater = true;
                }
            });
            add(pickRepeater);
        }

        if (PlantVsZombie.showCardJalapeno) {
            JButton pickJalapeno = new JButton();
            pickJalapeno.setBounds(143, 169, 66, 95);
            pickJalapeno.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_Jalapeno2.png")));
            pickJalapeno.setContentAreaFilled(false);
            pickJalapeno.setBorderPainted(false);
            pickJalapeno.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardJalapeno = true;
                }
            });
            add(pickJalapeno);
        }

        if (PlantVsZombie.showCardPotato) {
            JButton pickPotato = new JButton();
            pickPotato.setBounds(246, 169, 66, 95);
            pickPotato.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_potatomine2.png")));
            pickPotato.setContentAreaFilled(false);
            pickPotato.setBorderPainted(false);
            pickPotato.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardPotato = true;
                }
            });
            add(pickPotato);
        }

        if (PlantVsZombie.showCardCherryBomb) {
            JButton pickCherryBomb = new JButton();
            pickCherryBomb.setBounds(349, 169, 66, 95);
            pickCherryBomb.setIcon(new ImageIcon(this.getClass().getResource("images/cards/card_CherryBomb2.png")));
            pickCherryBomb.setContentAreaFilled(false);
            pickCherryBomb.setBorderPainted(false);
            pickCherryBomb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlantVsZombie.cardCherryBomb = true;
                }
            });
            add(pickCherryBomb);
        }

        JButton letsRock = new JButton();
        letsRock.setBounds(150, 445, 150, 35);
        letsRock.setOpaque(false);
        letsRock.setContentAreaFilled(false);
        letsRock.setBorderPainted(false);
        letsRock.setFocusPainted(false);
        letsRock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlantVsZombie.begin();
            }
        });
        add(letsRock);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pickImage, 0, 0, getWidth(), getHeight(), this);
    }
}
