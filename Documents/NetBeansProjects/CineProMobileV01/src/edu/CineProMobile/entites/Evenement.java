/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.entites;

/**
 *
 * @author user
 */
public class Evenement {
  private int idev  ;
  private String etat= "En attente";
  private float montant;
  private int duree;
  private int progret = 0;
  private String nomev;
  private String description;

    public Evenement() {
    }

    public int getIdev() {
        return idev;
    }

    public void setIdev(float idev) {
        this.idev = (int)idev;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getProgret() {
        return progret;
    }

    public void setProgret(int progret) {
        this.progret = progret;
    }

    public String getNomev() {
        return nomev;
    }

    public void setNomev(String nomev) {
        this.nomev = nomev;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @Override
    public String toString() {
                return "Evenement{  nom de l'event =" + nomev +  ", etat d'acceptation =" + etat + ", description=" + description +
                        " , progret " + progret+ ",montant de sponsoring demandé =  "+montant+ "}    \n";

    }
    
  

}