/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author sarra
 */
public class Publication {
    private int idPub;
    private String titre;
    private String imgPub;
    private String txtPub;
    
    private int idPresse;
    private boolean archive;
    
    public Publication() {
    }

    public Publication(int idPub, String titre, String imgPub, String txtPub) {
        this.idPub = idPub;
        this.titre = titre;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
    }

    public Publication(String titre, String imgPub, String txtPub) {
        this.titre = titre;
        this.imgPub = imgPub;
        this.txtPub = txtPub;
    }

    public int getIdPub() {
        return idPub;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImgPub() {
        return imgPub;
    }

    public void setImgPub(String imgPub) {
        this.imgPub = imgPub;
    }

    public String getTxtPub() {
        return txtPub;
    }

    public void setTxtPub(String txtPub) {
        this.txtPub = txtPub;
    }

    

    public int getIdPresse() {
        return idPresse;
    }

    public void setIdPresse(int idPresse) {
        this.idPresse = idPresse;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Publication{" + "idPub=" + idPub + ", titre=" + titre + ", imgPub=" + imgPub + ", txtPub=" + txtPub + '}';
    }

   
    
}
