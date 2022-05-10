/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Asus
 */
public class Film {
    int idF;
    String nomF;
    String Genre;
    Boolean Archive;
    String EtatAcc;
    String Image;
    String Description;
    Integer Duree;

    public Film(String nomF, String Genre, Boolean Archive, String EtatAcc, String Image, String Description, Integer Duree) {
        this.nomF = nomF;
        this.Genre = Genre;
        this.Archive = Archive;
        this.EtatAcc = EtatAcc;
        this.Image = Image;
        this.Description = Description;
        this.Duree = Duree;
    }

    public Integer getDuree() {
        return Duree;
    }

    public void setDuree(Integer Duree) {
        this.Duree = Duree;
    }

   

    public int getIdF() {
        return idF;
    }

    public String getNomF() {
        return nomF;
    }

    public String getGenre() {
        return Genre;
    }

    public Boolean getArchive() {
        return Archive;
    }

    public String getEtatAcc() {
        return EtatAcc;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public void setArchive(Boolean Archive) {
        this.Archive = Archive;
    }

    public void setEtatAcc(String EtatAcc) {
        this.EtatAcc = EtatAcc;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Film{" + "idF=" + idF + ", nomF=" + nomF + ", Genre=" + Genre + ", Archive=" + Archive + ", EtatAcc=" + EtatAcc + ", Image=" + Image + ", Description=" + Description + '}';
    }
    
    
    
}
