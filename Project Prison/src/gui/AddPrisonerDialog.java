package gui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author patzineubi
 */
public class AddPrisonerDialog extends javax.swing.JDialog {

    public AddPrisonerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Create the DatePicker.
//DatePicker datePicker = new DatePicker();
//
//// Add some action (in Java 8 lambda syntax style).
//datePicker.setOnAction(event -> {
//    LocalDate date = datePicker.getValue();
//    System.out.println("Selected date: " + date);
//});
//
//// Add the DatePicker to the Stage.
//StackPane root = new StackPane();
//root.getChildren().add(datePicker);
//stage.setScene(new Scene(root, 500, 650));
//stage.show();
//        JDialog dlg = new JDialog(new Frame(), true);
//    DatePicker dp = new DatePicker();
//    dp.setHideOnSelect(false);
//    dlg.getContentPane().add(dp);
//    dlg.pack();
//    dlg.show();
//    System.out.println(dp.getDate().toString());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfgeb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(6, 2));

        jLabel2.setText("Vorname:");
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField1);

        jLabel3.setText("Nachname:");
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField2);

        jLabel4.setText("Geburtsdatum:");
        jPanel1.add(jLabel4);

        txfgeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfgebActionPerformed(evt);
            }
        });
        jPanel1.add(txfgeb);

        jLabel5.setText("Inhaftierungsdatum:");
        jPanel1.add(jLabel5);

        jTextField4.setText("jTextField4");
        jPanel1.add(jTextField4);

        jLabel6.setText("Entlassungsdatum:");
        jPanel1.add(jLabel6);

        jTextField5.setText("jTextField5");
        jPanel1.add(jTextField5);

        jLabel7.setText("Zelle:");
        jPanel1.add(jLabel7);

        jTextField6.setText("jTextField6");
        jPanel1.add(jTextField6);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Prisoner:");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txfgebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfgebActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfgebActionPerformed

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
            public void run() {
                AddPrisonerDialog dialog = new AddPrisonerDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField txfgeb;
    // End of variables declaration//GEN-END:variables
}
