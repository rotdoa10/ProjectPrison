/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class PP {
    
    public void start()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 800);
        frame.setLayout(new GridLayout(2, 5));

        for (int i = 0; i < 10; i++) {

            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.darkGray);
                    
                    g.drawRect(10, 30, 180, 280);
                }
            };
            panel.setLayout(new BorderLayout());
            panel.setName("Zelle" + (i + 1));
            panel.setBorder(new TitledBorder("Zelle" + (i + 1)));
            
            JButton btn = new JButton();
            ImageIcon image = new ImageIcon(getClass().getResource("/pics/tuer.jpg"));
            
            btn.setIcon(image);
            panel.add(btn,BorderLayout.SOUTH);
            
            frame.add(panel);
            frame.validate();
            frame.repaint();
        }
    
    }

    public static void main(String[] args) {
        PP p = new PP();p.start();
    }

}
