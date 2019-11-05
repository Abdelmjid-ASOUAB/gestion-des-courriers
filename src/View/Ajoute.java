package View;

import Controller.Connect;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.CANCEL_OPTION;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.JXDatePicker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdelmjid ASOUAB
 */
public class Ajoute extends javax.swing.JPanel {

    Connection C = Connect.getConx2();
    ArrayList<String> annex;


    public void SaveCour() throws ClassNotFoundException, SQLException {

        if (Nom_Exp.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Remplissez les champs !");

        } else {
            String Adresse_Exp = this.Adresse_Exp.getText(), CIN_Exp = this.CIN_Exp.getText(), N_Reception = this.N_Reception.getText(),
                    Date_Envoyer = dateconv(date_env), Date_Reception = dateconv(date_recp), Date_Renvoyer = dateconv(date_renv),
                    Email_Exp = this.Email_Exp.getText(), N_Order = this.N_Order.getText(), Nom_Des = this.Nom_Des.getText(),
                    Nom_Exp = this.Nom_Exp.getText(), Object = this.Object.getText(), Telephone_Des = this.Telephone_Des.getText(),
                    Telephone_Exp = this.Telephone_Exp.getText(), Adresse_Des = this.Adresse_Des.getText(), Observation = this.Observation.getText(),
                    Type_Exp = this.Type_Exp.getSelectedItem().toString(), Annexe_Exp = this.Annexe_Exp.getSelectedItem().toString(), Type_Des = this.Type_Des.getSelectedItem().toString(),
                    Priorite = this.Priorite.getSelectedItem().toString(), Type_courr = this.Type_courr.getSelectedItem().toString(), Traite = this.Traite.getSelection().getActionCommand();
//           Connect.ajouteDest(Nom_Des,Nom_Des,Adresse_Des,Telephone_Des,"dest");
//                    Type_Exp = this.Type_Exp.getSelectedItem().toString(), Annexe_Exp = this.Annexe_Exp.getSelectedItem().toString(),
            //             
            //  Connect.ajouteCour(Adresse_Exp, Date_Envoyer, Email_Exp, Nom_Exp, Telephone_Exp, Type_Exp, CIN_Exp, Annexe_Exp, Priorite, N_Order, Date_Reception, Object, N_Reception, Date_Renvoyer, Nom_Des, Adresse_Des, Telephone_Des, Type_courr);
            //  JOptionPane.showMessageDialog(null, "Saving Completed...!!!");
            //(`CODEDES`, `NOMDEST`, `ADRESSEDEST`, `TELEDEST`, `TYPEDEST`)         
            //CODEEXP`, `CODEANN`, `NOMDES`, `CIN`, `ADRESSDES`, `TELEDES`, `EMAILDES`, `TYPEEXP

            //`NUMORD`, `CODECOU`, `CODETYPCOUR`, `CODEIMG`, `DATEENVOI`, `NUMRESPTION`, `DATERENVOYE`, `OBJET`, `OBSERVATION`
            
            int codeDest = Connect.ajouteDest(C, Nom_Des, Adresse_Des, Telephone_Des, Type_Des);
            if(codeDest!=-1){
                 int codeExp = Connect.ajouteExp(C, Annexe_Exp, Nom_Exp, CIN_Exp, Adresse_Exp, Telephone_Exp, Email_Exp, Type_Exp);
                 if(codeExp!=-1){
                    int codeCour = Connect.ajouteCour(C, N_Order, Type_courr, "img", Date_Envoyer, Date_Reception, N_Reception, Date_Renvoyer, Object, Observation, Priorite, Traite);
                        if(codeCour!=-1){
                                    System.out.println(Connect.ajouteRelation(C, codeCour, codeDest, codeExp));
                                             JOptionPane.showMessageDialog(null, "Courrier Ajouté");
                        }else{
            
                                JOptionPane.showMessageDialog(null, "Erreur dans les champs de Courriers!");

            }
                 
                 }else{
            
                                JOptionPane.showMessageDialog(null, "Erreur dans les champs de Expediteur !");

            }
                
            }else{
            
                                JOptionPane.showMessageDialog(null, "Erreur dans les champs de Destination !");

            }
           

        }

    }

    /*
    public void PlaceholderTextField(JXDatePicker Datee) throws UnsupportedEncodingException, IOException {
        String str2 = new String("ANNEE-MOIS-JOUR");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(str2.getBytes());

        Datee.setText(os.toString("UTF-8"));
        Datee.setForeground(Color.GRAY);
        Datee.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Datee.getText().equals("ANNEE-MOIS-JOUR")) {
                    Datee.setText("");
                    Datee.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Datee.getText().isEmpty()) {
                    Datee.setForeground(Color.GRAY);
                    Datee.setText("ANNEE-MOIS-JOUR");

                }
            }

        });

    }*/
    public String dateconv(JXDatePicker d) {

        int selectedMonth = (int) d.getDate().getMonth() + 1;
        int selectedYear = (int) d.getDate().getYear() + 1900;
        int selectedDay = (int) d.getDate().getDay() + 1;

        return selectedYear + "-" + selectedMonth + "-" + selectedDay;
    }

    private void getPrio() throws ClassNotFoundException, SQLException {
        ArrayList<String> typ;
        typ = Connect.getPrio(C);

        for (int ii = 0; ii < typ.size(); ii++) {
            Priorite.addItem(typ.get(ii).toUpperCase());
        }

        Priorite.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                if (c instanceof JLabel) {
                    JLabel l = (JLabel) c;
                    // list.setSelectionBackground(new java.awt.Color(236, 240, 241));
                    if ("URGENT".equals(value)) {
                        l.setBackground(new java.awt.Color(192, 57, 43));
                        if (isSelected) {
                            list.setSelectionForeground(new java.awt.Color(236, 240, 241));
                            Priorite.setBackground(new java.awt.Color(255, 51, 51));

                        }

                        //rgb(230, 126, 34) rgb(231, 76, 60) rgb(46, 204, 113) rgb(236, 240, 241) rgb(192, 57, 43)
                    } else if ("IMPORTANT".equals(value)) {
                        l.setBackground(new java.awt.Color(230, 126, 34));
                        if (isSelected) {
                            list.setSelectionForeground(new java.awt.Color(236, 240, 241));
                            Priorite.setBackground(new java.awt.Color(230, 126, 34));
                        }
                    } else if ("PEU IMPORTANT".equals(value)) {
                        l.setBackground(new java.awt.Color(46, 204, 113));
                        if (isSelected) {
                            list.setSelectionForeground(new java.awt.Color(236, 240, 241));
                            Priorite.setBackground(new java.awt.Color(46, 204, 113));
                        }
                    }
                    return l;
                }
                return c;
            }

        });
    }

    private void getTypExp() throws ClassNotFoundException, SQLException, IOException {
        Type_Exp.removeAllItems();

        ArrayList<String> typ;
        Connection C1 = Connect.getConx2();
        typ = Connect.getTypExp(C1);
        for (int ii = 0; ii < typ.size(); ii++) {
            Type_Exp.addItem(typ.get(ii).toUpperCase());
            Type_Des.addItem(typ.get(ii).toUpperCase());
        }

        /*    Type_Exp.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                if (c instanceof JLabel) {
                    JLabel l = (JLabel) c;
                    // list.setSelectionBackground(new java.awt.Color(236, 240, 241));
                    if ("Citoyen".equals(value)) {
                       
                        //rgb(230, 126, 34) rgb(231, 76, 60) rgb(46, 204, 113) rgb(236, 240, 241) rgb(192, 57, 43)
                    } else  {
                        CIN_Exp.enable();
                    } 
                    return l;
                }
                return c;
            }

        });
         */
    }

    private void getTypCour() throws ClassNotFoundException, SQLException {
        ArrayList<String> tmp = Connect.getypeCour(C);

        for (int ii = 0; ii < tmp.size(); ii++) {
            Type_courr.addItem(tmp.get(ii).toUpperCase());
        }

    }

    private void getAnnexe() throws ClassNotFoundException, SQLException {
        ArrayList<String> tmp = Connect.getAnnexe(C);

        for (int ii = 0; ii < tmp.size(); ii++) {
            Annexe_Exp.addItem(tmp.get(ii).toUpperCase());
        }

    }

    /**
     * Creates new form pan
     */
    public Ajoute() throws ClassNotFoundException, IOException, SQLException {
        String property = System.getProperty("file.encoding");
        initComponents();
        getTypExp();
        getAnnexe();
        getTypCour();
        getPrio();

        date_env.setDate(new Date());
        date_recp.setDate(new Date());
        date_renv.setDate(new Date());
        Type_Exp.addItem("AUTRE");
        Type_Des.addItem("AUTRE");
        Type_courr.addItem("AUTRE");
        Annexe_Exp.addItem("AUTRE");

        B_en_train.setSelected(true);
        B_oui.setActionCommand("oui");
        B_non.setActionCommand("non");
        B_en_train.setActionCommand("en train");
        //System.out.println("~~é~~~~~éééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééééé "+Traite.getSelection().getActionCommand());
//        PlaceholderTextField(Date_Envoyer);
// 

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Traite = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        NomLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        save2 = new javax.swing.JLabel();
        SAVE1 = new javax.swing.JPanel();
        save1 = new javax.swing.JLabel();
        SAVE = new javax.swing.JPanel();
        save3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Type_Exp = new javax.swing.JComboBox<>();
        Nom_Exp = new javax.swing.JTextField();
        Annexe_Exp = new javax.swing.JComboBox<>();
        CIN_Exp = new javax.swing.JTextField();
        Adresse_Exp = new javax.swing.JTextField();
        Telephone_Exp = new javax.swing.JTextField();
        Email_Exp = new javax.swing.JTextField();
        NomLabel3 = new javax.swing.JLabel();
        NomLabel4 = new javax.swing.JLabel();
        NomLabel2 = new javax.swing.JLabel();
        NomLabel1 = new javax.swing.JLabel();
        NomLabel16 = new javax.swing.JLabel();
        N_Reception = new javax.swing.JTextField();
        Object = new javax.swing.JTextField();
        N_Order = new javax.swing.JTextField();
        NomLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Priorite = new javax.swing.JComboBox<>();
        Observation = new javax.swing.JTextField();
        NomLabel19 = new javax.swing.JLabel();
        B_oui = new javax.swing.JRadioButton();
        B_en_train = new javax.swing.JRadioButton();
        B_non = new javax.swing.JRadioButton();
        NomLabel14 = new javax.swing.JLabel();
        NomLabel7 = new javax.swing.JLabel();
        date_recp = new org.jdesktop.swingx.JXDatePicker();
        NomLabel17 = new javax.swing.JLabel();
        NomLabel6 = new javax.swing.JLabel();
        date_env = new org.jdesktop.swingx.JXDatePicker();
        NomLabel8 = new javax.swing.JLabel();
        Type_courr = new javax.swing.JComboBox<>();
        NomLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        NomLabel13 = new javax.swing.JLabel();
        Telephone_Des = new javax.swing.JTextField();
        Adresse_Des = new javax.swing.JTextField();
        NomLabel15 = new javax.swing.JLabel();
        NomLabel12 = new javax.swing.JLabel();
        NomLabel10 = new javax.swing.JLabel();
        Type_Des = new javax.swing.JComboBox<>();
        Nom_Des = new javax.swing.JTextField();
        date_renv = new org.jdesktop.swingx.JXDatePicker();
        NomLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NomLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 102, 255));
        setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jSeparator3.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(400, 850, 750, 2);

        NomLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel.setText("Nom:");
        jPanel2.add(NomLabel);
        NomLabel.setBounds(30, 170, 40, 40);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        save2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        save2.setForeground(new java.awt.Color(255, 255, 255));
        save2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save2.setText("annuler");
        save2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                save2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                save2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(save2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(save2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(820, 460, 230, 50);

        SAVE1.setBackground(new java.awt.Color(255, 51, 51));
        SAVE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SAVE1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SAVE1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SAVE1MouseExited(evt);
            }
        });
        SAVE1.setLayout(null);

        save1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        save1.setForeground(new java.awt.Color(255, 255, 255));
        save1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save1.setText("annuler");
        save1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                save1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                save1MouseExited(evt);
            }
        });
        SAVE1.add(save1);
        save1.setBounds(3, 0, 100, 50);

        jPanel2.add(SAVE1);
        SAVE1.setBounds(940, 490, 0, 0);

        SAVE.setBackground(new java.awt.Color(0, 102, 255));
        SAVE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SAVEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SAVEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SAVEMouseExited(evt);
            }
        });
        SAVE.setLayout(null);

        save3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        save3.setForeground(new java.awt.Color(255, 255, 255));
        save3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save3.setText("enregistrer");
        save3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                save3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                save3MouseExited(evt);
            }
        });
        SAVE.add(save3);
        save3.setBounds(0, 0, 230, 50);

        jPanel2.add(SAVE);
        SAVE.setBounds(820, 400, 230, 50);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Annexe :");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 220, 60, 40);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Information du Destinataire ");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(790, 50, 260, 40);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Type:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(30, 110, 40, 35);

        jLabel9.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 255));
        jLabel9.setText("Information d'expediteur :");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(50, 50, 260, 40);

        jLabel10.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 255));
        jLabel10.setText("Information du Courrier :");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(410, 50, 260, 40);

        Type_Exp.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        Type_Exp.setMaximumRowCount(90);
        Type_Exp.setBorder(null);
        Type_Exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type_ExpActionPerformed(evt);
            }
        });
        jPanel2.add(Type_Exp);
        Type_Exp.setBounds(70, 110, 250, 40);

        Nom_Exp.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Nom_Exp.setToolTipText("");
        Nom_Exp.setBorder(null);
        jPanel2.add(Nom_Exp);
        Nom_Exp.setBounds(70, 170, 250, 40);

        Annexe_Exp.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        Annexe_Exp.setMaximumRowCount(90);
        Annexe_Exp.setBorder(null);
        Annexe_Exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Annexe_ExpActionPerformed(evt);
            }
        });
        jPanel2.add(Annexe_Exp);
        Annexe_Exp.setBounds(70, 220, 250, 40);

        CIN_Exp.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        CIN_Exp.setToolTipText("");
        CIN_Exp.setBorder(null);
        jPanel2.add(CIN_Exp);
        CIN_Exp.setBounds(70, 270, 250, 40);

        Adresse_Exp.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Adresse_Exp.setToolTipText("");
        Adresse_Exp.setBorder(null);
        jPanel2.add(Adresse_Exp);
        Adresse_Exp.setBounds(70, 320, 250, 40);

        Telephone_Exp.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Telephone_Exp.setToolTipText("");
        Telephone_Exp.setBorder(null);
        jPanel2.add(Telephone_Exp);
        Telephone_Exp.setBounds(70, 370, 250, 40);

        Email_Exp.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Email_Exp.setToolTipText("");
        Email_Exp.setBorder(null);
        jPanel2.add(Email_Exp);
        Email_Exp.setBounds(70, 420, 250, 40);

        NomLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel3.setText("Email :");
        jPanel2.add(NomLabel3);
        NomLabel3.setBounds(20, 420, 50, 40);

        NomLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel4.setText("Tél:");
        jPanel2.add(NomLabel4);
        NomLabel4.setBounds(30, 370, 40, 40);

        NomLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel2.setText("Adresse:");
        jPanel2.add(NomLabel2);
        NomLabel2.setBounds(10, 320, 60, 30);

        NomLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel1.setText("CIN:");
        jPanel2.add(NomLabel1);
        NomLabel1.setBounds(30, 270, 30, 40);

        NomLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel16.setText("Objet:");
        jPanel2.add(NomLabel16);
        NomLabel16.setBounds(410, 290, 42, 35);

        N_Reception.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        N_Reception.setToolTipText("");
        jPanel2.add(N_Reception);
        N_Reception.setBounds(460, 240, 140, 40);

        Object.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Object.setToolTipText("");
        jPanel2.add(Object);
        Object.setBounds(460, 290, 210, 40);

        N_Order.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        N_Order.setToolTipText("");
        jPanel2.add(N_Order);
        N_Order.setBounds(460, 340, 110, 40);

        NomLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel5.setText("N d'ordre:");
        jPanel2.add(NomLabel5);
        NomLabel5.setBounds(390, 340, 65, 35);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Priorité:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(400, 390, 55, 35);

        Priorite.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        Priorite.setMaximumRowCount(90);
        Priorite.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(Priorite);
        Priorite.setBounds(460, 390, 160, 40);

        Observation.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Observation.setToolTipText("");
        jPanel2.add(Observation);
        Observation.setBounds(460, 440, 230, 40);

        NomLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel19.setText("Observation:");
        jPanel2.add(NomLabel19);
        NomLabel19.setBounds(370, 440, 90, 35);

        B_oui.setBackground(new java.awt.Color(102, 255, 102));
        Traite.add(B_oui);
        B_oui.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_oui.setText("OUI");
        B_oui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ouiActionPerformed(evt);
            }
        });
        jPanel2.add(B_oui);
        B_oui.setBounds(470, 500, 53, 25);

        B_en_train.setBackground(new java.awt.Color(255, 153, 102));
        Traite.add(B_en_train);
        B_en_train.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_en_train.setText("en cours");
        B_en_train.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_en_trainActionPerformed(evt);
            }
        });
        jPanel2.add(B_en_train);
        B_en_train.setBounds(520, 500, 85, 25);

        B_non.setBackground(new java.awt.Color(255, 0, 0));
        Traite.add(B_non);
        B_non.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_non.setText("NON");
        jPanel2.add(B_non);
        B_non.setBounds(610, 500, 59, 23);

        NomLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel14.setText("Traité :");
        jPanel2.add(NomLabel14);
        NomLabel14.setBounds(390, 500, 65, 20);

        NomLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel7.setText("N d'réception:");
        jPanel2.add(NomLabel7);
        NomLabel7.setBounds(360, 240, 100, 35);

        date_recp.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        date_recp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_recpActionPerformed(evt);
            }
        });
        jPanel2.add(date_recp);
        date_recp.setBounds(460, 190, 240, 44);

        NomLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel17.setText(" réception:");
        jPanel2.add(NomLabel17);
        NomLabel17.setBounds(380, 210, 80, 18);

        NomLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel6.setText("Date de");
        jPanel2.add(NomLabel6);
        NomLabel6.setBounds(390, 190, 60, 30);

        date_env.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        date_env.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_envActionPerformed(evt);
            }
        });
        jPanel2.add(date_env);
        date_env.setBounds(460, 140, 240, 44);

        NomLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel8.setText("Date d'envoi:");
        jPanel2.add(NomLabel8);
        NomLabel8.setBounds(370, 150, 90, 30);

        Type_courr.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        Type_courr.setMaximumRowCount(90);
        Type_courr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Type_courr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type_courrActionPerformed(evt);
            }
        });
        jPanel2.add(Type_courr);
        Type_courr.setBounds(460, 90, 180, 40);

        NomLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel9.setText(" courrier :");
        jPanel2.add(NomLabel9);
        NomLabel9.setBounds(390, 110, 80, 20);

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setText("Type du");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(390, 90, 60, 30);

        NomLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel13.setText("Tél:");
        jPanel2.add(NomLabel13);
        NomLabel13.setBounds(780, 330, 40, 35);

        Telephone_Des.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Telephone_Des.setToolTipText("");
        jPanel2.add(Telephone_Des);
        Telephone_Des.setBounds(820, 330, 250, 40);

        Adresse_Des.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Adresse_Des.setToolTipText("");
        jPanel2.add(Adresse_Des);
        Adresse_Des.setBounds(820, 280, 250, 40);

        NomLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel15.setText("Adresse :");
        jPanel2.add(NomLabel15);
        NomLabel15.setBounds(760, 280, 60, 35);

        NomLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel12.setText("d'envoi:");
        jPanel2.add(NomLabel12);
        NomLabel12.setBounds(760, 250, 60, 18);

        NomLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel10.setText("Date ");
        jPanel2.add(NomLabel10);
        NomLabel10.setBounds(770, 230, 40, 20);

        Type_Des.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        Type_Des.setMaximumRowCount(90);
        Type_Des.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type_DesActionPerformed(evt);
            }
        });
        jPanel2.add(Type_Des);
        Type_Des.setBounds(820, 130, 250, 40);

        Nom_Des.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Nom_Des.setToolTipText("");
        jPanel2.add(Nom_Des);
        Nom_Des.setBounds(820, 180, 250, 40);

        date_renv.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        date_renv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_renvActionPerformed(evt);
            }
        });
        jPanel2.add(date_renv);
        date_renv.setBounds(820, 230, 250, 44);

        NomLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel11.setText("Nom:");
        jPanel2.add(NomLabel11);
        NomLabel11.setBounds(780, 180, 40, 35);

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("Type:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(780, 130, 40, 35);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/backgrond.jpg"))); // NOI18N
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, 0, 1090, 620);

        NomLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        NomLabel18.setText("Traité :");
        jPanel2.add(NomLabel18);
        NomLabel18.setBounds(390, 490, 65, 20);

        add(jPanel2);
        jPanel2.setBounds(1, 1, 1440, 1340);
    }// </editor-fold>//GEN-END:initComponents

    private void SAVEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVEMouseClicked
        try {
            // TODO add your handling code here:
            SaveCour();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
        }
        //SaveCour();
        SAVE.setBackground(new java.awt.Color(45, 52, 54));


    }//GEN-LAST:event_SAVEMouseClicked

    private void SAVEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVEMouseEntered
        // TODO add your handling code here:
        SAVE.setBackground(new java.awt.Color(116, 185, 255));
    }//GEN-LAST:event_SAVEMouseEntered

    private void SAVEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVEMouseExited
        // TODO add your handling code here:
        SAVE.setBackground(new java.awt.Color(0, 102, 255));
        JOptionPane d = new JOptionPane();

    }//GEN-LAST:event_SAVEMouseExited

    private void Type_ExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Type_ExpActionPerformed
        // TODO add your handling code here:

        if (Type_Exp.getSelectedItem().toString().equals("AUTRE")) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            Ajoute_Annexe A = null;
            int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous ajoutee Nouvelles type?", "", dialogButton);
            if (dialogReslt == 0) {

                try {
                    A = new Ajoute_Annexe("Nouvelle Type", 1, Type_Exp);
                    A.setSize(435,150);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }//GEN-LAST:event_Type_ExpActionPerformed

    private void Type_DesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Type_DesActionPerformed

        if (Type_Des.getSelectedItem().toString().equals("AUTRE")) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            Ajoute_Annexe A = null;
            int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter Nouveau type", "", dialogButton);
            if (dialogReslt == 0) {

                try {
                    A = new Ajoute_Annexe("Nouveau Type", 1, Type_Des);
                    A.setSize(435,150);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }


    }//GEN-LAST:event_Type_DesActionPerformed

    private void B_ouiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ouiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_ouiActionPerformed

    private void B_en_trainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_en_trainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_en_trainActionPerformed

    private void date_renvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_renvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date_renvActionPerformed

    private void date_envActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_envActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date_envActionPerformed

    private void date_recpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_recpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date_recpActionPerformed

    private void Annexe_ExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Annexe_ExpActionPerformed

        if (Annexe_Exp.getSelectedItem().toString().equals("AUTRE")) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            Ajoute_Annexe A = null;
            int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter Nouveau annexe?", "", dialogButton);
            if (dialogReslt == 0) {

                try {
                    A = new Ajoute_Annexe("Nouveau annexe ", 2, Annexe_Exp);
                    A.setSize(435,150);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        // 

    }//GEN-LAST:event_Annexe_ExpActionPerformed

    private void Type_courrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Type_courrActionPerformed
        if (Type_courr.getSelectedItem().toString().equals("AUTRE")) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            Ajoute_Annexe A = null;
            int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter Nouveau type?", "", dialogButton);
            if (dialogReslt == 0) {

                try {
                    A = new Ajoute_Annexe("Nouveau Type  ", 3, Type_courr);
                    A.setSize(435,150);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }    }//GEN-LAST:event_Type_courrActionPerformed

    private void save1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save1MouseClicked
        this.Adresse_Exp.setText("");
        this.CIN_Exp.setText("");
        this.N_Reception.setText("");
        //dateconv(date_env); dateconv(date_recp);dateconv(date_renv);
        this.Email_Exp.setText("");
        this.N_Order.setText("");
        this.Nom_Des.setText("");
        this.Nom_Exp.setText("");
        this.Object.setText("");
        this.Telephone_Des.setText("");
        this.Telephone_Exp.setText("");
        this.Adresse_Des.setText("");
        this.Observation.setText("");    }//GEN-LAST:event_save1MouseClicked

    private void save1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save1MouseEntered
        SAVE1.setBackground(new java.awt.Color(255, 102, 102));
    }//GEN-LAST:event_save1MouseEntered

    private void SAVE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVE1MouseClicked
        this.Adresse_Exp.setText("");
        this.CIN_Exp.setText("");
        this.N_Reception.setText("");
        //dateconv(date_env); dateconv(date_recp);dateconv(date_renv);
        this.Email_Exp.setText("");
        this.N_Order.setText("");
        this.Nom_Des.setText("");
        this.Nom_Exp.setText("");
        this.Object.setText("");
        this.Telephone_Des.setText("");
        this.Telephone_Exp.setText("");
        this.Adresse_Des.setText("");
        this.Observation.setText("");
    }//GEN-LAST:event_SAVE1MouseClicked

    private void SAVE1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVE1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SAVE1MouseEntered

    private void SAVE1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVE1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SAVE1MouseExited

    private void save1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save1MouseExited
        SAVE1.setBackground(new java.awt.Color(255, 51, 51));
    }//GEN-LAST:event_save1MouseExited

    private void save2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save2MouseClicked
 this.Adresse_Exp.setText(""); 
 this.CIN_Exp.setText(""); this.N_Reception.setText("");
                   
                     this.Email_Exp.setText(""); this.N_Order.setText(""); this.Nom_Des.setText("");
                    this.Nom_Exp.setText("");this.Object.setText(""); this.Telephone_Des.setText("");
                     this.Telephone_Exp.setText(""); this.Adresse_Des.setText(""); this.Observation.setText("");
                    

        // TODO add your handling code here:
    }//GEN-LAST:event_save2MouseClicked

    private void save2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_save2MouseEntered

    private void save2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_save2MouseExited

    private void save3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save3MouseClicked
        try {
            SaveCour();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ajoute.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_save3MouseClicked

    private void save3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_save3MouseEntered

    private void save3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_save3MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adresse_Des;
    private javax.swing.JTextField Adresse_Exp;
    private javax.swing.JComboBox<String> Annexe_Exp;
    private javax.swing.JRadioButton B_en_train;
    private javax.swing.JRadioButton B_non;
    private javax.swing.JRadioButton B_oui;
    private javax.swing.JTextField CIN_Exp;
    private javax.swing.JTextField Email_Exp;
    private javax.swing.JTextField N_Order;
    private javax.swing.JTextField N_Reception;
    private javax.swing.JLabel NomLabel;
    private javax.swing.JLabel NomLabel1;
    private javax.swing.JLabel NomLabel10;
    private javax.swing.JLabel NomLabel11;
    private javax.swing.JLabel NomLabel12;
    private javax.swing.JLabel NomLabel13;
    private javax.swing.JLabel NomLabel14;
    private javax.swing.JLabel NomLabel15;
    private javax.swing.JLabel NomLabel16;
    private javax.swing.JLabel NomLabel17;
    private javax.swing.JLabel NomLabel18;
    private javax.swing.JLabel NomLabel19;
    private javax.swing.JLabel NomLabel2;
    private javax.swing.JLabel NomLabel3;
    private javax.swing.JLabel NomLabel4;
    private javax.swing.JLabel NomLabel5;
    private javax.swing.JLabel NomLabel6;
    private javax.swing.JLabel NomLabel7;
    private javax.swing.JLabel NomLabel8;
    private javax.swing.JLabel NomLabel9;
    private javax.swing.JTextField Nom_Des;
    private javax.swing.JTextField Nom_Exp;
    private javax.swing.JTextField Object;
    private javax.swing.JTextField Observation;
    private javax.swing.JComboBox<String> Priorite;
    private javax.swing.JPanel SAVE;
    private javax.swing.JPanel SAVE1;
    private javax.swing.JTextField Telephone_Des;
    private javax.swing.JTextField Telephone_Exp;
    private javax.swing.ButtonGroup Traite;
    private javax.swing.JComboBox<String> Type_Des;
    private javax.swing.JComboBox<String> Type_Exp;
    private javax.swing.JComboBox<String> Type_courr;
    private org.jdesktop.swingx.JXDatePicker date_env;
    private org.jdesktop.swingx.JXDatePicker date_recp;
    private org.jdesktop.swingx.JXDatePicker date_renv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel save1;
    private javax.swing.JLabel save2;
    private javax.swing.JLabel save3;
    // End of variables declaration//GEN-END:variables
}
