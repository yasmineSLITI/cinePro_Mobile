/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import edu.CineProMabile.utils.Statics;
import edu.CineProMobile.entites.DemandeDeSponsoring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceDemande {
 

    public ArrayList<DemandeDeSponsoring> demande;

    public static ServiceDemande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceDemande() {
        req = new ConnectionRequest();
    }

    public static ServiceDemande getInstance() {
        if (instance == null) {
            instance = new ServiceDemande();
        }
        return instance;
        
    }

    public DemandeDeSponsoring parseDemande(String jsonText) {
        DemandeDeSponsoring t = new DemandeDeSponsoring();
        try {

            demande = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
            for (Map<String, Object> obj : list) {
               // t.setIdEv((int)Float.parseFloat(obj.get("")));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
   

    public ArrayList<DemandeDeSponsoring> getAllDemande() {
        req = new ConnectionRequest();
        demande=new ArrayList<>();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "afficherEventJSON";
        System.out.println("===>" + url);
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // demande = parseDemande(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demande;
    }

}
