/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ServicePanier {
      public ArrayList<Panier> paniers;

    public static ServicePanier instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePanier() {
        req = new ConnectionRequest();
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    public boolean addPanier(Panier p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL + "ajouterPan/4";
        //String url = Statics.BASE_URL + "ajouterfacture";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("idProduit", p.getIdProduit());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean removePanier(Panier p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL + "supprimerPanier";
        //String url = Statics.BASE_URL + "ajouterfacture";

        req.setUrl(url);
        req.setPost(false);
        //req.addArgument("idProduit", p.getIdProduit());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Panier> parsePanier(String jsonText) {
        try {
            paniers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> paniersListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>)paniersListJson.get("root");
            for (Map<String, Object> obj : list) {
                Panier p = new Panier();
                float id = Float.parseFloat(obj.get("idpanier").toString());
                p.setIdPanier("id");
                p.setIdProduit(obj.get("idproduit").toString());
                p.setIdBillet(obj.get("idbillet").toString());
                p.setIdClient(obj.get("idclient").toString());
                p.setNomPanier(obj.get("nompanier").toString());

               

               
                paniers.add(p);
            }

        } catch (IOException ex) {
            System.out.println("tt");
        }
        return paniers;
    }

    public ArrayList<Panier> getAllPanier() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "ListePanier";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paniers = parsePanier(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return paniers;
    }

}
