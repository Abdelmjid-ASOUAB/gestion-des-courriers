/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Connect;
import Model.Courrier;
import Model.User;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abdelmjid ASOUAB
 */
public class AjouteUser extends javax.swing.JFrame {

    Object[][] data;
    String Entete[] = {"user", "passeword"};

    Connection C;

    /**
     * Creates new form AjouteUser
     */
    public AjouteUser() throws ClassNotFoundException, SQLException {

        C = Connect.getConx2();

        ArrayList<User> USRS = null;
        try {
            USRS = Connect.getUsers(C);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        USRS = Connect.getUsers(C);
        data = new Object[USRS.size()+1][2];

        for (int j = 0; j < USRS.size(); j++) {
            data[j][0] = USRS.get(j).getUser();
            data[j][1] = USRS.get(j).getPwd();
        }

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usertxt = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        annule = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Nuser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Npwd = new javax.swing.JPasswordField();
        pwdtxt = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(usertxt);
        usertxt.setBounds(170, 180, 188, 34);

        add.setBackground(new java.awt.Color(102, 255, 102));
        add.setText("jButton1");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add);
        add.setBounds(50, 180, 108, 40);

        annule.setBackground(new java.awt.Color(255, 102, 102));
        annule.setText("jButton1");
        annule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annuleActionPerformed(evt);
            }
        });
        getContentPane().add(annule);
        annule.setBounds(50, 220, 108, 40);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Nouveaux utilisateur");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Npwd)
                            .addComponent(Nuser, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Nuser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Npwd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 0, 350, 160);
        getContentPane().add(pwdtxt);
        pwdtxt.setBounds(170, 220, 190, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,
            Entete));
    jTable1.setToolTipText("");
    jScrollPane1.setViewportView(jTable1);

    getContentPane().add(jScrollPane1);
    jScrollPane1.setBounds(380, 40, 330, 240);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        String nuser = Nuser.getText(), npwd = Npwd.getText(), pwd = pwdtxt.getText(), user = usertxt.getText();
        String hour = new SimpleDateFormat("HH").format(new Date());
        String min = new SimpleDateFormat("mm").format(new Date());

        ArrayList<User> USRS = null;
        try {
            USRS = Connect.getUsers(C);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < USRS.size(); i++) {
            if (user.equals("admin") && pwd.equals(hour + min)) {

                try {
                    Connect.ajouteUser(C, nuser, npwd);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouteUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("ok");

                data[USRS.size()][0] = nuser;
                data[USRS.size()][1] = npwd;
                new javax.swing.table.DefaultTableModel(
            data,
            Entete);

                break;
            }

        }


    }//GEN-LAST:event_addActionPerformed

    private void annuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annuleActionPerformed
        this.setVisible(false);    }//GEN-LAST:event_annuleActionPerformed

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
            java.util.logging.Logger.getLogger(AjouteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjouteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjouteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjouteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AjouteUser().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AjouteUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouteUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Npwd;
    private javax.swing.JTextField Nuser;
    private javax.swing.JButton add;
    private javax.swing.JButton annule;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPasswordField pwdtxt;
    private javax.swing.JTextField usertxt;
    // End of variables declaration//GEN-END:variables
}
