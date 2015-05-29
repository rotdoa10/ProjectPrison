package gui;

import database.DBAccess;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author patzineubi
 */
public class LoginGUI extends javax.swing.JFrame {

    DBAccess dba;

    public LoginGUI() throws Exception {
        initComponents();
        dba = new DBAccess();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txfUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fieldPassword = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        btnWeiter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Project Prison");
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Username:");
        jPanel1.add(jLabel2);
        jPanel1.add(txfUsername);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Passwort:");
        jPanel1.add(jLabel3);
        jPanel1.add(fieldPassword);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        btnWeiter.setText("Weiter");
        btnWeiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onWeiter(evt);
            }
        });
        jPanel2.add(btnWeiter, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onWeiter(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onWeiter

        if (txfUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Invalid input!");
        }  
        else {
            try {
                String user = txfUsername.getText();
                char[] pw = fieldPassword.getPassword();
                String pwStr = "";

                for (int i = 0; i < pw.length; i++) {
                    pwStr += pw[i];
                }

                boolean check = dba.checkLogin(user, pwStr);

                if (check) {
                    PrisonGUI g = new PrisonGUI();
                    g.start(user);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "FALSCHES PASSWORT ODER USERNAME!!");
                    fieldPassword.setText("");
                }

            } catch (Exception ex) {
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_onWeiter

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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new LoginGUI().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnWeiter;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txfUsername;
    // End of variables declaration//GEN-END:variables
}
