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
public class Expediteur implements Serializable {

    private java.lang.String codeExp;
    private java.lang.String nomExp;
    private java.lang.String cin;
    private java.lang.String adressExp;
    private java.lang.String teleExp;
    private java.lang.String faxExp;
    private java.lang.String emailExp;
    private java.lang.String typeExp;
    private java.lang.String CodeAnn;

    private String getCodeExp() {
        return codeExp;
    }

    public void setCodeExp(String codeExp) {
        this.codeExp = codeExp;
    }

    public String getNomExp() {
        return nomExp;
    }

    public void setNomExp(String nomExp) {
        this.nomExp = nomExp;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdressExp() {
        return adressExp;
    }

    public void setAdressExp(String adressExp) {
        this.adressExp = adressExp;
    }

    public String getTeleExp() {
        return teleExp;
    }

    public void setTeleExp(String teleExp) {
        this.teleExp = teleExp;
    }

    public String getFaxExp() {
        return faxExp;
    }

    public void setFaxExp(String faxExp) {
        this.faxExp = faxExp;
    }

    public String getEmailExp() {
        return emailExp;
    }

    public void setEmailExp(String emailExp) {
        this.emailExp = emailExp;
    }

    public String getTypeExp() {
        return typeExp;
    }

    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp;
    }

    public String getCodeAnn() {
        return CodeAnn;
    }

    public void setCodeAnn(String CodeAnn) {
        this.CodeAnn = CodeAnn;
    }

    @Override
    public String toString() {
        return "Expediteur{" + "codeExp=" + codeExp + ", nomExp=" + nomExp + ", cin=" + cin + ", adressExp=" + adressExp + ", teleExp=" + teleExp + ", faxExp=" + faxExp + ", emailExp=" + emailExp + ", typeExp=" + typeExp + ", CodeAnn=" + CodeAnn + '}';
    }

}
