package View;

import Controller.Connect;
import Model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdelmjid ASOUAB
 */
public class accueilFrame extends javax.swing.JFrame {

    ArrayList<Courrier> aaa;
    Connection C = Connect.getConx2();
    Ajoute Ajout;
    Table table;
    int admin = 0;

    public accueilFrame() throws ClassNotFoundException, IOException, SQLException {
        
        this.Ajout = new Ajoute();
        this.table = new Table();
        int rec, dem, ann,aut;
        rec = dem = ann = aut=0;
        aaa = Connect.getCourrier(C);
        for (int h = 0; h < aaa.size(); h++) {
            if (aaa.get(h).getTypeCour().equals("RECLAMATION")) {
                rec++;
            } else if (aaa.get(h).getTypeCour().equals("DEMANDE")) {
                dem++;
            } else if (aaa.get(h).getTypeCour().equals("ANNONCE")) {
                ann++;
            }else{
                aut++;
            }
        }

        initComponents();
        this.setSize(1100, 680);
        header.setVisible(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        Rec_lab.setText(rec + "");
        Dem_lab.setText(dem + "");
        Ann_lab.setText(ann + "");
        Autr_lab.setText(aut+"");
        total.setText(rec+dem+ann+aut+"");

        Home.add(Ajout);
        Home.add(table);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/icon.png")));
        this.setTitle("Gestion Des Courriers");
        ArrayList<Courrier> c = Connect.getCourrier(C);

        Ajout.setVisible(false);
        Accueil.setVisible(true);
        table.setVisible(false);

        position.setText("Accueil");

        LocalDate localDate = LocalDate.now();

        year.setText(DateTimeFormatter.ofPattern("yyy").format(localDate));
        mois.setText(DateTimeFormatter.ofPattern("MMMM").format(localDate).toUpperCase());
        day.setText(DateTimeFormatter.ofPattern("dd").format(localDate));
        time.setText(new SimpleDateFormat("hh").format(new Date()));
        min.setText(new SimpleDateFormat("mm").format(new Date()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        header = new javax.swing.JPanel();
        hom_bar = new javax.swing.JPanel();
        position = new javax.swing.JLabel();
        tab_home = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Home = new javax.swing.JPanel();
        Accueil = new javax.swing.JPanel();
        Ann_lab = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        Autr_lab = new javax.swing.JLabel();
        Dem_lab = new javax.swing.JLabel();
        Rec_lab = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tab4 = new javax.swing.JLabel();
        tab6 = new javax.swing.JLabel();
        tab5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        mois = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        day = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        tab7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        addusr = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        getContentPane().setLayout(null);

        header.setBackground(new java.awt.Color(0, 168, 255));
        header.setLayout(null);

        hom_bar.setBackground(new java.awt.Color(72, 126, 176));

        javax.swing.GroupLayout hom_barLayout = new javax.swing.GroupLayout(hom_bar);
        hom_bar.setLayout(hom_barLayout);
        hom_barLayout.setHorizontalGroup(
            hom_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );
        hom_barLayout.setVerticalGroup(
            hom_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        header.add(hom_bar);
        hom_bar.setBounds(10, 50, 72, 10);

        position.setFont(new java.awt.Font("OptimusPrinceps", 0, 36)); // NOI18N
        position.setForeground(new java.awt.Color(255, 255, 255));
        position.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        position.setText("jLabel8");
        header.add(position);
        position.setBounds(390, 0, 400, 47);

        tab_home.setBackground(new java.awt.Color(0, 168, 255));
        tab_home.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        tab_home.setForeground(new java.awt.Color(220, 221, 225));
        tab_home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        tab_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab_home.setOpaque(true);
        tab_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_homeMouseClicked(evt);
            }
        });
        header.add(tab_home);
        tab_home.setBounds(0, 0, 90, 50);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        header.add(jLabel8);
        jLabel8.setBounds(1354, 0, 125, 82);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/head.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        header.add(jLabel2);
        jLabel2.setBounds(0, 0, 1500, 50);

        getContentPane().add(header);
        header.setBounds(0, 0, 1500, 50);

        Home.setBackground(new java.awt.Color(102, 255, 102));
        Home.setLayout(new javax.swing.OverlayLayout(Home));

        Accueil.setBackground(new java.awt.Color(255, 255, 255));
        Accueil.setLayout(null);

        Ann_lab.setFont(new java.awt.Font("Corbel", 1, 48)); // NOI18N
        Ann_lab.setForeground(new java.awt.Color(255, 255, 255));
        Ann_lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ann_lab.setText("----");
        Accueil.add(Ann_lab);
        Ann_lab.setBounds(220, 150, 140, 50);

        total.setFont(new java.awt.Font("Corbel", 1, 48)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("----");
        Accueil.add(total);
        total.setBounds(320, 220, 140, 70);

        Autr_lab.setFont(new java.awt.Font("Corbel", 1, 48)); // NOI18N
        Autr_lab.setForeground(new java.awt.Color(255, 255, 255));
        Autr_lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Autr_lab.setText("----");
        Accueil.add(Autr_lab);
        Autr_lab.setBounds(50, 150, 140, 50);

        Dem_lab.setFont(new java.awt.Font("Corbel", 1, 48)); // NOI18N
        Dem_lab.setForeground(new java.awt.Color(255, 255, 255));
        Dem_lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dem_lab.setText("----");
        Accueil.add(Dem_lab);
        Dem_lab.setBounds(380, 150, 140, 50);

        Rec_lab.setFont(new java.awt.Font("Corbel", 1, 48)); // NOI18N
        Rec_lab.setForeground(new java.awt.Color(255, 255, 255));
        Rec_lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Rec_lab.setText("----");
        Accueil.add(Rec_lab);
        Rec_lab.setBounds(550, 140, 140, 50);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ajouter");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accueil.add(jLabel6);
        jLabel6.setBounds(20, 320, 200, 50);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Registry_Editor_96px_1.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accueil.add(jLabel10);
        jLabel10.setBounds(550, 380, 110, 110);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accueil.add(jLabel9);
        jLabel9.setBounds(310, 390, 100, 100);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accueil.add(jLabel7);
        jLabel7.setBounds(60, 390, 110, 100);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 31)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Rechercher");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accueil.add(jLabel5);
        jLabel5.setBounds(260, 320, 200, 50);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/G.jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        Accueil.add(jLabel11);
        jLabel11.setBounds(830, 250, 270, 220);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/butt.png"))); // NOI18N
        Accueil.add(jLabel3);
        jLabel3.setBounds(30, 90, 670, 220);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Paramètre");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accueil.add(jLabel4);
        jLabel4.setBounds(500, 320, 200, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abdelmjid ASOUAB\\Desktop\\Logo_Agadiaar.png")); // NOI18N
        Accueil.add(jLabel1);
        jLabel1.setBounds(810, 50, 260, 210);

        tab4.setBackground(new java.awt.Color(60, 64, 198));
        tab4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        tab4.setForeground(new java.awt.Color(220, 221, 225));
        tab4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4.setOpaque(true);
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });
        Accueil.add(tab4);
        tab4.setBounds(260, 320, 200, 210);

        tab6.setBackground(new java.awt.Color(245, 59, 87));
        tab6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        tab6.setForeground(new java.awt.Color(220, 221, 225));
        tab6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab6.setOpaque(true);
        tab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab6MouseEntered(evt);
            }
        });
        Accueil.add(tab6);
        tab6.setBounds(500, 320, 200, 210);

        tab5.setBackground(new java.awt.Color(15, 188, 249));
        tab5.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        tab5.setForeground(new java.awt.Color(220, 221, 225));
        tab5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab5.setOpaque(true);
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
        });
        Accueil.add(tab5);
        tab5.setBounds(20, 320, 200, 210);

        jPanel1.setBackground(new java.awt.Color(60, 64, 198));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 5));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 5));
        jPanel1.setLayout(null);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(210, 38, 70, 2);

        mois.setBackground(new java.awt.Color(60, 64, 198));
        mois.setFont(new java.awt.Font("Segoe UI Emoji", 1, 20)); // NOI18N
        mois.setForeground(new java.awt.Color(255, 255, 255));
        mois.setText("APR");
        jPanel1.add(mois);
        mois.setBounds(100, 10, 80, 20);

        min.setFont(new java.awt.Font("Andes", 1, 40)); // NOI18N
        min.setForeground(new java.awt.Color(245, 59, 87));
        min.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        min.setText("20");
        jPanel1.add(min);
        min.setBounds(190, 30, 110, 60);

        day.setFont(new java.awt.Font("Andes", 1, 60)); // NOI18N
        day.setForeground(new java.awt.Color(245, 59, 87));
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setText("30");
        jPanel1.add(day);
        day.setBounds(0, 0, 100, 80);

        year.setBackground(new java.awt.Color(72, 126, 176));
        year.setFont(new java.awt.Font("Andes", 1, 36)); // NOI18N
        year.setForeground(new java.awt.Color(255, 255, 255));
        year.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        year.setText("2018");
        year.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(year);
        year.setBounds(90, 30, 90, 40);

        time.setFont(new java.awt.Font("Andes", 1, 40)); // NOI18N
        time.setForeground(new java.awt.Color(245, 59, 87));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("00");
        time.setToolTipText("Heur");
        jPanel1.add(time);
        time.setBounds(190, -10, 110, 60);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(210, 0, 70, 80);

        Accueil.add(jPanel1);
        jPanel1.setBounds(790, 470, 470, 80);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tst.jpg"))); // NOI18N
        Accueil.add(img);
        img.setBounds(0, -10, 1500, 630);

        tab7.setBackground(new java.awt.Color(0, 168, 255));
        tab7.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        tab7.setForeground(new java.awt.Color(220, 221, 225));
        tab7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab7.setText("Rechercher");
        tab7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab7.setOpaque(true);
        tab7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab7MouseClicked(evt);
            }
        });
        Accueil.add(tab7);
        tab7.setBounds(250, 80, 200, 210);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abdelmjid ASOUAB\\Desktop\\stage\\reclamation.png")); // NOI18N
        Accueil.add(jLabel13);
        jLabel13.setBounds(80, 130, 130, 140);

        Home.add(Accueil);

        getContentPane().add(Home);
        Home.setBounds(0, 7, 1500, 640);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Ajoute courrier");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Rechercher");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("close");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        addusr.setText("Option");

        jMenu3.setText("Parametre");

        jMenu4.setText("Backup");

        jMenuItem6.setText("Exporter");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setText("importer");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenu3.add(jMenu4);

        addusr.add(jMenu3);

        jMenuBar1.add(addusr);

        jMenu5.setText("About");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    int xx;
    int xy;
    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        // TODO add your handling code here:

        //tab1.setBackground(new Color(25, 42, 86));
        hom_bar.setBackground(new Color(72, 126, 176));
        //tab3.setBackground(new Color(0, 168, 255));
        position.setText("Rechercher");
        header.setVisible(true);

        table.setVisible(true);
        Ajout.setVisible(false);
        Accueil.setVisible(false);
//        jScrollPane1.getVerticalScrollBar().setValue(0);


    }//GEN-LAST:event_tab4MouseClicked

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
        // TODO add your handling code here:

        hom_bar.setBackground(new Color(72, 126, 176));
        header.setVisible(true);

        table.setVisible(false);
        Accueil.setVisible(false);
        Ajout.setVisible(true);
        position.setText("Ajouter Un Courrier");
        //  jScrollPane1.getVerticalScrollBar().setValue(0);

    }//GEN-LAST:event_tab5MouseClicked

    private void tab_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_homeMouseClicked

        position.setText("Accueil");
        header.setVisible(false);

        table.setVisible(false);
        Accueil.setVisible(true);
        Ajout.setVisible(false);
        // jScrollPane1.getVerticalScrollBar().setValue(0);
        
        int rec,dem,aut,ann=0;
        rec=dem=aut=0;
            Connection aqqa =null;
        try {
            aqqa = Connect.getConx2();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            aaa = Connect.getCourrier(aqqa);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int h = 0; h < aaa.size(); h++) {
            if (aaa.get(h).getTypeCour().equals("RECLAMATION")) {
                rec++;
            } else if (aaa.get(h).getTypeCour().equals("DEMANDE")) {
                dem++;
            } else if (aaa.get(h).getTypeCour().equals("ANNONCE")) {
                ann++;
            }else{
            aut++;
            }
            
        }

         Rec_lab.setText(rec + "");
        Dem_lab.setText(dem + "");
        Ann_lab.setText(ann + "");
        Autr_lab.setText(aut+"");

        total.setText(rec+dem+ann+aut+"");

        try {
            aqqa.close();
        } catch (SQLException ex) {
            Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tab_homeMouseClicked

    private void tab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseClicked
        param fb = new param();
        fb.setVisible(true);

    }//GEN-LAST:event_tab6MouseClicked

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tab7MouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        
        LocalDate localDate = LocalDate.now();
        year.setText(DateTimeFormatter.ofPattern("yyy").format(localDate));
        mois.setText(DateTimeFormatter.ofPattern("MMMM").format(localDate).toUpperCase());
        day.setText(DateTimeFormatter.ofPattern("dd").format(localDate));
        time.setText(new SimpleDateFormat("hh").format(new Date()));
        min.setText(new SimpleDateFormat("mm").format(new Date()));
        
        

    }//GEN-LAST:event_formMouseMoved

    private void tab6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseEntered

    }//GEN-LAST:event_tab6MouseEntered

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        hom_bar.setBackground(new Color(72, 126, 176));
        header.setVisible(true);

        table.setVisible(false);
        Accueil.setVisible(false);
        Ajout.setVisible(true);
        position.setText("Ajoute Un Courrier");    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        //tab1.setBackground(new Color(25, 42, 86));
        hom_bar.setBackground(new Color(72, 126, 176));
        //tab3.setBackground(new Color(0, 168, 255));
        position.setText("Rechercher");
        header.setVisible(true);

        table.setVisible(true);
        Ajout.setVisible(false);
        Accueil.setVisible(false);    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        Ajoute_Annexe A = null;
        int dialogReslt = JOptionPane.showConfirmDialog(null, "voulez vous fermer l'application?", "", dialogButton);
        if (dialogReslt == 0) {
            System.exit(0);
        }    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        frameBackUp fb = new frameBackUp("setBackup");
        fb.setVisible(true);     }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        frameBackUp fb = new frameBackUp("getBackup");
        fb.setVisible(true);    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        frameBackUp fb = new frameBackUp("getBackup");
        fb.setVisible(true);     }//GEN-LAST:event_jMenu5ActionPerformed

    /**
     * @param args the command line arguments //
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(accueilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(accueilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(accueilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(accueilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new accueilFrame().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(accueilFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Accueil;
    private javax.swing.JLabel Ann_lab;
    private javax.swing.JLabel Autr_lab;
    private javax.swing.JLabel Dem_lab;
    private javax.swing.JPanel Home;
    private javax.swing.JLabel Rec_lab;
    private javax.swing.JMenu addusr;
    private javax.swing.JLabel day;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hom_bar;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel min;
    private javax.swing.JLabel mois;
    private javax.swing.JLabel position;
    private javax.swing.JLabel tab4;
    private javax.swing.JLabel tab5;
    private javax.swing.JLabel tab6;
    private javax.swing.JLabel tab7;
    private javax.swing.JLabel tab_home;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total;
    private javax.swing.JLabel year;
    // End of variables declaration//GEN-END:variables
}
