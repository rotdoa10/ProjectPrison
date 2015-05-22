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
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PrisonGUI implements ActionListener {

    LinkedList<JPanel> panelFeld = new LinkedList<JPanel>();
    LinkedList<JPanel> zweitesPanelFeld = new LinkedList<JPanel>();
    LinkedList<JButton> buttonFeld = new LinkedList<JButton>();
    LinkedList<JButton> iconList = new LinkedList<JButton>();
    private int anzahl = 10;
    private JFrame frame;
    private LinkedList<Prisoner> list = new LinkedList<Prisoner>();
    private int index = 0;
    private int i = 0;
    private DBAccess a;
    private String user;
    private JPanel mainpanel;

    public void start(String username) {
        user = username;
        frame = new JFrame();
        frame.setTitle("Angemeldet als " + username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(2, (anzahl / 2)));

        frame.add(mainpanel, BorderLayout.CENTER);

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("start");
        JMenu menuA = new JMenu("aktualisieren");

        JMenuItem item = new JMenuItem("zur Datenbank verbinden");
        item.addActionListener(this);
        item.setActionCommand("actionItem");

        JMenuItem itemAdd = new JMenuItem("add Prisoner");
        itemAdd.addActionListener(this);
        itemAdd.setActionCommand("addItem");

        JMenuItem itemAktuali = new JMenuItem("aktualisieren");
        itemAktuali.addActionListener(this);
        itemAktuali.setActionCommand("aktualisierenAction");

        menu.add(item);
        menu.add(itemAdd);
        menuA.add(itemAktuali);
        menubar.add(menu);
        menubar.add(menuA);
        frame.add(menubar, BorderLayout.NORTH);

        for (int k = 0; k < anzahl; k++) {

            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    super.paintComponent(g);
                    g2d.setColor(Color.darkGray);
                    g2d.setStroke(new BasicStroke(5.0f));

                    g2d.drawRect(10, 18, this.getWidth() - 20, this.getHeight() - 50);
                }
            };
            panel.setLayout(new BorderLayout());
            panel.setName("Zelle" + (k + 1));
            panel.setBorder(new TitledBorder("Zelle" + (k + 1)));
            JPanel pan = new JPanel();
            pan.setSize(50, 50);
            pan.setName("pan" + (k + 1));
            pan.setLayout(new GridLayout(2, 1));

            this.zweitesPanelFeld.add(pan);

            panel.add(pan);
            panelFeld.add(panel);

            mainpanel.add(panel);
            JButton btn = new JButton();
            btn.setActionCommand("doorAction");
            btn.setName("doorbtn" + (k + 1));
            ImageIcon image = new ImageIcon(getClass().getResource("/pics/tuer.jpg"));
            btn.setIcon(image);
            iconList.add(btn);
            panel.add(btn, BorderLayout.SOUTH);

        }
        frame.validate();
        frame.repaint();
    }

    public static void main(String[] args) {
        PrisonGUI g = new PrisonGUI();
        g.start("aed");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("actionItem")) {
            try {

                a = new DBAccess();
                list = a.getPrisoners();
                for (index = 0; index < panelFeld.size(); index++) {
                    for (i = 0; i < list.size(); i++) {

                        int zelleNR = list.get(i).getCellID();

                        if (("Zelle" + zelleNR).equals(panelFeld.get(index).getName())) {
                            JButton btn = new JButton();
                            btn.setText(list.get(i).getNachname() + " " + list.get(i).getVorname());
                            btn.setBounds(5, 15, panelFeld.get(index).getWidth() - 30, panelFeld.get(index).getHeight() - 110);
                            btn.addActionListener(this);
                            btn.setActionCommand(list.get(i).getNachname() + " " + list.get(i).getVorname());
                            System.out.println("btn" + (i + 1));
                            btn.setName(list.get(i).getNachname() + " " + list.get(i).getVorname());
                            zweitesPanelFeld.get(index).add(btn);
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
        } else if (e.getActionCommand().equals("addItem")) {
            try {
                JDialog d = new AddPrisonerDialog(frame, true);
                d.setVisible(true);

                a = new DBAccess();
                list = a.getPrisoners();

            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equals("aktualisierenAction")) {
            try {  
                for (index = 0; index < panelFeld.size(); index++) {
                    for (i = 0; i < list.size(); i++) {

                        int zelleNR = list.get(i).getCellID();

                        if (("Zelle" + zelleNR).equals(panelFeld.get(index).getName())) {
                            JButton btn = new JButton();
                            btn.setText(list.get(i).getNachname() + " " + list.get(i).getVorname());
                            btn.setBounds(5, 15, panelFeld.get(index).getWidth() - 30, panelFeld.get(index).getHeight() - 110);
                            btn.addActionListener(this);
                            btn.setActionCommand("prisonerbtnAction");
                            System.out.println("btn" + (i + 1));
                            btn.setName("prisonerbtn" + (i + 1));
                            zweitesPanelFeld.get(index).add(btn);
                            buttonFeld.add(btn);
                            frame.validate();
                            frame.repaint();
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equals("doorAction")) {
            for (int j = 0; j < iconList.size(); j++) {
                if (iconList.get(j).getName().equals("doorbtn" + (j + 1))) {
                    //JDialog d = new DoorDialog(frame,true,list.get(i));
                    //d.setVisible(true);
                }
            }
        }

        for(Prisoner p : list)
        {
            if (e.getActionCommand().equals(p.getNachname()+" "+p.getVorname())) {

            
                                    
                try {
                    JDialog d = new PrisonerDialog(frame, true, p, a.getAuthortiy(user));
                    d.setVisible(true);
                    break;

                } catch (Exception ex) {
                    Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }


}
