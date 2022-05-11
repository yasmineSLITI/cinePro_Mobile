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
public class Signale {
     private int idSignal;
    private int idClient;
    private int idPub;
    private int nbreSignal;
    
    public Signale() {
    } 

    @Override
    public String toString() {
        return "Signale{" + "idSignal=" + idSignal + ", idClient=" + idClient + ", idPub=" + idPub + ", nbreSignal=" + nbreSignal + '}';
    }
    
    
}
