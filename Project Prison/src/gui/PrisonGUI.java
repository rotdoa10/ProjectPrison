package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class PrisonGUI {
    
    public void start()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(2,5));
        frame.add(mainpanel,BorderLayout.CENTER);
       
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("start");
        JMenuItem item = new JMenuItem("start");
        menu.add(item);
        menubar.add(menu);
        frame.add(menubar,BorderLayout.NORTH);

        for (int i = 0; i < 10; i++) {

            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    super.paintComponent(g);
                    g2d.setColor(Color.darkGray);
                    g2d.setStroke(new BasicStroke(5.0f));
                    g2d.drawRect(10, 30, 180, 280);
                }
            };
            panel.setLayout(new BorderLayout());
            panel.setName("Zelle" + (i + 1));
            panel.setBorder(new TitledBorder("Zelle" + (i + 1)));
            
            JButton btn = new JButton();
            ImageIcon image = new ImageIcon(getClass().getResource("/pics/tuer.jpg"));
            
            btn.setIcon(image);
            panel.add(btn,BorderLayout.SOUTH);
            
            mainpanel.add(panel);
            frame.validate();
            frame.repaint();
        }
    
    }

    public static void main(String[] args) {
        PrisonGUI p = new PrisonGUI();p.start();
    }

}
