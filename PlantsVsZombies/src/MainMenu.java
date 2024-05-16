
import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {

    Image bgImage;
    private JButton menuButton;

    public MainMenu() {
        initComponents();
        setSize(1012, 785);
        bgImage  = new ImageIcon(this.getClass().getResource("images/menu.jpg")).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,null);
        
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        // menuButton = new JButton("Menu");

        setPreferredSize(new java.awt.Dimension(1012, 785));

        jPanel1.setOpaque(false);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        // menuButton.setBounds(900, 10, 80, 30); // Set button bounds
        // menuButton.addActionListener(e -> {
        //     // Add action listener for menu button
        //     JDialog dialog = new JDialog();
        //     dialog.setSize(200, 200);
        //     dialog.setLayout(new GridLayout(3, 1));
        //     JButton save = new JButton("Save");
        //     JButton pause = new JButton("Pause");
        //     JButton exit = new JButton("Exit");

        //     save.addActionListener(e1 -> {
        //         DataLevel.write("1");
        //     });

        //     exit.addActionListener(e1 -> {
        //         System.exit(0);
        //     });

        //     dialog.add(save);
        //     dialog.add(pause);
        //     dialog.add(exit);
        //     dialog.setVisible(true);
        // });
        // add(menuButton);
        // add(jPanel1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(523, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(547, Short.MAX_VALUE))
        );
    }

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {
        PlantVsZombie.begin();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
