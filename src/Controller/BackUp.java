package Controller;

import Controller.Connect;
import Model.Courrier;
import java.util.ArrayList;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BackUp {

    public static void setBackUp(String url) throws ClassNotFoundException, SQLException {
        Connection C = Connect.getConx2();
        ArrayList<Courrier> al = Connect.getCourrier(C);

        try {
            FileOutputStream fos = new FileOutputStream(url + "\\backUp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(al);
            oos.close();
            fos.close();
            
             JOptionPane.showMessageDialog(null, "les Donneés a été Exporté à"+url);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void getBackUp(String url) throws ClassNotFoundException, SQLException {
        Connection C = Connect.getConx2();
            Connect.VoidTables(C);
        ArrayList<Courrier> arraylist = new ArrayList<Courrier>();
        try {
            FileInputStream fis = new FileInputStream(url);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
               JOptionPane.showMessageDialog(null, "les Données a été Emporte ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        for (int a = 0; a < arraylist.size(); a++) {
            System.out.println(arraylist.get(a).toString());
        }
        Connect.saveBackup(C, arraylist);
    }
}
