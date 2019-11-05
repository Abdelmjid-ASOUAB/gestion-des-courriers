package Model;

/**
 * *********************************************************************
 * Module: Courrier.java Author: Abdelmjid ASOUAB Purpose: Defines the Class
 * Courrier
 * *********************************************************************
 */
import Model.*;
import java.io.Serializable;
import java.util.*;

public class Courrier implements Serializable {
    private int idRelation;
    private java.lang.String codeCour;
    private java.lang.String numOrd;
    private java.lang.String dateEnvoi;
    private java.lang.String numResption;
    private java.lang.String dateRenvoye;
    private java.lang.String objet;
    private java.lang.String dateResption;
    private java.lang.String observation;
    private java.lang.String priorite;
    private java.lang.String img;
    private java.lang.String typeCour;
    private Destination destination;
    private Expediteur expediteur;
    private String Traite;

    public String getNumOrd() {
        return numOrd;
    }

    public void setNumOrd(String numOrd) {
        this.numOrd = numOrd;
    }

    public String getCodeCour() {
        return codeCour;
    }

    public void setCodeCour(String codeCour) {
        this.codeCour = codeCour;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getNumResption() {
        return numResption;
    }

    public void setNumResption(String numResption) {
        this.numResption = numResption;
    }

    public String getDateRenvoye() {
        return dateRenvoye;
    }

    public void setDateRenvoye(String dateRenvoye) {
        this.dateRenvoye = dateRenvoye;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTypeCour() {
        return typeCour;
    }

    public void setTypeCour(String typeCour) {
        this.typeCour = typeCour;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Expediteur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Expediteur expediteur) {
        this.expediteur = expediteur;
    }

    public String getDateResption() {
        return dateResption;
    }

    public void setDateResption(String dateResption) {
        this.dateResption = dateResption;
    }

    public int getIdRelation() {
        return idRelation;
    }

    public void setIdRelation(int idRelation) {
        this.idRelation = idRelation;
    }

    public String getTraite() {
        return Traite;
    }

    public void setTraite(String Traite) {
        this.Traite = Traite;
    }

    @Override
    public String toString() {
        return "Courrier{" + "idRelation=" + idRelation + ", codeCour=" + codeCour + ", numOrd=" + numOrd + ", dateEnvoi=" + dateEnvoi + ", numResption=" + numResption + ", dateRenvoye=" + dateRenvoye + ", objet=" + objet + ", dateResption=" + dateResption + ", observation=" + observation + ", priorite=" + priorite + ", img=" + img + ", typeCour=" + typeCour + ", destination=" + destination + ", expediteur=" + expediteur + ", Traite=" + Traite + '}';
    }
    
}
