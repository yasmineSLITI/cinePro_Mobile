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
public class ServiceFacture {

    public ArrayList<Facture> factures;

    public static ServiceFacture instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceFacture() {
        req = new ConnectionRequest();
    }

    public static ServiceFacture getInstance() {
        if (instance == null) {
            instance = new ServiceFacture();
        }
        return instance;
    }

    public boolean addFacture(Facture f) {
        System.out.println(f);
        System.out.println("********");
        String url = Statics.BASE_URL + "ajouterfacture/new/1";
        //String url = Statics.BASE_URL + "ajouterfacture";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("idPanier", f.getIdPanier());

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

    public ArrayList<Facture> parseFacture(String jsonText) {
        try {
            factures = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> facturesListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>)facturesListJson.get("root");
            for (Map<String, Object> obj : list) {
                Facture f = new Facture();
                float id = Float.parseFloat(obj.get("idfacture").toString());
                f.setIdFacture((int) id);
                 f.setDateCreation(obj.get("datecreation").toString());
                  f.setTotal(((int) Float.parseFloat(obj.get("total").toString())));
                f.setIdPanier(obj.get("idpanier").toString());

               

               
                factures.add(f);
            }

        } catch (IOException ex) {
            System.out.println("tt");
        }
        return factures;
    }

    public ArrayList<Facture> getAllFacture() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "AllFacture";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                factures = parseFacture(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return factures;
    }
public boolean removeFacture(Facture f) {
        System.out.println(f);
        System.out.println("********");
        String url = Statics.BASE_URL + "removeFacture";
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
}
