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
public class Produit {
    private int IDProduit ;
    private String Designation;
    private String Description;
    private String Image;
    
    private int QuantiteEnStock ; 
    
    private float prixAchatUnit;
    private float prixVenteUnit;
    public boolean StatusStock;

    public int getIDProduit() {
        return IDProduit ;
    }

    public void setIdP(int idP) {
        this.IDProduit  = idP;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getQuantiteEnStock() {
        return QuantiteEnStock;
    }

    public void setQuantiteEnStock(int QuantiteEnStock) {
        this.QuantiteEnStock = QuantiteEnStock;
    }

    public float getPrixAchatUnit() {
        return prixAchatUnit;
    }

    public void setPrixAchatUnit(float prixAchatUnit) {
        this.prixAchatUnit = prixAchatUnit;
    }

    public float getPrixVenteUnit() {
        return prixVenteUnit;
    }

    public void setPrixVenteUnit(float prixVenteUnit) {
        this.prixVenteUnit = prixVenteUnit;
    }

    public boolean isStatusStock() {
        return StatusStock;
    }

    public void setStatusStock(boolean StatusStock) {
        this.StatusStock = StatusStock;
    }

    public Produit(int idP, String Designation, String Description, String Image, int QuantiteEnStock, float prixAchatUnit, float prixVenteUnit, boolean StatusStock) {
        this.IDProduit  = idP;
        this.Designation = Designation;
        this.Description = Description;
        this.Image = Image;
        this.QuantiteEnStock = QuantiteEnStock;
        this.prixAchatUnit = prixAchatUnit;
        this.prixVenteUnit = prixVenteUnit;
        this.StatusStock = StatusStock;
    }

    public Produit() {
    }

    @Override
    public String toString() {
        return "Produit{" + "idP=" + IDProduit  + ", Designation=" + Designation + ", Description=" + Description + ", Image=" + Image + ", QuantiteEnStock=" + QuantiteEnStock + ", prixAchatUnit=" + prixAchatUnit + ", prixVenteUnit=" + prixVenteUnit + ", StatusStock=" + StatusStock + '}';
    }

    
    

}