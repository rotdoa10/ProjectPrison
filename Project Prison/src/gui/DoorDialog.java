package gui;

import beans.Prisoner;
import database.DBAccess;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patzineubi
 */
public class DoorDialog extends javax.swing.JDialog {

    private DBAccess a;
    private LinkedList<Prisoner> list = new LinkedList<Prisoner>();
    
    public DoorDialog(java.awt.Frame parent, boolean modal, String btnName) {
        super(parent, modal);
        
        try {
            initComponents();
            // EditorKit erzeugen
            javax.swing.text.html.HTMLEditorKit eKit = new javax.swing.text.html.HTMLEditorKit();
            
            // EditorKit setzen
            pane.setEditorKit(eKit);
            
            a = new DBAccess();
            String[]split = btnName.split(" ");
            list = a.getPrisonersinCell(Integer.parseInt(split[1]));

            for (int i = 0; i < list.size(); i++) 
            {
                // Text setzen
                pane.setText("<HTML><BODY><h1>"+(i+1)+". Prisoner:</h1><h3>Name: "+list.get(i).getNachname()+" "+list.get(i).getVorname()+"</h3></br></br></BODY></HTML>");

            }
            
        } catch (Exception ex) {
            Logger.getLogger(DoorDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pane = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        exitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pane.setEditable(false);
        pane.setBorder(javax.swing.BorderFactory.createTitledBorder("Gefangene: "));
        pane.setBounds(new java.awt.Rectangle(0, 0, 200, 800));
        jScrollPane1.setViewportView(pane);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout());

        exitBtn.setText("exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed

        // exit-Button
        this.dispose();

    }//GEN-LAST:event_exitBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DoorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DoorDialog dialog = new DoorDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane pane;
    // End of variables declaration//GEN-END:variables
}
