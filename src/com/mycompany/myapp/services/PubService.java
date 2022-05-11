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
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sarra
 */
public class PubService {

    public ArrayList<Publication> Publication;

    public static PubService instance = null;
    public boolean resultOK;
    public boolean resultOkl=true;
    private ConnectionRequest req;

    PubService() {
        req = new ConnectionRequest();
    }

    public static PubService getInstance() {
        if (instance == null) {
            instance = new PubService();
        }
        return instance;
    }

    public boolean addPub(Publication p) {
        String url = Statics.BASE_URL + "addPubJson/1?titre=" + p.getTitre() + "&imgpub=" + p.getImgPub() + "&txtpub=" + p.getTxtPub();

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("titre", p.getTitre());
        req.addArgument("imgPub", p.getImgPub() + "");
        req.addArgument("txtPub", p.getTxtPub() + "");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Publication> parsePub(String jsonText) {
        try {
            Publication = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Publication t = new Publication();
               int idp = (int) Float.parseFloat(obj.get("idpub").toString());
////                String titre = obj.get("titre").toString();
////                String imgpub = obj.get("imgPub").toString();
////                String txtpub = obj.get("txtPub").toString();
//
                t.setIdPub((int) idp);
                t.setTitre(obj.get("titre").toString());
                t.setImgPub(obj.get("imgpub").toString()); 
                t.setTxtPub(obj.get("txtpub").toString());
//               
               // Publication t = new Publication((int) idp, titre, imgpub, txtpub);

                Publication.add(t);
            }

        } catch (IOException ex) {
        }
        return Publication;
    }

    public ArrayList<Publication> getAll() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "allPubs/json";
        //System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET"); //Change to GET if necessary
        req.setDuplicateSupported(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Publication = parsePub(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Publication;
    }
    
         public boolean supprimerPub(int id){
     
        String url=Statics.BASE_URL+"deletePubsJson/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOkl;
     }

    public boolean UpdatePub(Publication p) {

        String url = Statics.BASE_URL + "editPubJson/" + p.getIdPub() + "?titre=" + p.getTitre() + "&imgpub=" + p.getImgPub() + "&txtpub=" + p.getTxtPub();

        req.setUrl(url);
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
