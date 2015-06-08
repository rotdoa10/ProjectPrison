/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import beans.Prisoner;
import database.DBAccess;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patzineubi
 */
public class PrisonerDialog extends javax.swing.JDialog {

    DBAccess dba= new DBAccess();
    Prisoner pt;
    public PrisonerDialog(java.awt.Frame parent, boolean modal, Prisoner p, int priority) throws Exception {
        super(parent, modal);
        
        initComponents();    
        this.setLocationRelativeTo(parent);
        pt = p;
        LinkedList<String> l = dba.getCells();
        for (int i = 0; i < l.size(); i++) {
            cbCellNr.addItem(l.get(i));            
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
        
        lbName.setText(p.getNachname() + ", " + p.getVorname());
        lbGebDat.setText(sdf.format(p.getGebDate()));
        lbInDat.setText(sdf.format(p.getInDate()));
        tfOutDat.setText(sdf.format(p.getOutDate()));
        
        cbPriority.setSelectedIndex(pt.getpID()-1);
        cbCellNr.setSelectedIndex(pt.getCellID()-1);
        
        if(priority==1)
        {
            tfOutDat.setEditable(false);
            cbCellNr.setEditable(false);           
            cbPriority.setEditable(false);
            btOK.setEnabled(false);
            
        }

    }
 
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbGebDat = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbInDat = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfOutDat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbPriority = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbCellNr = new javax.swing.JComboBox();
        btOK = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(7, 2));

        jLabel1.setText("Name:");
        getContentPane().add(jLabel1);
        getContentPane().add(lbName);

        jLabel3.setText("Geburtsdatum:");
        getContentPane().add(jLabel3);
        getContentPane().add(lbGebDat);

        jLabel5.setText("Inhaftierungsdatum:");
        getContentPane().add(jLabel5);
        getContentPane().add(lbInDat);

        jLabel2.setText("Entlassungsdatum:");
        getContentPane().add(jLabel2);
        getContentPane().add(tfOutDat);

        jLabel7.setText("Priorität:");
        getContentPane().add(jLabel7);

        cbPriority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        getContentPane().add(cbPriority);

        jLabel8.setText("Zelle:");
        getContentPane().add(jLabel8);

        getContentPane().add(cbCellNr);

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOK(evt);
            }
        });
        getContentPane().add(btOK);

        btCancel.setText("Abbruch");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCancel(evt);
            }
        });
        getContentPane().add(btCancel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onCancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCancel
        this.dispose();       
    }//GEN-LAST:event_onCancel

    private void onOK(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOK
        SimpleDateFormat sdf = new SimpleDateFormat("DD.MM.YYYY");
        
        try {
            dba.updatePrisoner(pt.getId(), sdf.parse(tfOutDat.getText()) , Integer.parseInt((String) cbPriority.getSelectedItem()), Integer.parseInt((String)cbCellNr.getSelectedItem()));
        } catch (Exception ex) {
            Logger.getLogger(PrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_onOK

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(PrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //Prisoner pr = new Prisoner(3, "Test", "Testson", new Date(), new Date(), new Date(), 2, 6);
                    Prisoner pr = null;
                    PrisonerDialog dialog = new PrisonerDialog(new javax.swing.JFrame(), true, pr, 1);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(PrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOK;
    private javax.swing.JComboBox cbCellNr;
    private javax.swing.JComboBox cbPriority;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbGebDat;
    private javax.swing.JLabel lbInDat;
    private javax.swing.JLabel lbName;
    private javax.swing.JTextField tfOutDat;
    // End of variables declaration//GEN-END:variables
}
