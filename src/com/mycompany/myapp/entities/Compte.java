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
public class Compte {
    private String userName;
     private String mail;
      private String mdp;
       private String role;
       private String image;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role ="etudiant";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Compte() {
    }

    public Compte(String userName, String mail, String mdp, String role, String image) {
        this.userName = userName;
        this.mail = mail;
        this.mdp = mdp;
        this.role = role;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Compte{" + "userName=" + userName + ", mail=" + mail + ", mdp=" + mdp + ", role=" + role + ", image=" + image + '}';
    }
       
    
}
