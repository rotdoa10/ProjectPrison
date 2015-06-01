package gui;

import beans.Prisoner;
import database.DBAccess;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 *
 * @author patzineubi
 */
public class PrisonGUI implements ActionListener {

    private LinkedList<Prisoner> list = new LinkedList<Prisoner>();
    private LinkedList<JPanel> panelFeld = new LinkedList<JPanel>();
    private LinkedList<JPanel> zweitesPanelFeld = new LinkedList<JPanel>();
    private LinkedList<JButton> buttonFeld = new LinkedList<JButton>();
    private LinkedList<JButton> iconList = new LinkedList<JButton>();

    // Anzahl der Zellen
    private int anzahl = 10;
    
    private JFrame frame;
    private int index = 0;
    private int i = 0;
    private DBAccess a;
    private String user;
    private JPanel mainpanel;

    /**
     * In dieser Methode start(), wird die Benutzeroberfläche generiert. Der zuvor in der LoginGUI eingebebe
     * Username wird übergeben.
     * @param username
     * @throws Exception 
     */
    public void start(String username) throws Exception {
        user = username;
        frame = new JFrame();
        frame.setTitle("angemeldet als: " + username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(2, (anzahl / 2)));
        a = new DBAccess();
        frame.add(mainpanel, BorderLayout.CENTER);

        
        // JMenuBar mit den zugehörigen JMenus und JMenuItems erstellen.
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("start");
        menu.setFont(new Font("Serif", Font.BOLD, 14));
        JMenu menuAusloggen = new JMenu("ausloggen");
        menuAusloggen.setFont(new Font("Serif", Font.ITALIC, 14));

        JMenuItem item = new JMenuItem("zur Datenbank verbinden");
        item.addActionListener(this);
        item.setActionCommand("start");

        JMenuItem itemAdd = new JMenuItem("add Prisoner");
        itemAdd.addActionListener(this);
        itemAdd.setActionCommand("addItem");

        JMenuItem itemAusloggen = new JMenuItem("ausloggen");
        itemAusloggen.addActionListener(this);
        itemAusloggen.setActionCommand("ausloggenAction");

        menu.add(item);
        menu.add(itemAdd);
        menuAusloggen.add(itemAusloggen);
        menubar.add(menu);
        menubar.add(menuAusloggen);

        frame.add(menubar, BorderLayout.NORTH);

        
        // Zellen werden gezeichnet
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
            panel.setName("Zelle " + (k + 1));
            panel.setBorder(new TitledBorder("Zelle " + (k + 1)));
            JPanel pan = new JPanel();
            pan.setSize(50, 50);
            pan.setName("pan" + (k + 1));
            pan.setLayout(new GridLayout(2, 1));

            this.zweitesPanelFeld.add(pan);
            panel.add(pan);
            panelFeld.add(panel);
            mainpanel.add(panel);
            mainpanel.setVisible(false);

            // Zellentür-Buttons werden erstellt.
            JButton btn = new JButton();
            ImageIcon image = new ImageIcon(getClass().getResource("/pics/tuer.jpg"));
            btn.setIcon(image);
            btn.addActionListener(this);
            btn.setBackground(Color.white);
            btn.setName("doorbtn " + (k + 1));
            btn.setActionCommand("doorbtn " + (k + 1));
            iconList.add(btn);

            panel.add(btn, BorderLayout.SOUTH);
        }
        frame.validate();
        frame.repaint();
    }

    /**
     * Main-Methode der PrisonGUI
     * @param args 
     */
    public static void main(String[] args) {
        try {
            PrisonGUI g = new PrisonGUI();
            g.start("prison");
        } catch (Exception ex) {
            Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * In dieser Methode aktualisieren() wird eine Liste der Häftlinge von der
     * DBAccess geholt und in der jeweiligen Zellen abgebildet.
     * @throws Exception 
     */
    public void aktualisieren() throws Exception {
        for (int j = 0; j < zweitesPanelFeld.size(); j++) {
            zweitesPanelFeld.get(j).removeAll();
        }

        list = a.getPrisoners();
        for (index = 0; index < panelFeld.size(); index++) {
            for (i = 0; i < list.size(); i++) {

                int zelleNR = list.get(i).getCellID();

                if (("Zelle " + zelleNR).equals(panelFeld.get(index).getName())) {
                    JButton btn = new JButton();
                    btn.setBackground(Color.white);
                    btn.setForeground(Color.black);
                    btn.setFont(new Font("Serif", Font.BOLD, 14));
                    btn.setText(list.get(i).getNachname() + " " + list.get(i).getVorname());
                    btn.setBounds(5, 15, panelFeld.get(index).getWidth() - 30, panelFeld.get(index).getHeight() - 110);
                    btn.addActionListener(this);
                    btn.setActionCommand(list.get(i).getNachname() + " " + list.get(i).getVorname());
                    btn.setName(list.get(i).getNachname() + " " + list.get(i).getVorname());
                    zweitesPanelFeld.get(index).add(btn);
                    buttonFeld.add(btn);
                    frame.validate();
                    frame.repaint();
                }
            }
        }
    }

    /**
     * Die verschiedenen zuvor vergebenen ActionCommands werden hier abgearbeitet.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            try {
                // Sichtbarsetzen der Zellen
                mainpanel.setVisible(true);
                aktualisieren();
            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equals("addItem")) {
            try {
                // JDialog AddPrisonerDialog wird aufgerufen, um einen neuen Prisoner hinzuzufügen.
                JDialog d = new AddPrisonerDialog(frame, true);
                d.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equals("ausloggenAction")) {
            try {
                // PrisonGUI wird geschlossen und ein neuer Versuch für das 
                // Einloggen kann gemacht werden.
                frame.dispose();
                LoginGUI l = new LoginGUI();
                l.show();
                a.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Bei Klicken auf die Zellentürbuttons öffnet sich ein der DoorDialog.
        for (JButton b : iconList) {
            if (e.getActionCommand().equals(b.getName())) {
                JDialog d = new DoorDialog(frame, true, b.getName());
                d.setVisible(true);
            }
        }

        // Einzelner Prisoner-Button übergibt an den PrisonerDialog seine Daten, 
        // um sie darzustellen oder ändern zu können.
        for (Prisoner p : list) {
            try {
                if (e.getActionCommand().equals(p.getNachname() + " " + p.getVorname())) {
                    try {
                        JDialog d = new PrisonerDialog(frame, true, p, a.getAuthortiy(user));
                        d.setVisible(true);
                    } catch (Exception ex) {
                        Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                aktualisieren();
            } catch (Exception ex) {
                Logger.getLogger(PrisonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
