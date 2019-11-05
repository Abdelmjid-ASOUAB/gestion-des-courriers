package View;

import Controller.CreateExlFile;
import Controller.Connect;
import Model.*;
import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdelmjid ASOUAB
 */
public class Table extends javax.swing.JPanel {

    Connection C = Connect.getConx2();
    public int nbelem = 0;
    ArrayList<Courrier> selCour = new ArrayList<Courrier>();

    private void centerTable() {
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
        for (int i = 0; i < jTable2.getColumnCount(); i++) // centre chaque cellule de ton tableau
        {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(custom);
        }
        jScrollPane1.getVerticalScrollBar().setValue(0);

    }

    private void getTypExp(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<String> typ;
        typ = Connect.getTypExp(C);

        for (int ii = 0; ii < typ.size(); ii++) {
            Type_Exp.addItem(typ.get(ii).toUpperCase());
        }

    }

    private void getTypCour(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<String> tmp = Connect.getypeCour(C);

        for (int ii = 0; ii < tmp.size(); ii++) {
            Type_Cour.addItem(tmp.get(ii).toUpperCase());
        }

    }

    public static Object[][] getFullCour(Connection C) throws ClassNotFoundException, SQLException {

        JTable tabb = null;

        ArrayList<Courrier> cour = Connect.getCourrier(C);
        int cmp = cour.size();

        Object tabCour[][] = new Object[cmp][7];
        /*
        connection 
         */

        String Entete[] = {"id", "Adresse_Des", "Annexe_Exp", "CIN_Exp", "Date_Envoyer", "Date_Reception", "Date_Renvoyer", "Email_Exp", "N_Order", "N_Reception", "Nom_Des", "Nom_Exp", "Object", "Priorite", "Telephone_Des", "Telephone_Exp", "Type_Exp"};

        int i = 0;

        while (i < cmp) {

            tabCour[i][0] = cour.get(i).getIdRelation();
            tabCour[i][1] = cour.get(i).getTypeCour();
            tabCour[i][2] = cour.get(i).getObjet();
            tabCour[i][3] = cour.get(i).getExpediteur().getNomExp();
            tabCour[i][4] = cour.get(i).getExpediteur().getTeleExp();
            tabCour[i][5] = cour.get(i).getDestination().getNomDest();
            tabCour[i][6] = cour.get(i).getDateResption();

            i++;

        }

        tabb = new JTable(tabCour, Entete);

//        for (int ii = 0; ii < cmp; ii++) {
//            //System.out.println((i + 1) + "\n Courrier  \n " + arrCourr.get(i).toString());
//            for (int j = 0; j < 6; j++) {
//                System.out.print(tabCour[ii][j] + " ###ssss###  ");
//            }
//            System.out.println(" ");
//        }

        return tabCour;
    }

    public static Courrier getOneCour(int t, Connection C) throws ClassNotFoundException, SQLException {

        ArrayList<Courrier> cour = Connect.getCourrier(C);
        Courrier c = null;
        for (int j = 0; j < cour.size(); j++) {
            if (cour.get(j).getIdRelation() == t) {
                c = cour.get(j);
            }
        }
        return c;
    }
    /*
    public static Object[][] getCourDate(String t) throws ClassNotFoundException, SQLException {

        ArrayList<Courrier> cour = Connect.getCourrier();

        fullcourr fcour;
        JTable tabb = null;
        int cmp = cour.size();
        Object tabCour[][] = new Object[cmp][7];

        int i = 0, j = 0;

        while (i < cmp) {
            if (cour.get(i).getIdRelation() == 4) {
                tabCour[j][0] = cour.get(i).getIdRelation();
                tabCour[j][1] = cour.get(i).getTypeCour();
                tabCour[j][2] = cour.get(i).getObjet();
                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                tabCour[j][6] = cour.get(i).getDateResption();
                j++;
            }
            i++;
        }

        return tabCour;
    }
     */
    Object[][] data;

    String Entete[] = {"ID", "Type", "Object", "Expediteur", "Telephone", "Destination", "Date_Reception"};
    String Entete2[] = {"----", "----", "----", "----", "----", "----"};
    //Les titres des colonnes

    /**
     * Creates new form Table
     *
     */
    public Table() throws ClassNotFoundException, SQLException {

        data = getFullCour(C);
        initComponents();
        getTypCour(C);
        getTypExp(C);
        selCour = Connect.getCourrier(C);
        centerTable();
        Count.setText(data.length+"");
       
    }
      /**
     public Table(String typ) throws ClassNotFoundException, SQLException {
            int c = 0;

        data = getFullCour(C);
        initComponents();
        getTypCour(C);
        getTypExp(C);
        selCour = Connect.getCourrier(C);
        centerTable();
       
      
           ArrayList<Courrier> cour = Connect.getCourrier(C);
            Object[][] tabCour = new Object[cour.size()][7];
            Object[][] data2 = null;

           
       ArrayList<Courrier> selCour = new ArrayList<Courrier>();
                    int j = 0;
                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (cour.get(i).getTypeCour().equals(typ)) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                            selCour.add(cour.get(i));
                                c++;

                            j++;
                        }
                    }

                    data2 = tabCour;
       
                    
       
         jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    Entete));
            Count.setText(c+"");
             if(c==0){
                               JOptionPane.showMessageDialog(null, "Aucun résultat trouvé");
                               
            }
    }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        search_full1 = new javax.swing.JButton();
        getAll = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Count = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        annee = new javax.swing.JComboBox<>();
        mois = new javax.swing.JComboBox<>();
        jour = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Type_Cour = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Type_Exp = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        Exp_nom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(102, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Microsoft_Excel_40px.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Excel");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(940, 100, 120, 40);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTable2.setForeground(new java.awt.Color(51, 0, 51));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            data,
            Entete));
    jTable2.setToolTipText("");
    jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jTable2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
    jTable2.setFocusable(false);
    jTable2.setRequestFocusEnabled(false);
    jTable2.setRowHeight(30);
    jTable2.setRowMargin(0);
    jTable2.setRowSorter(null);
    jTable2.setSelectionBackground(new java.awt.Color(72, 126, 176));
    jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jTable2.setSurrendersFocusOnKeystroke(true);
    jTable2.setUpdateSelectionOnSort(false);
    jTable2.setVerifyInputWhenFocusTarget(false);
    jTable2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jTable2MouseMoved(evt);
        }
    });
    jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable2MouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(jTable2);

    add(jScrollPane1);
    jScrollPane1.setBounds(10, 168, 1080, 450);

    search_full1.setText("Rechercher");
    search_full1.setToolTipText("Rechercher Courriers");
    search_full1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            search_full1MouseClicked(evt);
        }
    });
    search_full1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            search_full1ActionPerformed(evt);
        }
    });
    add(search_full1);
    search_full1.setBounds(740, 80, 90, 50);

    getAll.setBackground(new java.awt.Color(102, 255, 102));
    getAll.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            getAllMouseClicked(evt);
        }
    });
    getAll.setLayout(null);

    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Restart_40px.png"))); // NOI18N
    jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    getAll.add(jLabel7);
    jLabel7.setBounds(0, 0, 40, 40);

    jLabel11.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
    jLabel11.setForeground(new java.awt.Color(255, 255, 255));
    jLabel11.setText("Rafraîchir");
    jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    getAll.add(jLabel11);
    jLabel11.setBounds(40, 4, 110, 30);

    add(getAll);
    getAll.setBounds(940, 60, 120, 40);

    Count.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
    Count.setForeground(new java.awt.Color(255, 255, 255));
    Count.setText("nb");
    add(Count);
    Count.setBounds(850, 70, 44, 50);

    jLabel12.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
    jLabel12.setForeground(new java.awt.Color(255, 255, 255));
    jLabel12.setText("Courrires");
    add(jLabel12);
    jLabel12.setBounds(880, 60, 60, 20);

    annee.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
    annee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
    annee.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            anneeMouseClicked(evt);
        }
    });
    annee.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            anneeActionPerformed(evt);
        }
    });
    add(annee);
    annee.setBounds(650, 90, 72, 40);

    mois.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
    mois.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
    mois.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            moisMouseClicked(evt);
        }
    });
    add(mois);
    mois.setBounds(580, 90, 60, 40);

    jour.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
    jour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
    jour.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jourMouseClicked(evt);
        }
    });
    add(jour);
    jour.setBounds(510, 90, 60, 40);

    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setText("jour- mois- annee");
    add(jLabel5);
    jLabel5.setBounds(570, 70, 111, 15);

    jLabel2.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Date");
    add(jLabel2);
    jLabel2.setBounds(590, 50, 38, 20);

    Type_Cour.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
    Type_Cour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--" }));
    Type_Cour.setToolTipText("");
    Type_Cour.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            Type_CourMouseClicked(evt);
        }
    });
    add(Type_Cour);
    Type_Cour.setBounds(310, 90, 147, 40);

    jLabel3.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Type de courriers");
    add(jLabel3);
    jLabel3.setBounds(320, 50, 145, 20);

    Type_Exp.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
    Type_Exp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--" }));
    Type_Exp.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            Type_ExpMouseClicked(evt);
        }
    });
    add(Type_Exp);
    Type_Exp.setBounds(50, 70, 250, 35);

    jLabel4.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setText("Expediteur");
    add(jLabel4);
    jLabel4.setBounds(110, 50, 90, 20);
    add(Exp_nom);
    Exp_nom.setBounds(90, 110, 150, 35);

    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setText("Type:   ");
    add(jLabel6);
    jLabel6.setBounds(10, 80, 37, 14);

    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("  Nom:");
    add(jLabel1);
    jLabel1.setBounds(30, 120, 50, 14);

    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/backgrondtt.jpg"))); // NOI18N
    jLabel8.setText("jLabel8");
    add(jLabel8);
    jLabel8.setBounds(0, 0, 1100, 640);
    }// </editor-fold>//GEN-END:initComponents

    private void getAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getAllMouseClicked
        // TODO add your handling code here:
        Object[][] data2 = null;

        try {
            data2 = getFullCour(C);
            selCour = Connect.getCourrier(C);
            Count.setText(data2.length+"");

//            Count.setText(Connect.countRows("*") + "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        new Thread(new Runnable() {
            @Override // jPanel2.setBounds(668, 0, 230, 142);
            public void run() {
                int A = 100, B = 40;
                for (int i = 0; i <= 35; i += 5) {

                    try {
                        Thread.sleep(110);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    jPanel1.setBounds(0, A, 1400, B);
                    A -= i;
                    B += i;
                    //setBackground(new java.awt.Color(A, 1, B));
                }
            }

        });*/

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                data2,
                Entete));
        centerTable();

    }//GEN-LAST:event_getAllMouseClicked


    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        jTable2.setEnabled(false);

        if (evt.getClickCount() == 2) {
            Courrier cour = null;
            final JTable jTable = (JTable) evt.getSource();
            final int row = jTable.getSelectedRow();
            final int column = jTable.getSelectedColumn();
            final int valueInCell = (int) jTable.getValueAt(row, 0);

            try {
                cour = getOneCour(valueInCell, C);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
//    public ModifFrame(String Adresse_Exp, String CIN_Exp, String N_Reception, String Date_Envoyer, String Date_Reception, String Date_Renvoyer, String Email_Exp, String N_Order, String Nom_Des, String Nom_Exp, String Object, String Telephone_Des, String Telephone_Exp, String Adresse_Des, String Type_Exp, String Annexe_Exp, String Priorite, String Type_courr) throws ClassNotFoundException, IOException {

            try {
                new ModifFrame(cour.getExpediteur().getAdressExp(), cour.getExpediteur().getCin(), cour.getNumResption(), cour.getDateEnvoi(), cour.getDateResption(), cour.getDateRenvoye(), cour.getExpediteur().getEmailExp(), cour.getNumOrd(), cour.getDestination().getNomDest(), cour.getExpediteur().getNomExp(), cour.getObjet(), cour.getDestination().getTeleDest(), cour.getExpediteur().getTeleExp(), cour.getDestination().getAdresseDest(), cour.getExpediteur().getTypeExp(), cour.getExpediteur().getCodeAnn(), cour.getPriorite(), cour.getTypeCour(), cour.getDestination().getTypeDest(), cour.getObservation(), valueInCell, cour.getTraite()).setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTable2.setEnabled(true);

        }


    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseMoved
        jTable2.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseMoved

    private void search_full1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_full1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_search_full1MouseClicked

    private void search_full1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_full1ActionPerformed
        try {

            ArrayList<Courrier> cour = Connect.getCourrier(C);
            Object[][] tabCour = new Object[cour.size()][7];
            Object[][] data2 = null;

            int c = 0;

            //par Date
            if (Type_Exp.getSelectedItem().toString().equals("--") && Exp_nom.getText().equals("") && Type_Cour.getSelectedItem().toString().equals("--")) {

                if (annee.getSelectedItem().toString().equals("--")) {
                    return;
                } else if (jour.getSelectedItem().toString().equals("--")) {
                    if (mois.getSelectedItem().toString().equals("--")) {
                        selCour = new ArrayList<Courrier>();

                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString())) {
                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();

                                c++;
                                selCour.add(cour.get(i));
                                j++;
                            } else {
                            }

                        }

                        data2 = tabCour;

                    } else {
                        int j = 0;
                        selCour = new ArrayList<Courrier>();
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                selCour.add(cour.get(i));

                                c++;

                                j++;
                            } else {
                            }

                        }

                        data2 = tabCour;
                    }
                } else if (mois.getSelectedItem().toString().equals("--")) {
                    return;
                } else {
                    selCour = new ArrayList<Courrier>();
                    int j = 0;
                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                            selCour.add(cour.get(i));
                                c++;

                            j++;
                        }
                    }

                    data2 = tabCour;
                }

            } //Type Courr
            else if (Type_Exp.getSelectedItem().toString().equals("--") && Exp_nom.getText().equals("") && annee.getSelectedItem().toString().equals("--") && mois.getSelectedItem().toString().equals("--") && jour.getSelectedItem().toString().equals("--")) {

                if (Type_Cour.getSelectedItem().toString().equals("--")) {
                    return;
                } else {
                    selCour = new ArrayList<Courrier>();
                    int j = 0;
                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                            selCour.add(cour.get(i));
                                c++;

                            j++;
                        } else {
                        }

                    }

                    data2 = tabCour;

                }

            } //par Expediteur
            else if (Type_Cour.getSelectedItem().toString().equals("--") && annee.getSelectedItem().toString().equals("--") && mois.getSelectedItem().toString().equals("--") && jour.getSelectedItem().toString().equals("--")) {
                if (Exp_nom.getText().equals("")) {
                    if (Type_Exp.getSelectedItem().toString().equals("--")) {

                        return;

                    } else {

                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                selCour.add(cour.get(i));
                                c++;

                                j++;
                            } else {
                            }

                        }

                        data2 = tabCour;
                    }

                } else if (Type_Exp.getSelectedItem().toString().equals("--")) {
                    if (Exp_nom.getText().equals("")) {
                        return;
                    } else {
                        selCour = new ArrayList<Courrier>();

                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {
                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                selCour.add(cour.get(i));
                                System.out.println("11  " + tabCour[j][3]);
                                System.out.println("11  " + tabCour[j][4]);
                                c++;

                                j++;
                            } else {
                            }

                        }

                        data2 = tabCour;

                    }
                } else {
                    selCour = new ArrayList<Courrier>();
                    int j = 0;
                    for (int i = 0; i < cour.size(); i++) {
                        if (cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                            selCour.add(cour.get(i));
                                c++;

                            j++;
                        } else {
                        }

                    }
                    data2 = tabCour;
                }

            } //all void
            else if (Type_Exp.getSelectedItem().toString().equals("--") && Exp_nom.getText().equals("") && Type_Cour.getSelectedItem().toString().equals("--") && annee.getSelectedItem().toString().equals("--") && mois.getSelectedItem().toString().equals("--") && jour.getSelectedItem().toString().equals("--")) {
                return;
            } //Date && TypeCour
            else if (Type_Exp.getSelectedItem().toString().equals("--") && Exp_nom.getText().equals("")) {

                if (annee.getSelectedItem().toString().equals("--")) {
                    return;
                } else if (jour.getSelectedItem().toString().equals("--")) {
                    if (mois.getSelectedItem().toString().equals("--")) {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;

                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                selCour.add(cour.get(i));
                                c++;
                                j++;
                               
                            } else {
                            }

                        }
                        data2 = tabCour;
                    } else {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;

                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));

                                j++;
                            } else {
                            }

                        }
                        data2 = tabCour;

                    }
                } else if (mois.getSelectedItem().toString().equals("--")) {
                    return;
                } else {
                    selCour = new ArrayList<Courrier>();
                    int j = 0;

                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                            selCour.add(cour.get(i));

                            j++;
                        } else {
                        }

                    }
                    data2 = tabCour;
                }

            }// Expediteur && Type cour
            else if (annee.getSelectedItem().toString().equals("--") && mois.getSelectedItem().toString().equals("--") && jour.getSelectedItem().toString().equals("--")) {
                if (Exp_nom.getText().equals("")) {
                    if (Type_Exp.getSelectedItem().toString().equals("--")) {

                        return;

                    } else {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;

                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));

                                j++;
                            } else {
                            }

                        }
                        data2 = tabCour;

                    }

                } else if (Type_Exp.getSelectedItem().toString().equals("--")) {
                    if (Exp_nom.getText().equals("")) {
                        return;
                    } else {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;

                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));

                                j++;
                            } else {
                            }

                        }
                        data2 = tabCour;

                    }
                } else {
                    //  data2 = getCourDate("Nom_Exp='" + Exp_nom.getText() + "' AND " + "Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'" + " and Type_courr ='" + Type_Cour.getSelectedItem().toString() + "'");
                    selCour = new ArrayList<Courrier>();
                    int j = 0;

                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                            selCour.add(cour.get(i));
                            j++;
                        } else {
                        }

                    }
                    data2 = tabCour;

                }

            }//Date && Expediteur
            else if (Type_Cour.getSelectedItem().toString().equals("--")) {

                if (Exp_nom.getText().equals("")) {
                    //   data2 = getCourDate("YEAR(Date_Reception)='" + annee.getSelectedItem().toString() + "'" + "and Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'");
                    if (annee.getSelectedItem().toString().equals("--")) {
                        return;
                    } else if (jour.getSelectedItem().toString().equals("--")) {
                        if (mois.getSelectedItem().toString().equals("--")) {
                            selCour = new ArrayList<Courrier>();
                            int j = 0;
                            for (int i = 0; i < cour.size(); i++) {
                                String D[] = cour.get(i).getDateResption().split("-");
                                if (D[0].equals(annee.getSelectedItem().toString()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                    tabCour[j][0] = cour.get(i).getIdRelation();
                                    tabCour[j][1] = cour.get(i).getTypeCour();
                                    tabCour[j][2] = cour.get(i).getObjet();
                                    tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                    tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                    tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                    tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                    selCour.add(cour.get(i));
                                    j++;
                                } else {
                                }

                            }

                            data2 = tabCour;

                        } else {
                            selCour = new ArrayList<Courrier>();
                            int j = 0;
                            for (int i = 0; i < cour.size(); i++) {
                                String D[] = cour.get(i).getDateResption().split("-");
                                if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                    tabCour[j][0] = cour.get(i).getIdRelation();
                                    tabCour[j][1] = cour.get(i).getTypeCour();
                                    tabCour[j][2] = cour.get(i).getObjet();
                                    tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                    tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                    tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                    tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                    selCour.add(cour.get(i));
                                    j++;
                                } else {
                                }

                            }

                            data2 = tabCour;
                        }
                    } else if (mois.getSelectedItem().toString().equals("--")) {
                        return;
                    } else {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;
                    }
                } else if (Type_Exp.getSelectedItem().toString().equals("--")) {
                    //::::::::::::::::::

                    //   data2 = getCourDate("YEAR(Date_Reception)='" + annee.getSelectedItem().toString() + "'" + "and Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'");
                    if (annee.getSelectedItem().toString().equals("--")) {
                        return;
                    } else if (jour.getSelectedItem().toString().equals("--")) {
                        if (mois.getSelectedItem().toString().equals("--")) {
                            selCour = new ArrayList<Courrier>();
                            int j = 0;
                            for (int i = 0; i < cour.size(); i++) {
                                String D[] = cour.get(i).getDateResption().split("-");
                                if (D[0].equals(annee.getSelectedItem().toString()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {

                                    tabCour[j][0] = cour.get(i).getIdRelation();
                                    tabCour[j][1] = cour.get(i).getTypeCour();
                                    tabCour[j][2] = cour.get(i).getObjet();
                                    tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                    tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                    tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                    tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                    selCour.add(cour.get(i));
                                    j++;
                                } else {
                                }

                            }

                            data2 = tabCour;

                        } else {
                            selCour = new ArrayList<Courrier>();
                            int j = 0;
                            for (int i = 0; i < cour.size(); i++) {
                                String D[] = cour.get(i).getDateResption().split("-");
                                if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {

                                    tabCour[j][0] = cour.get(i).getIdRelation();
                                    tabCour[j][1] = cour.get(i).getTypeCour();
                                    tabCour[j][2] = cour.get(i).getObjet();
                                    tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                    tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                    tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                    tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                    selCour.add(cour.get(i));
                                    j++;
                                } else {
                                }

                            }

                            data2 = tabCour;
                        }
                    } else if (mois.getSelectedItem().toString().equals("--")) {
                        return;
                    } else {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;
                    }

                    //:::::::::::::::::::::
                } else {

                    if (annee.getSelectedItem().toString().equals("--")) {
                        return;
                    } else if (jour.getSelectedItem().toString().equals("--")) {
                        if (mois.getSelectedItem().toString().equals("--")) {
                            selCour = new ArrayList<Courrier>();
                            int j = 0;
                            for (int i = 0; i < cour.size(); i++) {
                                String D[] = cour.get(i).getDateResption().split("-");
                                if (D[0].equals(annee.getSelectedItem().toString()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                    tabCour[j][0] = cour.get(i).getIdRelation();
                                    tabCour[j][1] = cour.get(i).getTypeCour();
                                    tabCour[j][2] = cour.get(i).getObjet();
                                    tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                    tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                    tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                    tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                    selCour.add(cour.get(i));
                                    j++;
                                } else {
                                }

                            }

                            data2 = tabCour;

                        } else {
                            selCour = new ArrayList<Courrier>();
                            int j = 0;
                            for (int i = 0; i < cour.size(); i++) {
                                String D[] = cour.get(i).getDateResption().split("-");
                                if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                    tabCour[j][0] = cour.get(i).getIdRelation();
                                    tabCour[j][1] = cour.get(i).getTypeCour();
                                    tabCour[j][2] = cour.get(i).getObjet();
                                    tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                    tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                    tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                    tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                    selCour.add(cour.get(i));
                                    j++;
                                } else {
                                }

                            }

                            data2 = tabCour;
                        }
                    } else if (mois.getSelectedItem().toString().equals("--")) {
                        return;
                    } else {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;
                    }

                }

            } // by ALL
            else if (Exp_nom.getText().equals("")) {

                if (annee.getSelectedItem().toString().equals("--")) {
                    return;
                } else if (jour.getSelectedItem().toString().equals("--")) {
                    if (mois.getSelectedItem().toString().equals("--")) {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;

                    } else {
                        // data2 = getCourDate("YEAR(Date_Reception)='" + annee.getSelectedItem().toString() + "' and MONTH(Date_Reception)='" + mois.getSelectedItem().toString() + "'" + " and Type_courr ='" + Type_Cour.getSelectedItem().toString() + "'" + "and Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'");
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;

                    }
                } else if (mois.getSelectedItem().toString().equals("--")) {
                    return;
                } else {
                    //data2 = getCourDate("Date_Reception='" + annee.getSelectedItem().toString() + mois.getSelectedItem().toString() + jour.getSelectedItem().toString() + "'" + " and Type_courr ='" + Type_Cour.getSelectedItem().toString() + "'" + "and Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'");
                    selCour = new ArrayList<Courrier>();
                    int j = 0;
                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem()) && cour.get(i).getExpediteur().getTypeExp().equals(Type_Exp.getSelectedItem())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                            selCour.add(cour.get(i));
                            j++;
                        }
                    }

                    data2 = tabCour;

                }

            } else if (Type_Exp.getSelectedItem().toString().equals("--")) { //" and Nom_Exp='" + Exp_nom.getText()
                if (annee.getSelectedItem().toString().equals("--")) {
                    return;
                } else if (jour.getSelectedItem().toString().equals("--")) {
                    if (mois.getSelectedItem().toString().equals("--")) {
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;

                    } else {
                        // data2 = getCourDate("YEAR(Date_Reception)='" + annee.getSelectedItem().toString() + "' and MONTH(Date_Reception)='" + mois.getSelectedItem().toString() + "'" + " and Type_courr ='" + Type_Cour.getSelectedItem().toString() + "'" + "and Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'");
                        selCour = new ArrayList<Courrier>();
                        int j = 0;
                        for (int i = 0; i < cour.size(); i++) {
                            String D[] = cour.get(i).getDateResption().split("-");
                            if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {

                                tabCour[j][0] = cour.get(i).getIdRelation();
                                tabCour[j][1] = cour.get(i).getTypeCour();
                                tabCour[j][2] = cour.get(i).getObjet();
                                tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                                tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                                tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                                tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                                selCour.add(cour.get(i));
                                j++;
                            }
                        }

                        data2 = tabCour;

                    }
                } else if (mois.getSelectedItem().toString().equals("--")) {
                    return;
                } else {
//                       data2 = getCourDate("Date_Reception='" + annee.getSelectedItem().toString() + mois.getSelectedItem().toString() + jour.getSelectedItem().toString() + "'" + " and Type_courr ='" + Type_Cour.getSelectedItem().toString() + "'" + "and Type_Exp='" + Type_Exp.getSelectedItem().toString() + "'");
                    selCour = new ArrayList<Courrier>();
                    int j = 0;
                    for (int i = 0; i < cour.size(); i++) {
                        String D[] = cour.get(i).getDateResption().split("-");
                        if (D[0].equals(annee.getSelectedItem().toString()) && D[1].equals(mois.getSelectedItem().toString()) && D[2].equals(jour.getSelectedItem().toString()) && cour.get(i).getTypeCour().equals(Type_Cour.getSelectedItem()) && cour.get(i).getExpediteur().getNomExp().equals(Exp_nom.getText())) {

                            tabCour[j][0] = cour.get(i).getIdRelation();
                            tabCour[j][1] = cour.get(i).getTypeCour();
                            tabCour[j][2] = cour.get(i).getObjet();
                            tabCour[j][3] = cour.get(i).getExpediteur().getNomExp();
                            tabCour[j][4] = cour.get(i).getExpediteur().getTeleExp();
                            tabCour[j][5] = cour.get(i).getDestination().getNomDest();
                            tabCour[j][6] = cour.get(i).getDateResption();
                                c++;

                            selCour.add(cour.get(i));
                            j++;
                        }
                    }

                    data2 = tabCour;
                }

            } else {
                return;
            }
           
            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    data2,
                    Entete));
            Count.setText(c+"");
             if(c==0){
                               JOptionPane.showMessageDialog(null, "Aucun résultat trouvé");
                               
            }
            centerTable();

//            for (int a = 0; a < selCour.size(); a++) {
//                System.out.println(selCour.get(a).toString());
//            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }    }//GEN-LAST:event_search_full1ActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
    int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Généré Excel ?", "", dialogButton);
        if (dialogReslt == 0) {
 String message = null;
        try {
            message = CreateExlFile.Create(selCour);
        } catch (IOException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, message);
        try {
            Process p = Runtime.getRuntime().exec(message);
            p.waitFor();

        } catch (IOException ex) {
            //Validate the case the file can't be accesed (not enought permissions)

        } catch (InterruptedException ex) {
            //Validate the case the process is being stopped by some external situation     

        }
           
     } else {   
        
   

        }    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
      int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Généré Excel ?", "", dialogButton);
        if (dialogReslt == 0) {
 String message = null;
          try {
              message = CreateExlFile.Create(selCour);
          } catch (IOException ex) {
              Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
          }
        JOptionPane.showMessageDialog(null, message);
        try {
            Process p = Runtime.getRuntime().exec(message);
            p.waitFor();

        } catch (IOException ex) {
            //Validate the case the file can't be accesed (not enought permissions)

        } catch (InterruptedException ex) {
            //Validate the case the process is being stopped by some external situation     

        }
           
     } else {   
        

        }    }//GEN-LAST:event_jLabel9MouseClicked

    private void anneeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anneeMouseClicked
  if (evt.getClickCount() == 2){
        annee.setSelectedIndex(0);
  }    }//GEN-LAST:event_anneeMouseClicked

    private void moisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moisMouseClicked
if (evt.getClickCount() == 2){
        mois.setSelectedIndex(0);
  }    }//GEN-LAST:event_moisMouseClicked

    private void jourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jourMouseClicked
if (evt.getClickCount() == 2){
        jour.setSelectedIndex(0);
  }    }//GEN-LAST:event_jourMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Généré Excel ?", "", dialogButton);
        if (dialogReslt == 0) {
 String message = null;
    try {
        message = CreateExlFile.Create(selCour);
    } catch (IOException ex) {
        Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
    }
        JOptionPane.showMessageDialog(null, message);
        try {
            Process p = Runtime.getRuntime().exec(message);
            p.waitFor();

        } catch (IOException ex) {
            //Validate the case the file can't be accesed (not enought permissions)

        } catch (InterruptedException ex) {
            //Validate the case the process is being stopped by some external situation     

        }
           
     } else {   
        }  
    }//GEN-LAST:event_jPanel1MouseClicked

    private void anneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anneeActionPerformed

    private void Type_ExpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Type_ExpMouseClicked
if (evt.getClickCount() == 2){
        Type_Exp.setSelectedIndex(0);
  }      }//GEN-LAST:event_Type_ExpMouseClicked

    private void Type_CourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Type_CourMouseClicked
if (evt.getClickCount() == 2){
        Type_Cour.setSelectedIndex(0);
  }  

    }//GEN-LAST:event_Type_CourMouseClicked

    JTable jTable1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Count;
    private javax.swing.JTextField Exp_nom;
    private javax.swing.JComboBox<String> Type_Cour;
    private javax.swing.JComboBox<String> Type_Exp;
    private javax.swing.JComboBox<String> annee;
    private javax.swing.JPanel getAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> jour;
    private javax.swing.JComboBox<String> mois;
    private javax.swing.JButton search_full1;
    // End of variables declaration//GEN-END:variables
}
