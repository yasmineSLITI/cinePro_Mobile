/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author hp
 */
public class Billet {
    private int IDBillet;
   private String categorieBillet;

    public Billet() {
    }

    public Billet(int IDBillet, String categorieBillet, int nb_place, String created_on, boolean archived, int idEvenement, int idClient) {
        this.IDBillet = IDBillet;
        this.categorieBillet = categorieBillet;
        this.nb_place = nb_place;
        this.created_on = created_on;
        this.archived = archived;
        this.idEvenement = idEvenement;
        this.idClient = idClient;
    }
   
 
    private int nb_place;
     private String created_on;
    private boolean archived;
    private int idEvenement;
    private int idClient;

    public int getIDBillet() {
        return IDBillet;
    }

    public void setIDBillet(int IDBillet) {
        this.IDBillet = IDBillet;
    }

    public String getCategorieBillet() {
        return categorieBillet;
    }

    public void setCategorieBillet(String categorieBillet) {
        this.categorieBillet = categorieBillet;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Billet{" + "IDBillet=" + IDBillet + ", categorieBillet=" + categorieBillet + ", nb_place=" + nb_place + ", created_on=" + created_on + ", archived=" + archived + ", idEvenement=" + idEvenement + ", idClient=" + idClient + '}';
    }
    

    
}
