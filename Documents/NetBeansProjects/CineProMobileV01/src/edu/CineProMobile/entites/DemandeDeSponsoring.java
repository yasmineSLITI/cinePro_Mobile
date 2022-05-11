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
public class DemandeDeSponsoring {
  private int idEv;
    private int idDemande;
    private String etatAccept = "En attente";
    private String description;
    private int idSponsor;
    private String paquet ; 
    private String nomSpons ="lenna" ; 
    private String nomEv ; 
    private String des ; 

    public DemandeDeSponsoring() {
    }

    public DemandeDeSponsoring(int idEv, int idDemande, 
            int idSponsor, String paquet, String acc) {
        this.idEv = idDemande;
        this.idDemande = idEv;
        this.idSponsor = idSponsor;
        this.paquet = paquet;
        this.etatAccept = acc;
    }
    
    
    public DemandeDeSponsoring(int idEv,int idSponsor, String description, String paquet) {
        this.idEv = idEv;
        this.description = description;
        this.idSponsor = idSponsor;
        
        this.paquet = paquet;
    }
    public DemandeDeSponsoring(int idEv,int idSponsor, String description, String paquet, String etat) {
        this.idEv = idEv;
        this.description = description;
        this.idSponsor = idSponsor;
        this.etatAccept = etat;
        
        this.paquet = paquet;
    }
    public DemandeDeSponsoring(int id,int idEv,int idSponsor, String description, String acceptation, String paquet) {
        this.idEv = idEv;
        this.idDemande = id;
        this.description = description;
        this.idSponsor = idSponsor;
        this.etatAccept=acceptation;
        this.paquet = paquet;
    }

    public DemandeDeSponsoring(int idEv, String description, int idSponsor, String paquet) {
        this.idEv = idEv;
        this.description = description;
        this.idSponsor = idSponsor;
        this.paquet = paquet;
    }
    
    public DemandeDeSponsoring(int idEv, String etatAccept, String description, int idSponsor, String pqt) {
        this.idEv = idEv;
        this.etatAccept = etatAccept;
        this.description = description;
        this.idSponsor = idSponsor;
        this.paquet = pqt;
    }

    public DemandeDeSponsoring(int idEv, int idDemande, 
            String etatAccept, String description, int idSponsor, 
            String paquet, String nomSpons) {
        this.idEv = idEv;
        this.idDemande = idDemande;
        this.etatAccept = etatAccept;
        this.description = description;
        this.idSponsor = idSponsor;
        this.paquet = paquet;
        this.nomSpons = nomSpons;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public String getNomSpons() {
        return nomSpons;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public void setNomSpons(String nomSpons) {
        this.nomSpons = nomSpons;
    }
    
    
    
    public int getIdEv() {
        return idEv;
    }

    public String getEtatAccept() {
        return etatAccept;
    }

    public String getDescription() {
        return description;
    }

    public int getIdSponsor() {
        return idSponsor;
    }

    public String getPaquet() {
        return paquet;
    }

    public void setPaquet(String paquet) {
        this.paquet = paquet;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public void setEtatAccept(String etatAccept) {
        this.etatAccept = etatAccept;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdSponsor(int idSponsor) {
        this.idSponsor = idSponsor;
    }

    @Override
    public String toString() {
        return "DemandeDeSponsoring{" + "idEv=" + idEv + ", etatAccept=" + etatAccept + ", description=" + description + ", idSponsor=" + idSponsor + ",paquet = "+paquet+"}\n";
    }
    
}
