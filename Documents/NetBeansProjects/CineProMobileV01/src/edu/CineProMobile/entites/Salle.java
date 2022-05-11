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
public class Salle {
    private float idSalle;
    private float capacité;
    private String dateDerniereMaintenance;
    private String enMaintenance ="Maintenue";
    private String nomS;
    private String dispo = "Disponible";
    
    
    public Salle() {
    }
    
    public Salle (String lenna){
        idSalle = 1;
        capacité = 589;
        
    }
    public Salle (int id){
        idSalle = id;
        
    }
    public Salle(int capacité, String dateDerniereMaintenance) {
        this.capacité = capacité;
        this.dateDerniereMaintenance = dateDerniereMaintenance;
    }
    public Salle(int id,int capacité, String dateDerniereMaintenance, String etat) {
        this.idSalle=id;
        this.capacité = capacité;
        this.dateDerniereMaintenance = dateDerniereMaintenance;
        this.enMaintenance = etat;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }

    public String getNomS() {
        return nomS;
    }
    public Salle(int id,int capacité, String dateDerniereMaintenance, String etat, String nm) {
        this.idSalle=id;
        this.capacité = capacité;
        this.dateDerniereMaintenance = dateDerniereMaintenance;
        this.enMaintenance = etat;
        this.nomS = nm;
    }
    public Salle(int id,int capacité, String dateDerniereMaintenance, String etat, String nm, String fr) {
        this.idSalle=id;
        this.capacité = capacité;
        this.dateDerniereMaintenance = dateDerniereMaintenance;
        this.enMaintenance = etat;
        this.nomS = nm;
        this.dispo = fr;
    }
    public Salle(int capacité, String dateDerniereMaintenance, String etat, String nm) {
        
        this.capacité = capacité;
        this.dateDerniereMaintenance = dateDerniereMaintenance;
        this.enMaintenance = etat;
        this.nomS = nm;
    }
    public Salle(int capacité, String dateDerniereMaintenance, String etat, String nm, String dispo) {
        
        this.capacité = capacité;
        this.dateDerniereMaintenance = dateDerniereMaintenance;
        this.enMaintenance = etat;
        this.nomS = nm;
        this.dispo=dispo;
    }
    
    public void setId(float id) {
        this.idSalle = id;
    }

    public float getId() {
        return idSalle;
    }
    public float getCapacite() {
        return capacité;
    }

    public String getEnMaintenance() {
        return enMaintenance;
    }
    

    public String getDateDerniereMaintenance() {
        return dateDerniereMaintenance;
    }

    public float getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String setEnMaintenance() {
        return enMaintenance;
    }

    public void setEnMaintenance(String enMaintenance) {
        this.enMaintenance = enMaintenance;
    }

    public void setCapacité(Float capacité) {
        this.capacité = capacité;
    }

    public void setDateDerniereMaintenance(String dateDerniereMaintenance) {
        this.dateDerniereMaintenance = dateDerniereMaintenance;
    }
    
    public void alertMaintenance(){
        
    }

    public String getDispo() {
        return dispo;
    }
    

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }
    public String toString() {
        String sh = "Salle {  id =  "+idSalle+"  , nom = "+nomS+"  , capacite =  "+capacité +"  disponible = "
                +dispo+"   date de maintenance =  "+dateDerniereMaintenance+",     maintenenace =  " +enMaintenance
                +"}  \n"; 
        
        return sh;
    }
    
    
}
