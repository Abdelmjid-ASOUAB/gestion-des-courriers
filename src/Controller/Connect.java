package Controller;

import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

//CTRL + SHIFT + O pour générer les imports
public class Connect {

    /*
    public static Statement getConx() throws ClassNotFoundException,SQLException {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

        final String DB_URL = "jdbc:mysql://localhost:3306/gestion";
        final String USER = "root";
        final String PASS = "";
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.print("\nConnecting to database...");
            conn = DriverManager.getConnection(DB_URL + unicode, USER, PASS);
            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {

        }
        return conn.createStatement();
    }*/
    public static Connection getConx2() throws ClassNotFoundException, SQLException {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
        final String DB_URL = "jdbc:mysql://localhost:3306/gestioncour";

       // final String DB_URL = "jdbc:mysql:gestioncour.sql";
        final String USER = "root";
        final String PASS = "";

//        final String DB_URL = "gestioncour.000webhostapp.com/id5456736_gestioncour";
//        final String USER = "id5456736_root";
//        final String PASS = "rootroot";
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.print("\nConnecting to database...");
            conn = DriverManager.getConnection(DB_URL + unicode, USER, PASS);
            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {
            if (se.toString().equals("com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown database 'gestioncourr'")) {
                System.err.println("data not found ");
                //  Runtime.getRuntime().exec("DIR C:\\xampp\\mysql\\bin mysql -uroot -p -h 127.0.0.1");
//                Runtime.getRuntime().exec("mysql -uroot -p -h 127.0.0.1");
//                Runtime.getRuntime().exec("create database inak");

                System.exit(0);
            } else if (se.toString().equals("com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure\n"
                    + "\n"
                    + "The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.")) {
                JOptionPane.showMessageDialog(null, "Ouvrez XAMPP !");
                System.exit(0);
            }
            System.err.println(se);

        }
        return conn;
    }

    public static ArrayList<Integer> getIdRelation(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> id = new ArrayList<Integer>();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT id  FROM relation_cour ");

            while (result.next()) {
                id.add(Integer.parseInt(result.getString("id")));

            }

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return id;
    }

    public static ArrayList<String> getPrio(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<String> typ = new ArrayList<String>();
        Statement stmt = null;
        ResultSet result = null;
        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT codeprio FROM priorite ");

            while (result.next()) {
                typ.add(result.getString("codeprio"));

            }
           

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return typ;
    }

    public static ArrayList<String> getTypExp(Connection C) throws ClassNotFoundException, SQLException {
        // Connection C=Connect.getConx2();
        ArrayList<String> typ = new ArrayList<String>();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT CODETYPEXP FROM typeexp ");

            while (result.next()) {
                typ.add(result.getString("CODETYPEXP"));

            }
           
            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return typ;
    }

    public static ArrayList<String> getAnnexe(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<String> typ = new ArrayList<String>();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT codeAnn FROM Annexe ");

            while (result.next()) {
                typ.add(result.getString("codeAnn"));

            }
 

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return typ;
    }

    public static ArrayList<String> getypeCour(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<String> typ = new ArrayList<String>();

        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT NOMTYP  FROM typecour ");

            while (result.next()) {
                typ.add(result.getString("nomtyp"));

            }

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return typ;
    }
    //Done

    public static ArrayList<Courrier> getCourrier(Connection C) throws ClassNotFoundException, SQLException {

        ArrayList<Courrier> id = new ArrayList<Courrier>();

        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT `NUMORD`, `CODECOU`, `CODETYPCOUR`, `CODEIMG`, `DATEENVOI`, `DATERESPTION`, `NUMRESPTION`, `DATERENVOYE`, `OBJET`, `OBSERVATION`, `PRIORITE` , `Traite` FROM `courrier` WHERE CODECOU IN (SELECT CODECOU FROM RELATION_COUR ) ");
            Courrier cour = null;

            ArrayList<Expediteur> exp = Connect.getExp(C);
            ArrayList<Destination> des = Connect.getDest(C);
            int i = 0;
            while (result.next()) {
                cour = new Courrier();
                cour.setIdRelation(getIdRelation(C).get(i));
                cour.setNumOrd(result.getString("NUMORD"));
                cour.setCodeCour(result.getString("CODECOU"));
                cour.setTypeCour(result.getString("CODETYPCOUR"));
                cour.setImg(result.getString("CODEIMG"));
                cour.setDateEnvoi(result.getString("DATEENVOI"));
                cour.setDateResption(result.getString("DATERESPTION"));
                cour.setNumResption(result.getString("NUMRESPTION"));
                cour.setDateRenvoye(result.getString("DATERENVOYE"));
                cour.setObjet(result.getString("OBJET"));
                cour.setObservation(result.getString("OBSERVATION"));
                cour.setPriorite(result.getString("PRIORITE"));
                cour.setExpediteur(exp.get(i));
                cour.setDestination(des.get(i));
                cour.setTraite(result.getString("Traite"));

                id.add(cour);
                i++;
            }

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return id;
    }

    //Done
    public static ArrayList<User> getUsers(Connection C) throws ClassNotFoundException, SQLException {

        ArrayList<User> id = new ArrayList<User>();

        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT * FROM users ");
            User user = null;

            int i = 0;
            while (result.next()) {
                user =new User();
                user.setId(result.getString("id_user"));
                user.setUser(result.getString("user"));
                user.setPwd(result.getString("pwd"));

                id.add(user);
                i++;
            }

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return id;
    }

    //Done
    public static ArrayList<Destination> getDest(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<Destination> id = new ArrayList<Destination>();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT `CODEDES`, `NOMDEST`, `ADRESSEDEST`, `TELEDEST`, `TYPEDEST` FROM `destination` WHERE CODEDES IN (SELECT CODEDES FROM RELATION_COUR ) ");
            Destination dest = null;
            while (result.next()) {
                dest = new Destination();

                dest.setCodeDes(result.getString("CODEDES"));
                dest.setNomDest(result.getString("NOMDEST"));
                dest.setAdresseDest(result.getString("ADRESSEDEST"));
                dest.setTeleDest(result.getString("TELEDEST"));
                dest.setTypeDest(result.getString("TYPEDEST"));

                id.add(dest);
            }

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return id;
    }

    //Done
    public static ArrayList<Expediteur> getExp(Connection C) throws ClassNotFoundException, SQLException {
        ArrayList<Expediteur> id = new ArrayList<Expediteur>();
        Statement stmt = null;
        ResultSet result = null;

        try {
            stmt = C.createStatement();            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery("SELECT `CODEEXP`, `CODEANN`, `NOMDES`, `CIN`, `ADRESSDES`, `TELEDES`, `EMAILDES`, `TYPEEXP` FROM `expediteur` WHERE CODEEXP IN (SELECT CODEEXP FROM RELATION_COUR ) ");
            Expediteur exp = null;
            while (result.next()) {
                exp = new Expediteur();

                exp.setCodeExp(result.getString("CODEEXP"));
                exp.setCodeAnn(result.getString("CODEANN"));
                exp.setNomExp(result.getString("NOMDES"));
                exp.setCin(result.getString("CIN"));
                exp.setAdressExp(result.getString("ADRESSDES"));
                exp.setTeleExp(result.getString("TELEDES"));
                exp.setEmailExp(result.getString("EMAILDES"));
                exp.setTypeExp(result.getString("TYPEEXP"));

                id.add(exp);
            }

            result.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.print("\n waloo  " + ex);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return id;
    }
//DOne

    public static int ajouteCour(Connection C, String... arg) throws SQLException {

        System.out.println(" ajoute  cour!\n");
        int returnKey = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `courrier`(`NUMORD`, `CODECOU`, `CODETYPCOUR`, `CODEIMG`, `DATEENVOI`, `DATERESPTION`, `NUMRESPTION`, `DATERENVOYE`, `OBJET`, `OBSERVATION`, `PRIORITE`, `Traite`) VALUES"
                    + " (\"" + arg[0] + "\",null" + ",\"" + arg[1] + "\",\"" + arg[2] + "\",\"" + arg[3] + "\",\"" + arg[4] + "\",\"" + arg[5] + "\",\"" + arg[6] + "\",\"" + arg[7] + "\",\"" + arg[8] + "\",\"" + arg[9] + "\",\"" + arg[10] + "\")";
            System.out.println(sqll);

            stmt = C.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            returnKey = rs.getInt(1);

            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thank you for your patronage!");
        return returnKey;

    }

    public static void ajouteAnnex(Connection C, String a) throws SQLException {

        System.out.println(" ajoute  cour!\n");
        int returnKey = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `annexe`(`CODEANN`, `NOMANN`) VALUES (\"" + a + "\",\"" + a + "\")";
            System.out.println(sqll);

            stmt = C.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // returnKey = rs.getInt(1);

            JOptionPane.showMessageDialog(null, "annexe Ajoutee !");

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error : " + se);
            se.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            e.printStackTrace();
        }


    }

    public static void ajouteTypeExp(Connection C, String a) throws SQLException {

        System.out.println(" ajoute  cour!\n");
        int returnKey = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `typeexp`(`CODETYPEXP`, `NOMTYPEXP`)  VALUES (\"" + a + "\",\"" + a + "\")";
            System.out.println(sqll);

            stmt = C.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // returnKey = rs.getInt(1);

            JOptionPane.showMessageDialog(null, "Type  Ajoutee !");

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error : " + se);
            se.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            e.printStackTrace();
        }


    }

    public static void ajouteTypeCour(Connection C, String a) throws SQLException {

        System.out.println(" ajoute  cour!\n");
        int returnKey = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `typecour` (`CODETYPCOUR`, `NOMTYP`) VALUES (\"" + a + "\",\"" + a + "\")";
            System.out.println(sqll);

            stmt = C.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // returnKey = rs.getInt(1);

            JOptionPane.showMessageDialog(null, "Type  Ajoutee !");

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error : " + se);
            se.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            e.printStackTrace();
        }


    }
//DOne

    public static int ajouteDest(Connection C, String... arg) throws SQLException {

        System.out.println(" ajoute  dest!\n");
        int returnKey = -1;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `destination`(`CODEDES`, `NOMDEST`, `ADRESSEDEST`, `TELEDEST`, `TYPEDEST`) VALUES  ("
                    + "null" + ",\"" + arg[0] + "\",\"" + arg[1] + "\",\"" + arg[2] + "\",\"" + arg[3] + "\")";

            stmt = C.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            returnKey = rs.getInt(1);

            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnKey;
    }
//DOne
    public static void ajouteUser(Connection C, String user,String pwd) throws SQLException {

        System.out.println(" ajoute  cour!\n");
        int returnKey = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `users`(`id_user`, `user`, `pwd`) VALUES (null,\"" + user + "\",\"" + pwd + "\")";
            System.out.println(sqll);

            stmt = C.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // returnKey = rs.getInt(1);

            JOptionPane.showMessageDialog(null, "User Ajoutee !");

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error : " + se);
            se.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            e.printStackTrace();
        }


    }

    public static int ajouteRelation(Connection C, int... arg) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        System.out.println(" ajoute  dest!\n");
        int returnKey = -1;

        try {
            // STEP 2: Register JDBC driver
            conn = C;

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `relation_cour`(`id`, `CODECOU`, `CODEDES`, `CODEEXP`) VALUES("
                    + "null" + "," + arg[0] + "," + arg[1] + "," + arg[2] + ")";

            stmt = conn.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            returnKey = rs.getInt(1);

            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnKey;
    }
//DOne

    public static int ajouteExp(Connection C, String... arg) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        System.out.println(" ajoute  Exp!\n");
        int returnKey = -1;

        try {
            // STEP 2: Register JDBC driver
            conn = C;

            //.executeUpdate(sql1);
            String sqll = "INSERT INTO `expediteur`(`CODEEXP`, `CODEANN`, `NOMDES`, `CIN`, `ADRESSDES`, `TELEDES`, `EMAILDES`, `TYPEEXP`) VALUES  ("
                    + "null" + ",\"" + arg[0] + "\",\"" + arg[1] + "\",\"" + arg[2] + "\",\"" + arg[3] + "\",\"" + arg[4] + "\",\"" + arg[5] + "\",\"" + arg[6] + "\")";
            System.out.println(sqll);
            stmt = conn.prepareStatement(sqll, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            returnKey = rs.getInt(1);

            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnKey;
    }

    public static void deleteCour(Connection C, int id) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ResultSet result = null;

        try {
            String sql = "SELECT CODECOU , CODEDES , CODEEXP FROM relation_cour WHERE id =" + id;
            int CODECOU = 0, CODEDES = 0, CODEEXP = 0;
            stmt = C.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery(sql);
            while (result.next()) {
                CODECOU = Integer.parseInt(result.getString("CODECOU"));
                CODEDES = Integer.parseInt(result.getString("CODEDES"));
                CODEEXP = Integer.parseInt(result.getString("CODEEXP"));
            }
            String deletRela = "DELETE FROM `relation_cour` WHERE id= " + id;
            String deletCour = "DELETE FROM `courrier` WHERE CODECOU = " + CODECOU;
            String deletExp = "DELETE FROM `courrier` WHERE CODEEXP = " + CODEEXP;
            String deletDest = "DELETE FROM `courrier` WHERE CODEDES = " + CODEDES;

            stmt.executeUpdate(deletRela);
            stmt.executeUpdate(deletCour);
            stmt.executeUpdate(deletExp);
            stmt.executeUpdate(deletDest);

        } catch (SQLException se) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }

    }

    /**
     *
     * @param arg
     * @param ID
     */
    public static void updateCour(Connection C, int ID, String... arg) throws SQLException {

        Statement stmt = null;
        ResultSet result = null;

        try {
            String sql = "SELECT CODECOU , CODEDES , CODEEXP FROM relation_cour WHERE id =" + ID;
            int CODECOU = 0, CODEDES = 0, CODEEXP = 0;
            stmt = C.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            result = stmt.executeQuery(sql);
            while (result.next()) {
                CODECOU = Integer.parseInt(result.getString("CODECOU"));
                CODEDES = Integer.parseInt(result.getString("CODEDES"));
                CODEEXP = Integer.parseInt(result.getString("CODEEXP"));
            }

            String upCour = "UPDATE `courrier` SET `NUMORD`='" + arg[9] + "',`CODETYPCOUR`='" + arg[16] + "',`CODEIMG`='img',`DATEENVOI`='" + arg[1] + "',`DATERESPTION`='" + arg[10] + "',`NUMRESPTION`='" + arg[17] + "',`DATERENVOYE`='" + arg[12] + "',`OBJET`='" + arg[11] + "',`OBSERVATION`='" + arg[18] + "',`PRIORITE`='" + arg[8] + "',`Traite`='" + arg[20] + "' WHERE CODECOU= '" + CODECOU + "'";
            String upExp = "UPDATE `expediteur` SET `CODEANN`='" + arg[7] + "',`NOMDES`='" + arg[3] + "',`CIN`='" + arg[6] + "',`ADRESSDES`='" + arg[0] + "',`TELEDES`='" + arg[4] + "',`EMAILDES`='" + arg[2] + "',`TYPEEXP`='" + arg[5] + "' WHERE  CODEEXP = '" + CODEEXP + "'";
            String upDes = "UPDATE `destination` SET `NOMDEST`='" + arg[13] + "',`ADRESSEDEST`='" + arg[14] + "',`TELEDEST`='" + arg[15] + "',`TYPEDEST`='" + arg[19] + "' WHERE CODEDES = '" + CODEDES + "'";
            System.out.println(upCour);
            System.out.println(upExp);
            System.out.println(upDes);

            stmt.executeUpdate(upCour);
            stmt.executeUpdate(upExp);
            stmt.executeUpdate(upDes);

            System.out.println(" SUCCESS!\n");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (result != null) {
                result.close();
            }
        }


    }

    public static void saveBackup(Connection C, ArrayList<Courrier> arr) throws SQLException {

        for (int i = 0; i < arr.size(); i++) {

            int codeDest = Connect.ajouteDest(C, arr.get(i).getDestination().getNomDest(), arr.get(i).getDestination().getAdresseDest(), arr.get(i).getDestination().getTeleDest(), arr.get(i).getDestination().getTypeDest());
            int codeExp = Connect.ajouteExp(C, arr.get(i).getExpediteur().getCodeAnn(), arr.get(i).getExpediteur().getNomExp(), arr.get(i).getExpediteur().getCin(), arr.get(i).getExpediteur().getAdressExp(), arr.get(i).getExpediteur().getTeleExp(), arr.get(i).getExpediteur().getEmailExp(), arr.get(i).getExpediteur().getTypeExp());
            int codeCour = Connect.ajouteCour(C, arr.get(i).getNumOrd(), arr.get(i).getTypeCour(), "img", arr.get(i).getDateEnvoi(), arr.get(i).getDateResption(), arr.get(i).getNumResption(), arr.get(i).getDateRenvoye(), arr.get(i).getObjet(), arr.get(i).getObservation(), arr.get(i).getPriorite(), arr.get(i).getTraite());
            System.out.println(Connect.ajouteRelation(C, codeCour, codeDest, codeExp));

        }

    }
    
    public static void VoidTables(Connection C) throws SQLException {

        System.out.println(" VoidTables !\n");
        int returnKey = -1;
        Connection conn = null;
       Statement stmt = null;
        stmt =C.createStatement();
        try {
            // STEP 2: Register JDBC driver

            //.executeUpdate(sql1);  TRUNCATE TABLE courrier; TRUNCATE TABLE destination; TRUNCATE TABLE expediteur;  SET FOREIGN_KEY_CHECKS=0;
            String sql = "TRUNCATE TABLE relation_cour";
            String sql1 = "TRUNCATE TABLE courrier";
            String sql2 = "TRUNCATE TABLE destination";
            String sql3 = "TRUNCATE TABLE expediteur";
            System.out.println(sql);

       String sql0 = "SET FOREIGN_KEY_CHECKS=0";
         String sql4 = "SET FOREIGN_KEY_CHECKS=1";    
           stmt.executeUpdate(sql0);
              
           stmt.executeUpdate(sql);
           stmt.executeUpdate(sql1);
           stmt.executeUpdate(sql2);
           stmt.executeUpdate(sql3);
   
         stmt.executeUpdate(sql4);
            // returnKey = rs.getInt(1);

            JOptionPane.showMessageDialog(null, "VoidTables !");

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error : " + se);
            se.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            e.printStackTrace();
        }


    }


}
