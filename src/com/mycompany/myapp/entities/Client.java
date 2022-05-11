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
public class Client {
    private int idClient; 
   private String nom;
   private String prenom;
   private String DateNaiss;
   private String userName;
   private String role ; 

    public Client(int idC, String nom, String prenom, String DateNaiss, String userName, String role) {
        this.idClient = idC;
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaiss = DateNaiss;
        this.userName = userName;
        this.role = role;
    }

    public Client() {
    }

    public int getIdC() {
        return idClient;
    }

    public void setIdC(int idC) {
        this.idClient = idC;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(String DateNaiss) {
        this.DateNaiss = DateNaiss;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" + "idC=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", DateNaiss=" + DateNaiss + ", userName=" + userName + ", role=" + role + '}';
    }
   
}
