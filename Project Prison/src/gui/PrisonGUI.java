package gui;

import beans.Prisoner;
import database.DBAccess;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class PrisonGUI implements ActionListener {
    
    LinkedList<JPanel> panelFeld = new LinkedList<JPanel>();
    LinkedList<JButton> buttonFeld = new LinkedList<JButton>();
    private int anzahl = 10;
    private JFrame frame;
    
    public void start()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(2,(anzahl/2)));
        frame.add(mainpanel,BorderLayout.CENTER);
       
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("start");
        JMenuItem item = new JMenuItem("zur Datenbank verbinden");
        item.addActionListener(this);
        item.setActionCommand("actionItem");
        menu.add(item);
        menubar.add(menu);
        frame.add(menubar,BorderLayout.NORTH);

        for (int i = 0; i < anzahl; i++) {

            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    super.paintComponent(g);
                    g2d.setColor(Color.darkGray);
                    g2d.setStroke(new BasicStroke(5.0f));
                    
                    g2d.drawRect(10, 18, this.getWidth()-20, this.getHeight()-100);
                }
            };
            panel.setLayout(new BorderLayout());
            panel.setName("Zelle" + (i + 1));
            panel.setBorder(new TitledBorder("Zelle" + (i + 1)));
            
            JButton btn = new JButton();
            ImageIcon image = new ImageIcon(getClass().getResource("/pics/tuer.jpg"));
            
            btn.setIcon(image);
            panel.add(btn,BorderLayout.SOUTH);
            panelFeld.add(panel);
            
            mainpanel.add(panel);
            frame.validate();
            frame.repaint();
        }
    
    }

    public static void main(String[] args) {
        PrisonGUI p = new PrisonGUI();
        p.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("actionItem"))
        {
            try {
                DBAccess a = new DBAccess();
                LinkedList<Prisoner> list = a.getPrisoners();
                for(int i = 0; i < list.size(); i++)
                {
                    int zelleNR = list.get(i).getCellID();
                    System.out.println("NR: "+zelleNR);

                    for(int j = 0; j < panelFeld.size(); j++)
                    {
                        if(("Zelle"+zelleNR).equals(panelFeld.get(j).getName()))
                        {
                            System.out.println("hallo");
                            JButton btn = new JButton();
                            btn.setText(list.get(i).getNachname());
                            
                            btn.setBounds(5, 15, panelFeld.get(j).getWidth()-30, panelFeld.get(j).getHeight()-110);
                            panelFeld.get(j).add(btn,BorderLayout.NORTH);
                            buttonFeld.add(btn);
                            frame.validate();
                            frame.repaint();
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
