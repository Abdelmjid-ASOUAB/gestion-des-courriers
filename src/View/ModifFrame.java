package View;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdelmjid ASOUAB
 */
public class ModifFrame extends javax.swing.JFrame {
    

    /**
     * Creates new form ModifFrame
     */
    public ModifFrame(String Adresse_Exp, String CIN_Exp, String N_Reception, String Date_Envoyer, String Date_Reception, String Date_Renvoyer, String Email_Exp, String N_Order, String Nom_Des, String Nom_Exp, String Object, String Telephone_Des, String Telephone_Exp, String Adresse_Des, String Type_Exp, String Annexe_Exp, String Priorite, String Type_courr,String Type_Des,String Observation,int ID,String Traite) throws ClassNotFoundException, IOException, SQLException {
        initComponents();
        Modf a = new Modf(Adresse_Exp, CIN_Exp, N_Reception, Date_Envoyer, Date_Reception, Date_Renvoyer, Email_Exp, N_Order, Nom_Des, Nom_Exp, Object, Telephone_Des, Telephone_Exp, Adresse_Des, Type_Exp, Annexe_Exp, Priorite, Type_courr,Type_Des,Observation,this,ID,Traite);
        a.setBounds(0, 0, 1300, 720);
        this.add(a);
//        centre  frame
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
           this.setSize(940,440);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1172, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}