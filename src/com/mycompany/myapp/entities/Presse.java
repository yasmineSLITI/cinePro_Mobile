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
public class Presse {
    private int id;
    private String userName;
    private boolean badgeAttribue = false;

    

    public Presse() {
    }

    

    

    

  

    

    public int getId() {
        return id;
    }

    public boolean getBadgeAttribue() {
        return badgeAttribue;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setBadgeAttribué(boolean badgeAttribue) {
        this.badgeAttribue = badgeAttribue;
    }

    @Override
    public String toString() {
        return "Presse{" + "id=" + id + ", userName=" + userName + ", badgeAttribue=" + badgeAttribue + '}';
    }

    

}
