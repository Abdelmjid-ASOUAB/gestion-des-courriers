/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Abdelmjid ASOUAB
 */
public class Destination implements Serializable {

    private java.lang.String codeDes;
   
    private java.lang.String nomDest;
    
    private java.lang.String adresseDest;
    
    private java.lang.String teleDest;
    
    private java.lang.String typeDest;

    public String getCodeDes() {
        return codeDes;
    }

    public void setCodeDes(String codeDes) {
        this.codeDes = codeDes;
    }

    public String getNomDest() {
        return nomDest;
    }

    public void setNomDest(String nomDest) {
        this.nomDest = nomDest;
    }

    public String getAdresseDest() {
        return adresseDest;
    }

    public void setAdresseDest(String adresseDest) {
        this.adresseDest = adresseDest;
    }

    public String getTeleDest() {
        return teleDest;
    }

    public void setTeleDest(String teleDest) {
        this.teleDest = teleDest;
    }

    public String getTypeDest() {
        return typeDest;
    }

    public void setTypeDest(String typeDest) {
        this.typeDest = typeDest;
    }

    @Override
    public String toString() {
        return "Destination{" + "codeDes=" + codeDes + ", nomDest=" + nomDest + ", adresseDest=" + adresseDest + ", teleDest=" + teleDest + ", typeDest=" + typeDest + '}';
    }
    
    
}
