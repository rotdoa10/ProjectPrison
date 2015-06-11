package gui;

import database.DBAccess;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Patrizia Neubauer, Project Prison, 10.06.2015
 */
public class AddPrisonerDialog extends javax.swing.JDialog {

    private DBAccess a;
    private LinkedList<String> list = new LinkedList<String>();

    public AddPrisonerDialog(java.awt.Frame parent, boolean modal) throws ParseException, Exception {
        super(parent, modal);
        initComponents();
        a = new DBAccess();
        list = a.getCells();
            for (int i = 0; i < list.size(); i++) {
                zelleBox.addItem(list.get(i));
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txfVorname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txfNachname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfGeb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txfIn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txfEnt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        zelleBox = new javax.swing.JComboBox();
        labelP = new javax.swing.JLabel();
        txfPr = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(8, 2));

        jLabel2.setText("Vorname:");
        jPanel1.add(jLabel2);
        jPanel1.add(txfVorname);

        jLabel3.setText("Nachname:");
        jPanel1.add(jLabel3);
        jPanel1.add(txfNachname);

        jLabel4.setText("Geburtsdatum:");
        jPanel1.add(jLabel4);
        jPanel1.add(txfGeb);

        jLabel5.setText("Inhaftierungsdatum:");
        jPanel1.add(jLabel5);
        jPanel1.add(txfIn);

        jLabel6.setText("Entlassungsdatum:");
        jPanel1.add(jLabel6);
        jPanel1.add(txfEnt);

        jLabel7.setText("Zelle:");
        jPanel1.add(jLabel7);

        jPanel1.add(zelleBox);

        labelP.setText("Priorität (1-5):");
        jPanel1.add(labelP);
        jPanel1.add(txfPr);

        btnOK.setText("ok");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        jPanel1.add(btnOK);

        btnCancel.setText("cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Prisoner:");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed

        // Ein neuer Prisoner wird zur Datenbank hinzugefügt.
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String vorname = this.txfVorname.getText();
            String nachname = this.txfNachname.getText();
            String geb = this.txfGeb.getText();
            Date dGeb = sdf.parse(geb);
            String in = this.txfIn.getText();
            Date dIn = sdf.parse(in);
            String ent = this.txfEnt.getText();
            Date dEnt = sdf.parse(ent);
            int zelle = Integer.parseInt((String) this.zelleBox.getSelectedItem());
            int pri = Integer.parseInt(this.txfPr.getText());

            if (vorname != null || nachname != null || geb != null || in != null || ent != null || this.txfPr.getText() != null) 
            {
                try {

                    a.addPrisoner(vorname, nachname, dGeb, dIn, dEnt, pri, zelle);
                } catch (ParseException ex) {
                    Logger.getLogger(AddPrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AddPrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Falsche Eingabe!!!!");
            }

            dispose();
        } catch (ParseException ex) {
            Logger.getLogger(AddPrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddPrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();

    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed

        // cancel - Button
        dispose();

    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(AddPrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPrisonerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AddPrisonerDialog dialog = new AddPrisonerDialog(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(AddPrisonerDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelP;
    private javax.swing.JTextField txfEnt;
    private javax.swing.JTextField txfGeb;
    private javax.swing.JTextField txfIn;
    private javax.swing.JTextField txfNachname;
    private javax.swing.JTextField txfPr;
    private javax.swing.JTextField txfVorname;
    private javax.swing.JComboBox zelleBox;
    // End of variables declaration//GEN-END:variables
}
