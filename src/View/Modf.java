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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdelmjid ASOUAB
 */
public class Modf extends javax.swing.JPanel {
    Connection C= Connect.getConx2();
    ArrayList<String> annex;
    JFrame ff;
    int ID;

    public void updateCour() throws SQLException {

        if (Nom_Exp.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "rempler les champs!!!");

        } else {
            String Adresse_Exp = this.Adresse_Exp.getText(), CIN_Exp = this.CIN_Exp.getText(), N_Reception = this.N_Reception.getText(),
                    Date_Envoyer = this.Date_Envoyer.getText(), Date_Reception = this.Date_Reception.getText(), Date_Renvoyer = this.Date_Renvoyer.getText(),
                    Email_Exp = this.Email_Exp.getText(), N_Order = this.N_Order.getText(), Nom_Des = this.Nom_Des.getText(),
                    Nom_Exp = this.Nom_Exp.getText(), Object = this.Object.getText(), Telephone_Des = this.Telephone_Des.getText(),
                    Telephone_Exp = this.Telephone_Exp.getText(), Adresse_Des = this.Adresse_Des.getText(), Observation = this.Observation.getText(),
                    Type_Exp = this.Type_Exp.getSelectedItem().toString(), Annexe_Exp = this.Annexe_Exp.getSelectedItem().toString(), Type_Des = this.Type_Des.getSelectedItem().toString(),
                    Priorite = this.Priorite.getSelectedItem().toString(), Type_courr = this.Type_courr.getSelectedItem().toString(),Traite=this.Traite.getSelection().getActionCommand();

            Connect.updateCour(C,ID, Adresse_Exp, Date_Envoyer, Email_Exp, Nom_Exp, Telephone_Exp, Type_Exp, CIN_Exp, Annexe_Exp, Priorite, N_Order, Date_Reception, Object, Date_Renvoyer, Nom_Des, Adresse_Des, Telephone_Des, Type_courr, N_Reception, Observation, Type_Des,Traite);
            JOptionPane.showMessageDialog(null, "Update Completed...!!!");

        }

    }
//Type_Des = this.Type_Des.getSelectedItem().toString()

    public void getData(String Adresse_Exp, String CIN_Exp, String N_Reception, String Date_Envoyer, String Date_Reception, String Date_Renvoyer, String Email_Exp, String N_Order, String Nom_Des, String Nom_Exp, String Object, String Telephone_Des, String Telephone_Exp, String Adresse_Des, String Type_Exp, String Annexe_Exp, String Priorite, String Type_courr, String Type_Des, String Observation,String Traite) {
        this.Adresse_Exp.setText(Adresse_Exp);
        this.CIN_Exp.setText(CIN_Exp);
        this.N_Reception.setText(N_Reception);
        this.Date_Envoyer.setText(Date_Envoyer);
        this.Date_Reception.setText(Date_Reception);
        this.Date_Renvoyer.setText(Date_Renvoyer);
        this.Email_Exp.setText(Email_Exp);
        this.N_Order.setText(N_Order);
        this.Nom_Des.setText(Nom_Des);
        this.Nom_Exp.setText(Nom_Exp);
        this.Object.setText(Object);
        this.Telephone_Des.setText(Telephone_Des);
        this.Telephone_Exp.setText(Telephone_Exp);
        this.Adresse_Des.setText(Adresse_Des);
        this.Type_Exp.setSelectedItem(Type_Exp);
        this.Annexe_Exp.setSelectedItem(Annexe_Exp);
        this.Priorite.setSelectedItem(Priorite);
        this.Type_courr.setSelectedItem(Type_courr);
        this.Type_Des.setSelectedItem(Type_Des);
        this.Observation.setText(Observation);
        
        if(Traite.equals("oui")){
            B_oui.setSelected(true);
        }else if(Traite.equals("non")){
            B_non.setSelected(true);
        }else if(Traite.equals("en train")){
            B_en_train.setSelected(true);
        }else{
            B_non.setSelected(true);
        }
    }

    public void PlaceholderTextField(JTextField Datee) throws UnsupportedEncodingException, IOException {
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

    private void getTypExp() throws ClassNotFoundException, SQLException {
        ArrayList<String> typ;
        typ = Connect.getTypExp(C);

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
    public Modf(String Adresse_Exp, String CIN_Exp, String N_Reception, String Date_Envoyer, String Date_Reception, String Date_Renvoyer, String Email_Exp, String N_Order, String Nom_Des, String Nom_Exp, String Object, String Telephone_Des, String Telephone_Exp, String Adresse_Des, String Type_Exp, String Annexe_Exp, String Priorite, String Type_courr, String Type_Des, String Observation, JFrame f, int ID,String Traite) throws ClassNotFoundException, IOException, SQLException {
        ff = f;
        this.ID = ID;
 
        String property = System.getProperty("file.encoding");
        initComponents();
        getTypExp();
        getAnnexe();
        getPrio();
        getTypCour();
        PlaceholderTextField(this.Date_Envoyer);
        PlaceholderTextField(this.Date_Reception);
        PlaceholderTextField(this.Date_Renvoyer);
        getData(Adresse_Exp, CIN_Exp, N_Reception, Date_Envoyer, Date_Reception, Date_Renvoyer, Email_Exp, N_Order, Nom_Des, Nom_Exp, Object, Telephone_Des, Telephone_Exp, Adresse_Des, Type_Exp, Annexe_Exp, Priorite, Type_courr, Type_Des, Observation,Traite);
        B_oui.setActionCommand("oui");
        B_non.setActionCommand("non");
        B_en_train.setActionCommand("en train");
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Traite = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        Nom_Exp = new javax.swing.JTextField();
        NomLabel3 = new javax.swing.JLabel();
        NomLabel2 = new javax.swing.JLabel();
        NomLabel8 = new javax.swing.JLabel();
        Nom_Des = new javax.swing.JTextField();
        NomLabel11 = new javax.swing.JLabel();
        Type_courr = new javax.swing.JComboBox<>();
        N_Reception = new javax.swing.JTextField();
        Email_Exp = new javax.swing.JTextField();
        CIN_Exp = new javax.swing.JTextField();
        NomLabel9 = new javax.swing.JLabel();
        Type_Exp = new javax.swing.JComboBox<>();
        Priorite = new javax.swing.JComboBox<>();
        NomLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        NomLabel1 = new javax.swing.JLabel();
        NomLabel = new javax.swing.JLabel();
        NomLabel4 = new javax.swing.JLabel();
        Annexe_Exp = new javax.swing.JComboBox<>();
        Telephone_Exp = new javax.swing.JTextField();
        SAVE = new javax.swing.JPanel();
        save = new javax.swing.JLabel();
        B_oui = new javax.swing.JRadioButton();
        B_en_train = new javax.swing.JRadioButton();
        B_non = new javax.swing.JRadioButton();
        NomLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        NomLabel6 = new javax.swing.JLabel();
        N_Order = new javax.swing.JTextField();
        Telephone_Des = new javax.swing.JTextField();
        Object = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Date_Envoyer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Date_Renvoyer = new javax.swing.JTextField();
        NomLabel13 = new javax.swing.JLabel();
        Adresse_Des = new javax.swing.JTextField();
        NomLabel7 = new javax.swing.JLabel();
        Adresse_Exp = new javax.swing.JTextField();
        Date_Reception = new javax.swing.JTextField();
        NomLabel12 = new javax.swing.JLabel();
        NomLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        annule = new javax.swing.JPanel();
        save1 = new javax.swing.JLabel();
        Type_Des = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        Observation = new javax.swing.JTextField();
        NomLabel14 = new javax.swing.JLabel();
        NomLabel15 = new javax.swing.JLabel();
        NomLabel16 = new javax.swing.JLabel();
        NomLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        NomLabel18 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        delet = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 102, 255));
        setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        Nom_Exp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Nom_Exp.setToolTipText("");
        jPanel2.add(Nom_Exp);
        Nom_Exp.setBounds(90, 110, 190, 30);

        NomLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel3.setText("Email :");
        jPanel2.add(NomLabel3);
        NomLabel3.setBounds(40, 310, 39, 20);

        NomLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel2.setText("Adresse:");
        jPanel2.add(NomLabel2);
        NomLabel2.setBounds(30, 230, 50, 20);

        NomLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel8.setText("Date");
        jPanel2.add(NomLabel8);
        NomLabel8.setBounds(350, 80, 30, 20);

        Nom_Des.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Nom_Des.setToolTipText("");
        jPanel2.add(Nom_Des);
        Nom_Des.setBounds(700, 110, 220, 30);

        NomLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel11.setText("Nom:");
        jPanel2.add(NomLabel11);
        NomLabel11.setBounds(670, 110, 29, 20);

        Type_courr.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        Type_courr.setMaximumRowCount(90);
        Type_courr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(Type_courr);
        Type_courr.setBounds(400, 50, 150, 30);

        N_Reception.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        N_Reception.setToolTipText("");
        jPanel2.add(N_Reception);
        N_Reception.setBounds(400, 170, 120, 30);

        Email_Exp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Email_Exp.setToolTipText("");
        jPanel2.add(Email_Exp);
        Email_Exp.setBounds(90, 310, 180, 30);

        CIN_Exp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        CIN_Exp.setToolTipText("");
        jPanel2.add(CIN_Exp);
        CIN_Exp.setBounds(90, 190, 100, 30);

        NomLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel9.setText("de réception:");
        jPanel2.add(NomLabel9);
        NomLabel9.setBounds(320, 140, 90, 10);

        Type_Exp.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        Type_Exp.setMaximumRowCount(90);
        jPanel2.add(Type_Exp);
        Type_Exp.setBounds(90, 70, 190, 30);

        Priorite.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        Priorite.setMaximumRowCount(90);
        Priorite.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(Priorite);
        Priorite.setBounds(400, 290, 150, 30);

        NomLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel10.setText("de renvoyer:");
        jPanel2.add(NomLabel10);
        NomLabel10.setBounds(620, 160, 80, 20);

        jSeparator3.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(400, 850, 750, 2);

        NomLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel1.setText("CIN:");
        jPanel2.add(NomLabel1);
        NomLabel1.setBounds(60, 190, 22, 20);

        NomLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel.setText("Nom:");
        jPanel2.add(NomLabel);
        NomLabel.setBounds(50, 110, 30, 20);

        NomLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel4.setText("Tele:");
        jPanel2.add(NomLabel4);
        NomLabel4.setBounds(50, 270, 30, 20);

        Annexe_Exp.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        Annexe_Exp.setMaximumRowCount(90);
        jPanel2.add(Annexe_Exp);
        Annexe_Exp.setBounds(90, 150, 140, 30);

        Telephone_Exp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Telephone_Exp.setToolTipText("");
        jPanel2.add(Telephone_Exp);
        Telephone_Exp.setBounds(90, 270, 180, 30);

        SAVE.setBackground(new java.awt.Color(0, 102, 255));
        SAVE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        save.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Enregistrer la Modification");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout SAVELayout = new javax.swing.GroupLayout(SAVE);
        SAVE.setLayout(SAVELayout);
        SAVELayout.setHorizontalGroup(
            SAVELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SAVELayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save)
                .addContainerGap())
        );
        SAVELayout.setVerticalGroup(
            SAVELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(SAVE);
        SAVE.setBounds(670, 270, 240, 40);

        B_oui.setBackground(new java.awt.Color(102, 255, 102));
        Traite.add(B_oui);
        B_oui.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        B_oui.setText("OUI");
        B_oui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ouiActionPerformed(evt);
            }
        });
        jPanel2.add(B_oui);
        B_oui.setBounds(380, 370, 50, 20);

        B_en_train.setBackground(new java.awt.Color(255, 153, 102));
        Traite.add(B_en_train);
        B_en_train.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        B_en_train.setText("En Train...");
        B_en_train.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_en_trainActionPerformed(evt);
            }
        });
        jPanel2.add(B_en_train);
        B_en_train.setBounds(440, 370, 90, 20);

        B_non.setBackground(new java.awt.Color(255, 0, 0));
        Traite.add(B_non);
        B_non.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        B_non.setText("NON");
        jPanel2.add(B_non);
        B_non.setBounds(540, 370, 51, 20);

        NomLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel5.setText("N d'ordre:");
        jPanel2.add(NomLabel5);
        NomLabel5.setBounds(340, 250, 60, 20);

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setText("Type");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(340, 40, 40, 20);

        NomLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel6.setText("Date ");
        jPanel2.add(NomLabel6);
        NomLabel6.setBounds(350, 120, 40, 20);

        N_Order.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        N_Order.setToolTipText("");
        jPanel2.add(N_Order);
        N_Order.setBounds(400, 250, 120, 30);

        Telephone_Des.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Telephone_Des.setToolTipText("");
        jPanel2.add(Telephone_Des);
        Telephone_Des.setBounds(700, 230, 220, 30);

        Object.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Object.setToolTipText("");
        jPanel2.add(Object);
        Object.setBounds(400, 210, 190, 30);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("Annexe :");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(30, 150, 51, 20);

        Date_Envoyer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Date_Envoyer.setToolTipText("");
        jPanel2.add(Date_Envoyer);
        Date_Envoyer.setBounds(400, 90, 190, 30);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("Priorité:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(350, 290, 50, 20);

        Date_Renvoyer.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        Date_Renvoyer.setToolTipText("");
        jPanel2.add(Date_Renvoyer);
        Date_Renvoyer.setBounds(700, 150, 220, 30);

        NomLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel13.setText("Telephone:");
        jPanel2.add(NomLabel13);
        NomLabel13.setBounds(630, 230, 70, 20);

        Adresse_Des.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Adresse_Des.setToolTipText("");
        jPanel2.add(Adresse_Des);
        Adresse_Des.setBounds(700, 190, 220, 30);

        NomLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NomLabel7.setText("Traité :");
        jPanel2.add(NomLabel7);
        NomLabel7.setBounds(320, 360, 50, 30);

        Adresse_Exp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Adresse_Exp.setToolTipText("");
        jPanel2.add(Adresse_Exp);
        Adresse_Exp.setBounds(90, 230, 180, 30);

        Date_Reception.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Date_Reception.setToolTipText("");
        jPanel2.add(Date_Reception);
        Date_Reception.setBounds(400, 130, 190, 30);

        NomLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel12.setText("Adresse:");
        jPanel2.add(NomLabel12);
        NomLabel12.setBounds(650, 190, 50, 20);

        NomLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel19.setText("N d'réception:");
        jPanel2.add(NomLabel19);
        NomLabel19.setBounds(310, 170, 90, 20);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setText("Type:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(50, 70, 40, 20);

        annule.setBackground(new java.awt.Color(0, 102, 255));
        annule.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        annule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                annuleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                annuleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                annuleMouseExited(evt);
            }
        });

        save1.setBackground(new java.awt.Color(0, 102, 255));
        save1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        save1.setForeground(new java.awt.Color(255, 255, 255));
        save1.setText("Annuler");
        save1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                save1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout annuleLayout = new javax.swing.GroupLayout(annule);
        annule.setLayout(annuleLayout);
        annuleLayout.setHorizontalGroup(
            annuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(annuleLayout.createSequentialGroup()
                .addComponent(save1)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        annuleLayout.setVerticalGroup(
            annuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(save1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(annule);
        annule.setBounds(810, 320, 70, 30);

        Type_Des.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        Type_Des.setMaximumRowCount(90);
        Type_Des.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type_DesActionPerformed(evt);
            }
        });
        jPanel2.add(Type_Des);
        Type_Des.setBounds(700, 70, 220, 30);

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setText("Type:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(660, 70, 40, 20);

        Observation.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Observation.setToolTipText("");
        jPanel2.add(Observation);
        Observation.setBounds(400, 330, 190, 30);

        NomLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel14.setText("Observation:");
        jPanel2.add(NomLabel14);
        NomLabel14.setBounds(310, 330, 80, 20);

        NomLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel15.setText("Objet:");
        jPanel2.add(NomLabel15);
        NomLabel15.setBounds(360, 210, 40, 20);

        NomLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel16.setText(" de courriere :");
        jPanel2.add(NomLabel16);
        NomLabel16.setBounds(310, 60, 100, 10);

        NomLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel17.setText(" de Envoyer:");
        jPanel2.add(NomLabel17);
        NomLabel17.setBounds(320, 100, 90, 10);

        jLabel9.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 255));
        jLabel9.setText("Information d'expediteur");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(40, 10, 230, 40);

        jLabel10.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 255));
        jLabel10.setText("Information de Courriere :");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(340, 0, 260, 40);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Information de Destinateur ");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(670, 0, 260, 40);

        NomLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        NomLabel18.setText("Date ");
        jPanel2.add(NomLabel18);
        NomLabel18.setBounds(650, 150, 40, 20);

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        delet.setBackground(new java.awt.Color(0, 102, 255));
        delet.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        delet.setForeground(new java.awt.Color(255, 255, 255));
        delet.setText("Supprimer");
        delet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deletMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(delet, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(delet)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(680, 320, 90, 30);

        add(jPanel2);
        jPanel2.setBounds(1, 1, 1100, 1340);
    }// </editor-fold>//GEN-END:initComponents

    private void SAVEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVEMouseClicked
        try {
            updateCour();
        } catch (SQLException ex) {
            Logger.getLogger(Modf.class.getName()).log(Level.SEVERE, null, ex);
        }
        SAVE.setBackground(new java.awt.Color(45, 52, 54));
    }//GEN-LAST:event_SAVEMouseClicked

    private void saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseEntered
        SAVE.setBackground(new java.awt.Color(116, 185, 255));      // TODO add your handling code here:
    }//GEN-LAST:event_saveMouseEntered

    private void SAVEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVEMouseEntered
        SAVE.setBackground(new java.awt.Color(116, 185, 255));
    }//GEN-LAST:event_SAVEMouseEntered

    private void SAVEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SAVEMouseExited
        SAVE.setBackground(new java.awt.Color(0, 102, 255));

    }//GEN-LAST:event_SAVEMouseExited

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        try {
            updateCour();
        } catch (SQLException ex) {
            Logger.getLogger(Modf.class.getName()).log(Level.SEVERE, null, ex);
        }

        SAVE.setBackground(new java.awt.Color(45, 52, 54));


    }//GEN-LAST:event_saveMouseClicked

    private void save1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save1MouseClicked
        ff.setVisible(false);

    }//GEN-LAST:event_save1MouseClicked

    private void save1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save1MouseEntered

    }//GEN-LAST:event_save1MouseEntered

    private void annuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annuleMouseClicked
        ff.setVisible(false);

    }//GEN-LAST:event_annuleMouseClicked

    private void annuleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annuleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_annuleMouseEntered

    private void annuleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annuleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_annuleMouseExited

    private void Type_DesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Type_DesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Type_DesActionPerformed

    private void deletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletMouseClicked

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogReslt = JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Supprimer ?", "", dialogButton);
        if (dialogReslt == 0) {

            try {
                Connect.deleteCour(C,ID);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Modf.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Modf.class.getName()).log(Level.SEVERE, null, ex);
            }
            ff.setVisible(false);
        } else {

        }


    }//GEN-LAST:event_deletMouseClicked

    private void deletMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_deletMouseEntered

    private void B_ouiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ouiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_ouiActionPerformed

    private void B_en_trainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_en_trainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_en_trainActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adresse_Des;
    private javax.swing.JTextField Adresse_Exp;
    private javax.swing.JComboBox<String> Annexe_Exp;
    private javax.swing.JRadioButton B_en_train;
    private javax.swing.JRadioButton B_non;
    private javax.swing.JRadioButton B_oui;
    private javax.swing.JTextField CIN_Exp;
    private javax.swing.JTextField Date_Envoyer;
    private javax.swing.JTextField Date_Reception;
    private javax.swing.JTextField Date_Renvoyer;
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
    private javax.swing.JTextField Telephone_Des;
    private javax.swing.JTextField Telephone_Exp;
    private javax.swing.ButtonGroup Traite;
    private javax.swing.JComboBox<String> Type_Des;
    private javax.swing.JComboBox<String> Type_Exp;
    private javax.swing.JComboBox<String> Type_courr;
    private javax.swing.JPanel annule;
    private javax.swing.JLabel delet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel save;
    private javax.swing.JLabel save1;
    // End of variables declaration//GEN-END:variables
}
